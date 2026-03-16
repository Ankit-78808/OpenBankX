package com.cts.openbankx.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import com.cts.openbankx.enums.ActorType;
import com.cts.openbankx.enums.ReportScope;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "compliance_reports")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ComplianceReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID compReportId;
    
    @Enumerated(EnumType.STRING)
	private ReportScope scope; // TPP / Consent / Period

    // Metrics stored as separate fields
    private int activeConsents;
    private int revocations;
    private int scaFailures;
    private int breaches;

    // Modern Java 8+ time API
    private Instant generatedDate;
    

    // getters and setters
}
