package com.cts.openbankx.service;

import com.cts.openbankx.model.ComplianceReport;

import org.springframework.transaction.annotation.Transactional;
import com.cts.openbankx.repository.ComplianceReportRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ComplianceReportService {

    private final ComplianceReportRepository repository;

    public ComplianceReportService(ComplianceReportRepository repository) {
        this.repository = repository;
    }

    public ComplianceReport createReport(ComplianceReport report) {
    	if(report.getGeneratedDate()==null) {
    		report.setGeneratedDate(Instant.now());
    	}
        return repository.save(report);
    }

    public List<ComplianceReport> getAllReports() {
        return repository.findAll();
    }

    public ComplianceReport getReportById(UUID id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("ComplianceReport not Found:" +id));
    }
    
    public List<ComplianceReport> getByReportId(UUID compReportId){
    	return repository.findByCompReportId(compReportId);
    }

    public List<ComplianceReport> getReportsByScope(String scope) {
        return repository.findByScope(scope);
    }
    public List<ComplianceReport> getReportByGeneratedDate(Instant generatedDate){
    	return repository.findByGeneratedDate(generatedDate);
    }
    public void deleteReport(UUID compReportId) {
    	repository.deleteById(compReportId);
    }
}
