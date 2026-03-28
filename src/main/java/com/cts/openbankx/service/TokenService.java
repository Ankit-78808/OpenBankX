package com.cts.openbankx.service;

import com.cts.openbankx.auth.AuthorizationCode;
import com.cts.openbankx.auth.AuthorizationCodeRepository;
import com.cts.openbankx.enums.ConsentStatus;
import com.cts.openbankx.enums.TokenStatus;
import com.cts.openbankx.enums.TokenType;
import com.cts.openbankx.model.Consent;
import com.cts.openbankx.model.Token;
import com.cts.openbankx.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {

    private final TokenRepository tokenRepo;
    private final AuthorizationCodeRepository authCodeRepo;

    public TokenService(TokenRepository tokenRepo,
                        AuthorizationCodeRepository authCodeRepo) {
        this.tokenRepo = tokenRepo;
        this.authCodeRepo = authCodeRepo;
    }

    /* ============================================================
       ✅ BASIC CRUD OPERATIONS (KEEPING YOUR EXISTING FUNCTIONS)
       ============================================================ */

    public List<Token> findAll() {
        return tokenRepo.findAll();
    }

    public Token findById(Long id) {
        return tokenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Token not found: " + id));
    }

    public Token save(Token t) {
        return tokenRepo.save(t);
    }

    public List<Token> findByUser(Long userId) {
        return tokenRepo.findByUser_UserId(userId);
    }

    public Token revoke(Long id) {
        Token t = findById(id);
        t.setStatus(TokenStatus.REVOKED);
        return tokenRepo.save(t);
    }

    /* ============================================================
       ✅ OAUTH2: AUTHORIZATION CODE → ACCESS TOKEN
       ============================================================ */

    public Map<String, Object> exchangeAuthorizationCode(
            String grantType,
            String code,
            Long clientId
    ) {

        // 1️⃣ Validate grant type
        if (!"authorization_code".equals(grantType)) {
            throw new RuntimeException("Unsupported grant_type");
        }

        // 2️⃣ Fetch valid authorization code
        AuthorizationCode authCode =
                authCodeRepo.findByCodeAndUsedFalse(code)
                        .orElseThrow(() ->
                                new RuntimeException("Invalid or already used authorization code"));

        // 3️⃣ Check expiry
        if (authCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Authorization code expired");
        }

        // 4️⃣ Validate client
        if (!authCode.getAuthClient().getClientId().equals(clientId)) {
            throw new RuntimeException("Client mismatch");
        }

        // 5️⃣ Validate consent
        Consent consent = authCode.getConsent();
        if (consent.getStatus() != ConsentStatus.ACTIVE) {
            throw new RuntimeException("Consent is not ACTIVE");
        }

        // 6️⃣ Mark code as used (IMPORTANT)
        authCode.setUsed(true);
        authCodeRepo.save(authCode);

        // 7️⃣ Create ACCESS TOKEN
        Token accessToken = new Token();
        accessToken.setAuthClient(authCode.getAuthClient());
        accessToken.setUser(authCode.getUser());
        accessToken.setTokenType(TokenType.ACCESS);
        accessToken.setScope(consent.getScopeJSON());
        accessToken.setIssuedAt(LocalDateTime.now());
        accessToken.setExpiresAt(LocalDateTime.now().plusHours(1));
        accessToken.setStatus(TokenStatus.ACTIVE);

        tokenRepo.save(accessToken);

        // 8️⃣ Create REFRESH TOKEN
        Token refreshToken = new Token();
        refreshToken.setAuthClient(authCode.getAuthClient());
        refreshToken.setUser(authCode.getUser());
        refreshToken.setTokenType(TokenType.REFRESH);
        refreshToken.setScope(consent.getScopeJSON());
        refreshToken.setIssuedAt(LocalDateTime.now());
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(30));
        refreshToken.setStatus(TokenStatus.ACTIVE);

        tokenRepo.save(refreshToken);

        // 9️⃣ OAuth2-compliant response
        return Map.of(
                "access_token", "atk_" + UUID.randomUUID().toString().replace("-", ""),
                "refresh_token", "rtk_" + UUID.randomUUID().toString().replace("-", ""),
                "token_type", "Bearer",
                "expires_in", 3600,
                "scope", accessToken.getScope()
        );
    }
}