package com.cts.openbankx.model;

import java.time.LocalDateTime;

import com.cts.openbankx.enums.EventType;
import com.cts.openbankx.enums.PerformedBy;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Consent_Event")

public class ConsentEvent {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "consentEventId")
	private Long consentEventId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consentId",
				nullable = false,
				foreignKey = @ForeignKey(name = "fk_consent_event_consent"))
	@JsonBackReference
	private Consent consent;
	
	@Enumerated(EnumType.STRING)
	private EventType eventType;
	
	private LocalDateTime eventDate;
	
	@Enumerated(EnumType.STRING)
	private PerformedBy performedBy;
	
	private String notes;
	
	

	public Long getConsentEventId() {
		return consentEventId;
	}

	public void setConsentEventId(Long consentEventId) {
		this.consentEventId = consentEventId;
	}

	public Consent getConsent() {
		return consent;
	}

	public void setConsent(Consent consent) {
		this.consent = consent;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public PerformedBy getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(PerformedBy performedBy) {
		this.performedBy = performedBy;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
