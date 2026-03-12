package com.cts.openbankx.service;

import com.cts.openbankx.model.ComplianceReport;
import com.cts.openbankx.repository.ComplianceReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ComplianceReportService {

    private final ComplianceReportRepository repository;

    public ComplianceReportService(ComplianceReportRepository repository) {
        this.repository = repository;
    }

    public ComplianceReport createReport(ComplianceReport report) {
        return repository.save(report);
    }

    public List<ComplianceReport> getAllReports() {
        return repository.findAll();
    }

    public ComplianceReport getReportById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<ComplianceReport> getReportsByScope(String scope) {
        return repository.findByScope(scope);
    }
}
