package com.cts.openbankx.model;

import com.cts.openbankx.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "auth_client")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AuthClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tpp_app_id", nullable = false)
    @JsonIgnoreProperties({"publicKeysJWKSet"})
    private TPPApp tppApp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClientType clientType;

    @Column(columnDefinition = "TEXT")
    private String redirectURIs;

    @Column(columnDefinition = "TEXT")
    private String scopesAllowed;

    @Column(nullable = false)
    private String status;

    public AuthClient() {}

    public AuthClient(Long clientId, TPPApp tppApp, ClientType clientType, String redirectURIs, String scopesAllowed, String status) {
        this.clientId = clientId;
        this.tppApp = tppApp;
        this.clientType = clientType;
        this.redirectURIs = redirectURIs;
        this.scopesAllowed = scopesAllowed;
        this.status = status;
    }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public TPPApp getTppApp() { return tppApp; }
    public void setTppApp(TPPApp tppApp) { this.tppApp = tppApp; }

    public ClientType getClientType() { return clientType; }
    public void setClientType(ClientType clientType) { this.clientType = clientType; }

    public String getRedirectURIs() { return redirectURIs; }
    public void setRedirectURIs(String redirectURIs) { this.redirectURIs = redirectURIs; }

    public String getScopesAllowed() { return scopesAllowed; }
    public void setScopesAllowed(String scopesAllowed) { this.scopesAllowed = scopesAllowed; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
