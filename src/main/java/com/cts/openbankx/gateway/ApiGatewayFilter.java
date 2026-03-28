package com.cts.openbankx.gateway;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cts.openbankx.enums.HttpMethod;
import com.cts.openbankx.model.APILog;
import com.cts.openbankx.model.TPPApp;
import com.cts.openbankx.service.APILogService;
import com.cts.openbankx.service.TPPAppService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * API Gateway Filter
 *
 * Applies ONLY to business APIs (AISP / PISP / CBPII)
 * Skips auth, onboarding, admin APIs
 */
@Component
public class ApiGatewayFilter extends OncePerRequestFilter {

    private final APILogService apiLogService;
    private final TPPAppService tppAppService;

    // Simple in‑memory rate‑limit counter
    private final Map<Long, Integer> requestCounter = new ConcurrentHashMap<>();

    private static final int MAX_REQUESTS = 1000;

    public ApiGatewayFilter(APILogService apiLogService, TPPAppService tppAppService) {
        this.apiLogService = apiLogService;
        this.tppAppService = tppAppService;
    }

    /**
     * ✅ IMPORTANT:
     * Skip gateway for non‑TPP endpoints
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getRequestURI();

        return path.startsWith("/api/v1/auth")
            || path.startsWith("/api/v1/tpp")
            || path.startsWith("/api/v1/apps")
            || path.startsWith("/api/v1/consents")
            || path.startsWith("/api/v1/users")
            || path.startsWith("/error");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        TPPApp app = null;

        try {
            /*
             * ==============================
             * 1. Header validation
             * ==============================
             */
            String tppAppHeader = request.getHeader("X-TPP-App-Id");
            if (tppAppHeader == null) {
                response.sendError(
                        HttpStatus.BAD_REQUEST.value(),
                        "Missing required header: X-TPP-App-Id");
                return;
            }

            Long tppAppId = Long.valueOf(tppAppHeader);

            /*
             * ==============================
             * 2. Validate TPP application
             * ==============================
             */
            app = tppAppService.findById(tppAppId);

            /*
             * ==============================
             * 3. Scope validation
             * ==============================
             */
//            String scope = request.getHeader("X-Scope");
//            if (scope == null || scope.isBlank()) {
//                response.sendError(
//                    HttpStatus.FORBIDDEN.value(),
//                    "Missing or empty scope"
//                );
//                return;
//            }
//
//            if (!isScopeAllowed(request.getRequestURI(), scope)) {
//                response.sendError(
//                    HttpStatus.FORBIDDEN.value(),
//                    "Scope not permitted for this resource"
//                );
//                return;
//            }

            /*
             * ==============================
             * 4. Rate limiting
             * ==============================
             */
            int count = requestCounter.merge(tppAppId, 1, Integer::sum);

            if (count > MAX_REQUESTS) {
                response.sendError(
                        HttpStatus.TOO_MANY_REQUESTS.value(),
                        "Rate limit exceeded");
                return;
            }

            /*
             * ==============================
             * 5. Continue request
             * ==============================
             */
            filterChain.doFilter(request, response);

        } finally {
            /*
             * ==============================
             * 6. API Logging (only for TPP calls)
             * ==============================
             */
            if (app != null) {
                long latency = System.currentTimeMillis() - startTime;

                APILog log = new APILog();
                log.setTppApp(app);
                log.setAuthClient(null);
                log.setEndpoint(request.getRequestURI());
                log.setMethod(HttpMethod.valueOf(request.getMethod()));
                log.setStatusCode(response.getStatus());
                log.setLatencyMs((int) latency);
                log.setTimestamp(LocalDateTime.now());

                apiLogService.save(log);
            }
        }
    }

    /**
     * Scope‑to‑endpoint rules
     */
    private boolean isScopeAllowed(String uri, String scope) {

        if (uri.startsWith("/api/v1/aisp/accounts") && scope.contains("accounts:read")) {
            return true;
        }

        if (uri.startsWith("/api/v1/pisp/payments") && scope.contains("payments")) {
            return true;
        }

        if (uri.startsWith("/api/v1/cbpii/funds-check") && scope.contains("funds")) {
            return true;
        }

        return false;
    }
}