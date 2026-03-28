package com.cts.openbankx.controller;

import com.cts.openbankx.model.AuditTrail;
import com.cts.openbankx.model.ComplianceReport;
import com.cts.openbankx.service.AuditTrailService;
import com.cts.openbankx.service.ComplianceReportService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/compliance")
@PreAuthorize("hasRole('ADMIN')")
public class ComplianceController {
    private final AuditTrailService auditService;
    private final ComplianceReportService reportService;

    public ComplianceController(AuditTrailService auditService, ComplianceReportService reportService) {
        this.auditService = auditService;
        this.reportService = reportService;
    }

    // --- Audit Trails (read-only + create, no update/delete = immutable) ---
    @GetMapping("/audit")
    public List<AuditTrail> getAllAudit() { return auditService.findAll(); }

    @GetMapping("/audit/actor/{actorId}")
    public List<AuditTrail> getByActor(@PathVariable String actorId) { return auditService.findByActor(actorId); }

    @GetMapping("/audit/resource/{resource}")
    public List<AuditTrail> getByResource(@PathVariable String resource) { return auditService.findByResource(resource); }

    @PostMapping("/audit")
    public AuditTrail createAudit(@RequestBody AuditTrail trail) { return auditService.save(trail); }

    // --- Compliance Reports ---
    @GetMapping("/reports")
    public List<ComplianceReport> getAllReports() { return reportService.findAll(); }

    @GetMapping("/reports/{id}")
    public ComplianceReport getReport(@PathVariable Long id) { return reportService.findById(id); }

    @PostMapping("/reports")
    public ComplianceReport createReport(@RequestBody ComplianceReport report) { return reportService.save(report); }
}
