package com.cts.openbankx.repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.ComplianceReport;

public interface ComplianceReportRepository extends JpaRepository<ComplianceReport, UUID> {

	List<ComplianceReport> findByScope(String scope);
	List<ComplianceReport> findByCompReportId(UUID compReportId);
	List<ComplianceReport> findByGeneratedDate(Instant generatedDate);

}
