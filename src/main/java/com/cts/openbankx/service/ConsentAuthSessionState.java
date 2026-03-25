package com.cts.openbankx.service;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ConsentAuthSessionState implements Serializable {

    private final Long consentId;
    private final Long userId;
    private final LocalDateTime authenticatedAt;
    private final String otpCode;
    private final LocalDateTime otpExpiresAt;
    private final int failedOtpAttempts;
    private final boolean scaVerified;

    public ConsentAuthSessionState(
            Long consentId,
            Long userId,
            LocalDateTime authenticatedAt,
            String otpCode,
            LocalDateTime otpExpiresAt,
            int failedOtpAttempts,
            boolean scaVerified) {
        this.consentId = consentId;
        this.userId = userId;
        this.authenticatedAt = authenticatedAt;
        this.otpCode = otpCode;
        this.otpExpiresAt = otpExpiresAt;
        this.failedOtpAttempts = failedOtpAttempts;
        this.scaVerified = scaVerified;
    }

    public Long getConsentId() {
        return consentId;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getAuthenticatedAt() {
        return authenticatedAt;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public LocalDateTime getOtpExpiresAt() {
        return otpExpiresAt;
    }

    public int getFailedOtpAttempts() {
        return failedOtpAttempts;
    }

    public boolean isScaVerified() {
        return scaVerified;
    }

    public ConsentAuthSessionState withFailedOtpAttempts(int attempts) {
        return new ConsentAuthSessionState(
                consentId,
                userId,
                authenticatedAt,
                otpCode,
                otpExpiresAt,
                attempts,
                scaVerified);
    }

    public ConsentAuthSessionState withScaVerified(boolean verified) {
        return new ConsentAuthSessionState(
                consentId,
                userId,
                authenticatedAt,
                otpCode,
                otpExpiresAt,
                failedOtpAttempts,
                verified);
    }
}
