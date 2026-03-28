package com.cts.openbankx.auth;

/**
 * DTO for OAuth2 Token Endpoint
 *
 * Used by:
 * POST /api/v1/auth/token
 *
 * Supports:
 * - Authorization Code grant
 */
public class TokenRequest {

    private String grant_type;
    private String code;
    private Long client_id;

    public TokenRequest() {
    }

    // ✅ Getters & Setters

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }
}