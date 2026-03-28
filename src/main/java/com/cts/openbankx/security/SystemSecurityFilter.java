package com.cts.openbankx.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cts.openbankx.model.TPPApp;
import com.cts.openbankx.model.Token;
import com.cts.openbankx.service.TPPAppService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 

@Component
public class SystemSecurityFilter extends OncePerRequestFilter {

    private final TPPAppService tppAppService;
    private final SystemTokenService systemTokenService;

    public SystemSecurityFilter(TPPAppService tppAppService,
                                SystemTokenService systemTokenService) {
        this.tppAppService = tppAppService;
        this.systemTokenService = systemTokenService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        return "OPTIONS".equalsIgnoreCase(request.getMethod())
            || path.startsWith("/api/v1/internal")
            || path.startsWith("/api/v1/auth")
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

        try {
            String appIdHeader = request.getHeader("X-TPP-App-Id");

            if (appIdHeader == null || appIdHeader.isBlank()) {
                response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Missing required header: X-TPP-App-Id");
                return;
            }

            Long appId;
            try {
                appId = Long.valueOf(appIdHeader);
            } catch (NumberFormatException ex) {
                response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid X-TPP-App-Id");
                return;
            }

            // ✅ Resolve TPP App
            TPPApp app = tppAppService.findById(appId);

            // ✅ Resolve scope
            String scope = EndpointScopeMapper.resolveScope(request);

            // ✅ Resolve system token
            Token token = systemTokenService.resolveToken(app, scope);

            // ✅ STORE BOTH TOKEN AND TPP IN CONTEXT (THIS IS THE FIX)
            RequestSecurityContext.set(token, app);

            filterChain.doFilter(request, response);

        } finally {
            RequestSecurityContext.clear();
        }
    }

}

