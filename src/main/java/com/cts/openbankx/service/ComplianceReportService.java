package com.cts.openbankx.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.openbankx.model.ComplianceReport;
import com.cts.openbankx.repository.ComplianceReportRepository;

@Service
public class ComplianceReportService {
	private final ComplianceReportRepository repo;

	public ComplianceReportService(ComplianceReportRepository repo) {
		this.repo = repo;
	}

	public List<ComplianceReport> findAll() {
		return repo.findAll();
	}

	public ComplianceReport findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("ComplianceReport not found: " + id));
	}

	public ComplianceReport save(ComplianceReport r) {
		if (r.getGeneratedDate() == null)
			r.setGeneratedDate(LocalDateTime.now());
		return repo.save(r);
	}
}
