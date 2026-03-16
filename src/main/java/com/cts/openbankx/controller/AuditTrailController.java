package com.cts.openbankx.controller;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.AuditTrail;
import com.cts.openbankx.service.AuditTrailService;

@RestController
@RequestMapping("/api/v1/audit")
public class AuditTrailController{
	
	
	private  AuditTrailService auditTrailService;
	
	@PostMapping
	public ResponseEntity<AuditTrail> create(@RequestBody AuditTrail auditTrail){
		return ResponseEntity.status(HttpStatus.CREATED).body(auditTrailService.createAuditTrail(auditTrail));
	}
	
	@GetMapping("/{trailid}")
	public ResponseEntity<AuditTrail> getByid(@PathVariable UUID trailid){
		return ResponseEntity.ok(auditTrailService.getAuditByTrailId(trailid));
	}
	
	@GetMapping("/actor/{actorid}")
	public ResponseEntity<List<AuditTrail>> getByActor(@PathVariable UUID actorid){
		return ResponseEntity.ok(auditTrailService.getAuditByActor(actorid));
	}
	
	@GetMapping("/actor-type/{actortype}")
	public ResponseEntity<List<AuditTrail>> getByActorType(@PathVariable String actortype){
		return ResponseEntity.ok(auditTrailService.getAuditByActorType(actortype));
	}
	
	@GetMapping("/time-stamp/{timestamp}")
	public ResponseEntity<List<AuditTrail>> getByTimeStamp(@PathVariable Instant timestamp){
		return ResponseEntity.ok(auditTrailService.getAuditByTimeStamp(timestamp));
	}
	
}