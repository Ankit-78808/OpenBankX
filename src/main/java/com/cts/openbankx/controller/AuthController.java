package com.cts.openbankx.controller;

import com.cts.openbankx.auth.TokenRequest;
import com.cts.openbankx.enums.*;
import com.cts.openbankx.model.*;
import com.cts.openbankx.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthClientService authClientService;
    private final TokenService tokenService;
    private final SCAEventService scaEventService;
    private final UserService userService;
    private final ConsentService consentService;

    public AuthController(AuthClientService authClientService,
                          TokenService tokenService,
                          SCAEventService scaEventService,
                          UserService userService,
                          ConsentService consentService) {
        this.authClientService = authClientService;
        this.tokenService = tokenService;
        this.scaEventService = scaEventService;
        this.userService = userService;
        this.consentService = consentService;
    }

    // In-memory OTP store: consentId → [otpCode, expiryTime]
    private final Map<Long, Object[]> otpStore = new HashMap<>();

    // ------------------------------------------------------------
    // GET /api/v1/auth/authorize
    // ------------------------------------------------------------
    @GetMapping("/authorize")
    public Map<String, Object> authorize(
            @RequestParam Long userId,
            @RequestParam Long consentId
    ) {

        User user = userService.findById(userId);
        Consent consent = consentService.findById(consentId);

        String otp = String.format("%06d", new Random().nextInt(1_000_000));
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);
        otpStore.put(consentId, new Object[]{otp, expiresAt});

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("userId", user.getUserId());
        response.put("userName", user.getName());
        response.put("consentId", consent.getConsentId());
        response.put("scopes", consent.getScopeJSON());
        response.put("tppAppName", consent.getTppApp().getAppName());
        response.put("expiryDate", consent.getExpiryDate());
        response.put("status", consent.getStatus());
        response.put("otp", otp); // exposed only for testing
        response.put("otpExpiresAt", expiresAt);
        response.put("message", "Please approve the consent using OTP.");

        return response;
    }

    // ------------------------------------------------------------
    // POST /api/v1/auth/sca/verify
    // ------------------------------------------------------------
    @PostMapping("/sca/verify")
    public ResponseEntity<Map<String, Object>> verifySCA(
            @RequestBody Map<String, Object> request
    ) {

        Long consentId = Long.valueOf(request.get("consentId").toString());
        String submittedOtp = request.get("otp").toString();

        Object[] stored = otpStore.get(consentId);
        if (stored == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "No OTP found or expired"));
        }

        String storedOtp = (String) stored[0];
        LocalDateTime expiry = (LocalDateTime) stored[1];

        if (LocalDateTime.now().isAfter(expiry)) {
            otpStore.remove(consentId);
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "OTP expired"));
        }

        if (!storedOtp.equals(submittedOtp)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid OTP"));
        }

        otpStore.remove(consentId);

        SCAEvent event = new SCAEvent();
        event.setUser(
                userService.findById(
                        Long.valueOf(request.get("userId").toString())
                )
        );
        event.setMethod(SCAMethod.OTP);
        event.setResult(SCAResult.PASS);
        event.setEventTime(LocalDateTime.now());
        event.setReferenceId("CONSENT-" + consentId);

        SCAEvent saved = scaEventService.save(event);

        return ResponseEntity.ok(
                Map.of(
                        "scaEventId", saved.getScaEventId(),
                        "verified", true,
                        "message", "SCA verification successful"
                )
        );
    }

    // ------------------------------------------------------------
    // ✅ POST /api/v1/auth/token  (ONLY THIS IS CHANGED)
    // OAuth2 Authorization Code → Access Token
    // ------------------------------------------------------------
    @PostMapping("/token")
    public Map<String, Object> exchangeToken(
            @RequestBody TokenRequest request
    ) {

        return tokenService.exchangeAuthorizationCode(
                request.getGrant_type(),
                request.getCode(),
                request.getClient_id()
        );
    }

    // ------------------------------------------------------------
    // BELOW: EXISTING CRUD APIs (UNCHANGED)
    // ------------------------------------------------------------

    @GetMapping("/clients")
    public List<AuthClient> getAllClients() {
        return authClientService.findAll();
    }

    @GetMapping("/clients/{id}")
    public AuthClient getClient(@PathVariable Long id) {
        return authClientService.findById(id);
    }

    @PostMapping("/clients")
    public AuthClient createClient(@RequestBody AuthClient client) {
        return authClientService.save(client);
    }

    @GetMapping("/tokens")
    public List<Token> getAllTokens() {
        return tokenService.findAll();
    }

    @GetMapping("/tokens/user/{userId}")
    public List<Token> getTokensByUser(@PathVariable Long userId) {
        return tokenService.findByUser(userId);
    }

    @PutMapping("/tokens/{id}/revoke")
    public Token revokeToken(@PathVariable Long id) {
        return tokenService.revoke(id);
    }

    @GetMapping("/sca-events")
    public List<SCAEvent> getAllSCAEvents() {
        return scaEventService.findAll();
    }

    @GetMapping("/sca-events/user/{userId}")
    public List<SCAEvent> getSCAByUser(@PathVariable Long userId) {
        return scaEventService.findByUser(userId);
    }

    @PostMapping("/sca-events")
    public SCAEvent createSCA(@RequestBody SCAEvent event) {
        return scaEventService.save(event);
    }
}
