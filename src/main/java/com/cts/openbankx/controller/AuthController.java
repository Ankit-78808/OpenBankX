package com.cts.openbankx.controller;

import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.openbankx.dto.AuthorizeConsentResponse;
import com.cts.openbankx.dto.ConsentLoginRequest;
import com.cts.openbankx.service.AuthFlowService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthFlowService authFlowService;

    public AuthController(AuthFlowService authFlowService) {
        this.authFlowService = authFlowService;
    }

    @GetMapping(value = "/authorize", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<Resource> authorizePage(@RequestParam(required = false) Long consentId) {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new ClassPathResource("static/authorize-consent.html"));
    }

    @GetMapping("/authorize/context")
    public ResponseEntity<Map<String, Object>> authorizeContext(
            @RequestParam Long consentId,
            HttpSession session) {
        AuthorizeConsentResponse review = authFlowService.getAuthorizeContext(consentId, session);
        return ResponseEntity.ok(Map.of("message", "Consent review context loaded.", "review", review));
    }

    @PostMapping(value = "/authorize/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> loginForConsentReview(
            @Valid @RequestBody ConsentLoginRequest request,
            HttpSession session) {
        AuthorizeConsentResponse review = authFlowService.authenticateCustomer(request, session);
        return ResponseEntity.ok(Map.of(
                "message", "Customer identity verified. Continue with strong customer authentication.",
                "review", review));
    }

    @PostMapping(value = "/authorize/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> clearConsentSession(
            @RequestBody Map<String, Long> request,
            HttpSession session) {
        Long consentId = request.get("consentId");
        if (consentId != null) {
            authFlowService.clearConsentSession(consentId, session);
        }
        return ResponseEntity.ok(Map.of("message", "Consent review session cleared.", "consentId", consentId));
    }
}