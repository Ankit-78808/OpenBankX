package com.cts.openbankx.controller;

import com.cts.openbankx.model.ComplianceReport;
import com.cts.openbankx.service.ComplianceReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/compliance-reports")
public class ComplianceReportController {

    private final ComplianceReportService service;

    public ComplianceReportController(ComplianceReportService service) {
        this.service = service;
    }

    // Create new compliance report
    @PostMapping
    public ComplianceReport createReport(@RequestBody ComplianceReport report) {
        return service.createReport(report);
    }

    // Fetch all compliance reports
    @GetMapping
    public List<ComplianceReport> getAllReports() {
        return service.getAllReports();
    }

    // Fetch compliance report by ID
    @GetMapping("/{id}")
    public ComplianceReport getReportById(@PathVariable UUID id) {
        return service.getReportById(id);
    }

    // Fetch compliance reports by scope
    @GetMapping("/scope/{scope}")
    public List<ComplianceReport> getReportsByScope(@PathVariable String scope) {
        return service.getReportsByScope(scope);
    }
}
