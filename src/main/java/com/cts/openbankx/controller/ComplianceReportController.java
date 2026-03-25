package com.cts.openbankx.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.ComplianceReport;
import com.cts.openbankx.service.ComplianceReportService;

@RestController
@RequestMapping("/api/v1/compliance-reports") 
public class ComplianceReportController {
	
    private final ComplianceReportService complianceReportService;

    
    public ComplianceReportController(ComplianceReportService complianceReportService) {
        this.complianceReportService = complianceReportService;
    }
	
    @PostMapping
    public ResponseEntity<ComplianceReport> createReport(@RequestBody ComplianceReport compReport) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(complianceReportService.createReport(compReport));
    }
	
    @GetMapping
    public ResponseEntity<List<ComplianceReport>> getAllReports() {
        return ResponseEntity.ok(complianceReportService.getAllReports());
    }
	
    @GetMapping("/{id}")
    public ResponseEntity<ComplianceReport> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(complianceReportService.getReportById(id));
    }

    
    @DeleteMapping("/{compReportId}")
    public ResponseEntity<Void> delete(@PathVariable UUID compReportId) {
        complianceReportService.deleteReport(compReportId);
        return ResponseEntity.noContent().build();
    }
    
    
}