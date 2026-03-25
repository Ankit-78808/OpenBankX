package com.cts.openbankx.model;

import java.time.Instant;
import java.util.UUID;

import com.cts.openbankx.enums.ReportScope;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "compliance_reports")

public class ComplianceReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID compReportId;
    
    @Enumerated(EnumType.STRING)
	private ReportScope scope; // TPP / Consent / Period

    // Metrics stored as separate fields
    private int activeConsents;
    public UUID getCompReportId() {
		return compReportId;
	}

	public void setCompReportId(UUID compReportId) {
		this.compReportId = compReportId;
	}

	public ReportScope getScope() {
		return scope;
	}

	public void setScope(ReportScope scope) {
		this.scope = scope;
	}

	public int getActiveConsents() {
		return activeConsents;
	}

	public void setActiveConsents(int activeConsents) {
		this.activeConsents = activeConsents;
	}

	public int getRevocations() {
		return revocations;
	}

	public void setRevocations(int revocations) {
		this.revocations = revocations;
	}

	public int getScaFailures() {
		return scaFailures;
	}

	public void setScaFailures(int scaFailures) {
		this.scaFailures = scaFailures;
	}

	public int getBreaches() {
		return breaches;
	}

	public void setBreaches(int breaches) {
		this.breaches = breaches;
	}

	public Instant getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(Instant generatedDate) {
		this.generatedDate = generatedDate;
	}

	private int revocations;
    private int scaFailures;
    private int breaches;

    // Modern Java 8+ time API
    private Instant generatedDate;
    

    // getters and setters
}
