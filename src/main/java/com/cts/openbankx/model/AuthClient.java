package com.cts.openbankx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Maps to table: AuthClient
 * Columns: ClientID, TPPAppID, ClientType, RedirectURIs, ScopesAllowed, Status
 */
@Entity
@Table(name = "AuthClient")
public class AuthClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClientID", nullable = false, unique = true)
    private Long clientId; // PK

    @NotNull
    @Column(name = "TPPAppID", nullable = false)
    private Long tppAppId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ClientType", nullable = false, length = 20)
    private ClientType clientType; // Confidential / Public

    @NotBlank
    @Column(name = "RedirectURIs", nullable = false, length = 2000)
    private String redirectUris; // e.g., "https://app.example.com/callback"

    @NotBlank
    @Column(name = "ScopesAllowed", nullable = false, length = 1000)
    private String scopesAllowed; // e.g., "accounts:read balances:read payments:write"

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false, length = 20)
    private Status status = Status.Active; // Active / Revoked

    // ---- Required by JPA
    protected AuthClient() {}

    // ---- Convenience constructor (optional)
    public AuthClient(Long tppAppId,
                      ClientType clientType,
                      String redirectUris,
                      String scopesAllowed,
                      Status status) {
        this.tppAppId = tppAppId;
        this.clientType = clientType;
        this.redirectUris = redirectUris;
        this.scopesAllowed = scopesAllowed;
        this.status = status;
    }

    // ---- Getters & Setters (camelCase) ----
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getTppAppId() {
        return tppAppId;
    }

    public void setTppAppId(Long tppAppId) {
        this.tppAppId = tppAppId;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public String getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(String redirectUris) {
        this.redirectUris = redirectUris;
    }

    public String getScopesAllowed() {
        return scopesAllowed;
    }

    public void setScopesAllowed(String scopesAllowed) {
        this.scopesAllowed = scopesAllowed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // ---- Enums ----
    public enum ClientType {
        Confidential, Public
    }

    public enum Status {
        Active, Revoked
    }
}