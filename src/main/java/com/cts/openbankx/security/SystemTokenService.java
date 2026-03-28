package com.cts.openbankx.security;
import com.cts.openbankx.model.Token;
import com.cts.openbankx.service.ConsentService;
import com.cts.openbankx.service.TokenService;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.cts.openbankx.enums.TokenStatus;
import com.cts.openbankx.enums.TokenType;
import com.cts.openbankx.model.Consent;
import com.cts.openbankx.model.TPPApp;

@Service
public class SystemTokenService {

    private final TokenService tokenService;
    private final ConsentService consentService;

    private final Map<String, Token> cache = new ConcurrentHashMap<>();

    public SystemTokenService(TokenService tokenService,
                              ConsentService consentService) {
        this.tokenService = tokenService;
        this.consentService = consentService;
    }

    public Token resolveToken(TPPApp app, String scope) {

        String key = app.getTppAppId() + ":" + scope;

        Token cached = cache.get(key);
        if (cached != null &&
            cached.getExpiresAt().isAfter(LocalDateTime.now())) {
            return cached;
        }

        Consent consent = consentService
                .findByTppApp(app.getTppAppId())
                .stream()
                .filter(c -> c.getStatus().name().equals("ACTIVE"))
                .filter(c -> c.getScopeJSON().contains(scope))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("No active consent for scope"));

        Token token = new Token();
        token.setUser(consent.getUser());
        token.setTokenType(TokenType.ACCESS);
        token.setScope(scope);
        token.setIssuedAt(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        token.setStatus(TokenStatus.ACTIVE);

        Token saved = tokenService.save(token);
        cache.put(key, saved);

        return saved;
    }
}
