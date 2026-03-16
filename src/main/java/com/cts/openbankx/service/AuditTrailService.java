package com.cts.openbankx.service;

import com.cts.openbankx.model.AuditTrail;
import com.cts.openbankx.repository.AuditTrailRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditTrailService {
	
	@Autowired
	private final AuditTrailRepository repository;


    public AuditTrailService(AuditTrailRepository repository) {
        this.repository = repository;
    }
    
    public AuditTrail createAuditTrail(AuditTrail auditTrail) {
    	return repository.save(auditTrail);
    }
    
    @Transactional(readOnly=true)
    public List<AuditTrail> getAllAuditTrail(){
    	return repository.findAll();
    }
    
    @Transactional(readOnly = true)
    public AuditTrail getAuditByTrailId(UUID trailid) {
        return repository.findById(trailid)
                .orElseThrow(() -> new RuntimeException("AuditTrail not Found: " + trailid));
    }
    
    @Transactional(readOnly=true)
    public List<AuditTrail> getAuditByActor(UUID actorid ) {
    	return repository.findByActorId(actorid);
    }
    
    @Transactional(readOnly=true)
    public List<AuditTrail> getAuditByActorType(String actortype ) {
    	return repository.findByActorType(actortype);
    }
    
    @Transactional(readOnly=true)
    public List<AuditTrail> getAuditByTimeStamp(Instant timestamp) {
    	return repository.findByTimestamp(timestamp);
    }


    
}
