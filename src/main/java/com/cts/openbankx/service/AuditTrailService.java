package com.cts.openbankx.service;

import com.cts.openbankx.model.AuditTrail;
import com.cts.openbankx.repository.AuditTrailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuditTrailService {

    private final AuditTrailRepository repository;

    public AuditTrailService(AuditTrailRepository repository) {
        this.repository = repository;
    }

    public AuditTrail createAuditTrail(AuditTrail trail) {
        return repository.save(trail);
    }

    public List<AuditTrail> getAllAuditTrails() {
        return repository.findAll();
    }

    public AuditTrail getAuditTrailById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<AuditTrail> getAuditTrailsByActorType(String actorType) {
        return repository.findByActorType(actorType);
    }
}
