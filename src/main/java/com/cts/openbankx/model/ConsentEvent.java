package com.cts.openbankx.model;

import java.time.LocalDateTime;

import com.cts.openbankx.enums.EventType;
import com.cts.openbankx.enums.PerformedBy;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Consent_Event")

public class ConsentEvent {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ConsentEventID;
	
	private Long ConsentID;
	
	@Enumerated(EnumType.STRING)
	private EventType EventType;
	
	private LocalDateTime EventDate;
	
	private PerformedBy PerformedBy;
	
	private String Notes;

	public Long getConsentEventID() {
		return ConsentEventID;
	}

	public void setConsentEventID(Long consentEventID) {
		ConsentEventID = consentEventID;
	}

	public Long getConsentID() {
		return ConsentID;
	}

	public void setConsentID(Long consentID) {
		ConsentID = consentID;
	}

	public EventType getEventType() {
		return EventType;
	}

	public void setEventType(EventType eventType) {
		EventType = eventType;
	}

	public LocalDateTime getEventDate() {
		return EventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		EventDate = eventDate;
	}

	public PerformedBy getPerformedBy() {
		return PerformedBy;
	}

	public void setPerformedBy(PerformedBy performedBy) {
		PerformedBy = performedBy;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}
	
}
