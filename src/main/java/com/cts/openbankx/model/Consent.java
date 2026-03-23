package com.cts.openbankx.model;

import java.time.LocalDateTime;
import java.util.List;

import com.cts.openbankx.enums.ConsentScope;
import com.cts.openbankx.enums.ConsentStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;


@Entity
@Table(name = "Consent")
public class Consent{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consentId;
    
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "UserID",
    			nullable = false,
    			foreignKey = @ForeignKey(name = "fk_consent_user"))
    private User user;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "TPPAppID",
    			nullable = false,
    			foreignKey = @ForeignKey(name = "fk_consent_TPPApp"))
    private TPPApp tppApp;
    
	@Enumerated(EnumType.STRING)
    private ConsentScope scopeJSON;
    

    private String resourceFilterJSON;

    private LocalDateTime createdDate;
    private LocalDateTime expiredDate;
    
    @Enumerated(EnumType.STRING)
    private ConsentStatus status;
    
    @OneToMany(mappedBy = "consent", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ConsentEvent> consentEvents;//it can have many events 
    
    
    public Long getConsentId() {
		return consentId;
	}

	public void setConsentId(Long consentId) {
		this.consentId = consentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TPPApp getTppApp() {
		return tppApp;
	}

	public void setTppApp(TPPApp tppApp) {
		this.tppApp = tppApp;
	}

	public ConsentScope getScopeJSON() {
		return scopeJSON;
	}

	public void setScopeJSON(ConsentScope scopeJSON) {
		this.scopeJSON = scopeJSON;
	}

	public String getResourceFilterJSON() {
		return resourceFilterJSON;
	}

	public void setResourceFilterJSON(String resourceFilterJSON) {
		this.resourceFilterJSON = resourceFilterJSON;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDateTime expiredDate) {
		this.expiredDate = expiredDate;
	}

	public ConsentStatus getStatus() {
		return status;
	}

	public void setStatus(ConsentStatus status) {
		this.status = status;
	}

	public List<ConsentEvent> getConsentEvents() {
		return consentEvents;
	}

	public void setConsentEvents(List<ConsentEvent> consentEvents) {
		this.consentEvents = consentEvents;
	}


    
}