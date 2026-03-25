package com.cts.openbankx.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.openbankx.dto.AuthorizeConsentResponse;
import com.cts.openbankx.dto.ConsentLoginRequest;
import com.cts.openbankx.service.AuthFlowService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Validated
@Controller
@RequestMapping("/api/v1/auth")
/**
 * Customer-facing consent authorization endpoints.
 *
 * This controller is intentionally thin:
 * - it accepts browser/API requests
 * - delegates business rules to AuthFlowService
 * - returns either the authorization page or JSON responses
 */
public class AuthController {

    private static final String REVIEW = "review";
    private static final String MESSAGE = "message";

    private final AuthFlowService authFlowService;

    public AuthController(AuthFlowService authFlowService) {
        this.authFlowService = authFlowService;
    }

    /**
     * Serves the consent authorization page.
     * <p>
     * The HTML page reads the consentId from the query string and then
     * calls the JSON endpoints below to load context, verify the customer,
     * and complete SCA.
     */
    @GetMapping(value = "/authorize", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<Resource> authorizePage(@RequestParam(required = false) Long consentId) {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new ClassPathResource("static/authorize-consent.html"));
    }

    /**
     * Loads the current consent review state for the browser page.
     * <p>
     * Response includes consent details plus session-derived flags such as:
     * - whether the customer has already been identified in this session
     * - whether SCA has already been completed in this session
     */
    @GetMapping(value = "/authorize/context", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> authorizeContext(
            @RequestParam Long consentId,
            HttpSession session) {
        AuthorizeConsentResponse review = authFlowService.getAuthorizeContext(consentId, session);
        return ResponseEntity.ok(reviewResponse("Consent review context loaded.", review));
    }

    /**
     * Verifies that the customer reviewing the consent is the same customer
     * linked to that consent.
     * <p>
     * In the current implementation this is done by matching email + phone,
     * then creating a session-scoped auth state used by later SCA steps.
     */
    @PostMapping(value = "/authorize/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> loginForConsentReview(
            @Valid @RequestBody ConsentLoginRequest request,
            HttpSession session) {
        AuthorizeConsentResponse review = authFlowService.authenticateCustomer(request, session);
        return ResponseEntity.ok(reviewResponse(
                "Customer identity verified. Continue with strong customer authentication.",
                review));
    }

    /**
     * Clears any session state for the given consent review flow.
     * <p>
     * Useful when the user wants to restart the consent journey in the same
     * browser session.
     */
    @PostMapping(value = "/authorize/logout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> clearConsentSession(
            @RequestBody Map<String, Long> request,
            HttpSession session) {
        Long consentId = request.get("consentId");
        if (consentId != null) {
            authFlowService.clearConsentSession(consentId, session);
        }
        return ResponseEntity.ok(messageResponse("Consent review session cleared.", "consentId", consentId));
    }

    private Map<String, Object> reviewResponse(String message, AuthorizeConsentResponse review) {
        return messageResponse(message, REVIEW, review);
    }

    private Map<String, Object> messageResponse(String message, String key, Object value) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(MESSAGE, message);
        response.put(key, value);
        return response;
    }
}
