package com.cts.openbankx.model;

import com.cts.openbankx.enums.ConsentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consent")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tpp_app_id", nullable = false)
    private TPPApp tppApp;

    @Column(columnDefinition = "TEXT")
    private String scopeJSON;

    @Column(columnDefinition = "TEXT")
    private String resourceFilterJSON;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsentStatus status;

    public Consent() {}

    public Consent(Long consentId, User user, TPPApp tppApp, String scopeJSON, String resourceFilterJSON, LocalDateTime createdDate, LocalDateTime expiryDate, ConsentStatus status) {
        this.consentId = consentId;
        this.user = user;
        this.tppApp = tppApp;
        this.scopeJSON = scopeJSON;
        this.resourceFilterJSON = resourceFilterJSON;
        this.createdDate = createdDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    public Long getConsentId() { return consentId; }
    public void setConsentId(Long consentId) { this.consentId = consentId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public TPPApp getTppApp() { return tppApp; }
    public void setTppApp(TPPApp tppApp) { this.tppApp = tppApp; }

    public String getScopeJSON() { return scopeJSON; }
    public void setScopeJSON(String scopeJSON) { this.scopeJSON = scopeJSON; }

    public String getResourceFilterJSON() { return resourceFilterJSON; }
    public void setResourceFilterJSON(String resourceFilterJSON) { this.resourceFilterJSON = resourceFilterJSON; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }

    public ConsentStatus getStatus() { return status; }
    public void setStatus(ConsentStatus status) { this.status = status; }
}
