package com.cts.openbankx.model;

import com.cts.openbankx.enums.ConsentEventType;
import com.cts.openbankx.enums.PerformedBy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consent_event")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ConsentEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consentEventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consent_id", nullable = false)
    private Consent consent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsentEventType eventType;

    @Column(nullable = false)
    private LocalDateTime eventDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerformedBy performedBy;

    private String notes;

    public ConsentEvent() {}

    public ConsentEvent(Long consentEventId, Consent consent, ConsentEventType eventType, LocalDateTime eventDate, PerformedBy performedBy, String notes) {
        this.consentEventId = consentEventId;
        this.consent = consent;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.performedBy = performedBy;
        this.notes = notes;
    }

    public Long getConsentEventId() { return consentEventId; }
    public void setConsentEventId(Long consentEventId) { this.consentEventId = consentEventId; }

    public Consent getConsent() { return consent; }
    public void setConsent(Consent consent) { this.consent = consent; }

    public ConsentEventType getEventType() { return eventType; }
    public void setEventType(ConsentEventType eventType) { this.eventType = eventType; }

    public LocalDateTime getEventDate() { return eventDate; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    public PerformedBy getPerformedBy() { return performedBy; }
    public void setPerformedBy(PerformedBy performedBy) { this.performedBy = performedBy; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
