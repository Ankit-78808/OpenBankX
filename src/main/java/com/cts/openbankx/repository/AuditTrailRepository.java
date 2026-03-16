package com.cts.openbankx.repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.AuditTrail;

public interface AuditTrailRepository extends JpaRepository<AuditTrail, UUID> {

	List<AuditTrail> findByActorType(String actorType);
	List<AuditTrail> findByActorId(UUID actorId);
	List<AuditTrail> findByTimestamp(Instant timestamp);
	List<AuditTrail> findByResource(String resource);

}
