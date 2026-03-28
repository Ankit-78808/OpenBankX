package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cts.openbankx.enums.ActorType;
import com.cts.openbankx.model.AuditTrail;
import com.cts.openbankx.service.AuditTrailService;

@RestController
@RequestMapping("/api/audit-trails")
@PreAuthorize("hasRole('ADMIN')")
public class AuditTrailController {

    private final AuditTrailService auditTrailService;

    public AuditTrailController(AuditTrailService auditTrailService) {
        this.auditTrailService = auditTrailService;
    }

    /**
     * Get all audit trail records
     * GET /api/audit-trails
     */
    @GetMapping
    public ResponseEntity<List<AuditTrail>> getAllAuditTrails() {
        return ResponseEntity.ok(auditTrailService.findAll());
    }

    /**
     * Create a new audit log entry
     * POST /api/audit-trails
     */
    @PostMapping
    public ResponseEntity<AuditTrail> createAuditTrail(@RequestBody AuditTrail auditTrail) {
        AuditTrail saved = auditTrailService.save(auditTrail);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Log an audit action (explicit logging API)
     * POST /api/audit-trails/log
     */
    @PostMapping("/log")
    public ResponseEntity<AuditTrail> logAction(
            @RequestParam ActorType actorType,
            @RequestParam String actorId,
            @RequestParam String action,
            @RequestParam String resource,
            @RequestParam(required = false) String metadata) {

        AuditTrail audit = auditTrailService.log(
                actorType, actorId, action, resource, metadata
        );
        return new ResponseEntity<>(audit, HttpStatus.CREATED);
    }

    /**
     * Get audit logs by actor ID
     * GET /api/audit-trails/actor/{actorId}
     */
    @GetMapping("/actor/{actorId}")
    public ResponseEntity<List<AuditTrail>> getByActor(@PathVariable String actorId) {
        return ResponseEntity.ok(auditTrailService.findByActor(actorId));
    }

    /**
     * Get audit logs by resource
     * GET /api/audit-trails/resource/{resource}
     */
    @GetMapping("/resource/{resource}")
    public ResponseEntity<List<AuditTrail>> getByResource(@PathVariable String resource) {
        return ResponseEntity.ok(auditTrailService.findByResource(resource));
    }
}