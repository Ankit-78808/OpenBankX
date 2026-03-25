package com.cts.openbankx.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.cts.openbankx.dto.AuthorizeConsentResponse;
import com.cts.openbankx.dto.ConsentLoginRequest;
import com.cts.openbankx.model.Consent;
import com.cts.openbankx.model.User;
import com.cts.openbankx.repository.ConsentRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthFlowService {

    private static final String CONSENT_AUTH_STATES = "consentAuthStates";
    private static final int OTP_TTL_MINUTES = 5;
    private static final String DEVICE_BINDING_PREFIX = "BANK-DEVICE-";
    private static final List<String> SUPPORTED_SCA_METHODS = List.of("OTP", "Device", "App");

    private final ConsentRepository consentRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    public AuthFlowService(ConsentRepository consentRepository) {
        this.consentRepository = consentRepository;
    }

    @Transactional(readOnly = true)
    public AuthorizeConsentResponse getAuthorizeContext(Long consentId, HttpSession session) {
        Consent consent = getConsent(consentId);
        ConsentAuthSessionState state = getSessionState(session, consentId);
        return toAuthorizeConsentResponse(consent, state);
    }

    @Transactional
    public AuthorizeConsentResponse authenticateCustomer(ConsentLoginRequest request, HttpSession session) {
        Consent consent = getConsent(request.consentId());
        validateConsent(consent);

        User user = consent.getUser();
        if (!isMatchingCustomer(user, request.email(), request.phone())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Customer identity could not be verified for this consent");
        }

        ConsentAuthSessionState state = createSessionState(consent, user);
        saveSessionState(session, state);
        return toAuthorizeConsentResponse(consent, state);
    }

    public void clearConsentSession(Long consentId, HttpSession session) {
        getSessionStates(session).remove(consentId);
    }

    private Consent getConsent(Long consentId) {
        return consentRepository.findById(consentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consent not found"));
    }

    private void validateConsent(Consent consent) {
        if (isExpired(consent)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Consent has already expired");
        }
    }

    private boolean isMatchingCustomer(User user, String email, String phone) {
        return matchesEmail(user, email) && matchesPhone(user, phone);
    }

    private ConsentAuthSessionState createSessionState(Consent consent, User user) {
        LocalDateTime now = LocalDateTime.now();
        return new ConsentAuthSessionState(
                consent.getConsentId(),
                user.getUserId(),
                now,
                generateOtp(),
                now.plusMinutes(OTP_TTL_MINUTES),
                0,
                false);
    }

    private boolean matchesEmail(User user, String email) {
        return user.getEmail() != null && user.getEmail().equalsIgnoreCase(email.trim());
    }

    private boolean matchesPhone(User user, String phone) {
        return normalizeDigits(user.getPhone()).equals(normalizeDigits(phone));
    }

    private String normalizeDigits(String value) {
        return value == null ? "" : value.replaceAll("\\D", "");
    }

    private String generateOtp() {
        int otp = 100000 + secureRandom.nextInt(900000);
        return Integer.toString(otp);
    }

    private AuthorizeConsentResponse toAuthorizeConsentResponse(Consent consent, ConsentAuthSessionState state) {
        boolean expired = isExpired(consent);
        boolean authenticatedCustomer = state != null;
        boolean scaVerified = state != null && state.isScaVerified();
        User user = consent.getUser();

        return new AuthorizeConsentResponse(
                consent.getConsentId(),
                consent.getStatus().name(),
                consent.getScopeJSON().name(),
                consent.getResourceFilterJSON(),
                consent.getCreatedDate(),
                consent.getExpiredDate(),
                consent.getTppApp().getAppName(),
                consent.getTppApp().getScopesRequested(),
                expired,
                authenticatedCustomer,
                scaVerified,
                authenticatedCustomer ? user.getName() : null,
                authenticatedCustomer ? maskEmail(user.getEmail()) : null,
                authenticatedCustomer ? maskPhone(user.getPhone()) : null,
                state != null ? state.getOtpExpiresAt() : null,
                authenticatedCustomer ? deviceBindingHint(user.getUserId()) : null,
                SUPPORTED_SCA_METHODS,
                authenticatedCustomer && !scaVerified ? state.getOtpCode() : null,
                nextStep(expired, authenticatedCustomer, scaVerified));
    }

    private boolean isExpired(Consent consent) {
        return consent.getExpiredDate() != null && consent.getExpiredDate().isBefore(LocalDateTime.now());
    }

    private String nextStep(boolean expired, boolean authenticatedCustomer, boolean scaVerified) {
        if (expired) {
            return "This consent has expired and can no longer be authorized.";
        }
        if (!authenticatedCustomer) {
            return "Verify your email and phone number to review the consent request.";
        }
        if (!scaVerified) {
            return "Complete OTP, device binding, or app approval to finish strong customer authentication.";
        }
        return "Customer authentication is complete. The consent is ready for downstream processing.";
    }

    private String maskEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            return null;
        }

        String[] parts = email.split("@", 2);
        String local = parts[0];
        String domain = parts[1];
        if (local.length() <= 2) {
            return local.charAt(0) + "*" + "@" + domain;
        }
        return local.substring(0, 2) + "***@" + domain;
    }

    private String maskPhone(String phone) {
        String digits = normalizeDigits(phone);
        if (digits.length() < 4) {
            return phone;
        }
        return "******" + digits.substring(digits.length() - 4);
    }

    private String deviceBindingHint(Long userId) {
        return DEVICE_BINDING_PREFIX + userId;
    }

    private void saveSessionState(HttpSession session, ConsentAuthSessionState state) {
        getSessionStates(session).put(state.getConsentId(), state);
    }

    private ConsentAuthSessionState getSessionState(HttpSession session, Long consentId) {
        return getSessionStates(session).get(consentId);
    }

    @SuppressWarnings("unchecked")
    private Map<Long, ConsentAuthSessionState> getSessionStates(HttpSession session) {
        Object existing = session.getAttribute(CONSENT_AUTH_STATES);
        if (existing instanceof Map<?, ?> states) {
            return (Map<Long, ConsentAuthSessionState>) states;
        }

        Map<Long, ConsentAuthSessionState> created = new HashMap<>();
        session.setAttribute(CONSENT_AUTH_STATES, created);
        return created;
    }
}