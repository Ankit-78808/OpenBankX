package com.cts.openbankx.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AuthorizeConsentResponse(
        Long consentId,
        String consentStatus,
        String scope,
        String resourceFilterJSON,
        LocalDateTime createdDate,
        LocalDateTime expiredDate,
        String tppAppName,
        String tppScopesRequested,
        boolean expired,
        boolean authenticatedCustomer,
        boolean scaVerified,
        String customerName,
        String maskedEmail,
        String maskedPhone,
        LocalDateTime otpExpiresAt,
        String deviceBindingHint,
        List<String> supportedScaMethods,
        String sandboxOtp,
        String nextStep
) {
}
