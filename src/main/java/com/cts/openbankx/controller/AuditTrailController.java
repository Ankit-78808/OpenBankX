package com.cts.openbankx.controller;

import com.cts.openbankx.model.AuditTrail;
import com.cts.openbankx.service.AuditTrailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/audit-trails")
public class AuditTrailController {

    private final AuditTrailService service;

    public AuditTrailController(AuditTrailService service) {
        this.service = service;
    }

    // Create new audit trail
    @PostMapping
    public AuditTrail createAuditTrail(@RequestBody AuditTrail trail) {
        return service.createAuditTrail(trail);
    }

    // Fetch all audit trails
    @GetMapping
    public List<AuditTrail> getAllAuditTrails() {
        return service.getAllAuditTrails();
    }

    // Fetch audit trail by ID
    @GetMapping("/{id}")
    public AuditTrail getAuditTrailById(@PathVariable UUID id) {
        return service.getAuditTrailById(id);
    }

    // Fetch audit trails by actor type
    @GetMapping("/actor/{actorType}")
    public List<AuditTrail> getAuditTrailsByActorType(@PathVariable String actorType) {
        return service.getAuditTrailsByActorType(actorType);
    }
}
