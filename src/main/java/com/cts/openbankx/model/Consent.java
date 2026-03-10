package com.cts.openbankx.model;

import java.time.LocalDateTime;

import com.cts.openbankx.enums.Scope;
import com.cts.openbankx.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Consent")
public class Consent{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ConsentId;
    private Long UserId;
    // private Long TPPAppID;

    @Enumerated(EnumType.STRING)
    private Scope ScopeJSON;
    

    private String ResourceFilterJSON;

    private LocalDateTime CreatedDate;
    private LocalDateTime ExpiredDate;

    @Enumerated(EnumType.STRING)
    private Status Status;

    public Long getConsentId() {
        return ConsentId;
    }

    public void setConsentId(Long consentId) {
        ConsentId = consentId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    // public Long getTPPAppID() {
    //     return TPPAppID;
    // }

    // public void setTPPAppID(Long tPPAppID) {
    //     TPPAppID = tPPAppID;
    // }

    public Scope getScopeJSON() {
        return ScopeJSON;
    }

    public void setScopeJSON(Scope scopeJSON) {
        ScopeJSON = scopeJSON;
    }

    public String getResourceFilterJSON() {
        return ResourceFilterJSON;
    }

    public void setResourceFilterJSON(String resourceFilterJSON) {
        ResourceFilterJSON = resourceFilterJSON;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public LocalDateTime getExpiredDate() {
        return ExpiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        ExpiredDate = expiredDate;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status status) {
        Status = status;
    }
    
}