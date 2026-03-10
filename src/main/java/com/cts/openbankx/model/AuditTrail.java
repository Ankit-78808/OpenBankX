package com.cts.openbankx.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_trails")
public class AuditTrail {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "trail_id", columnDefinition = "BINARY(16)")
	private UUID trailId;

	@Column(name = "actor_type", nullable = false)
	private String actorType; // User / TPP / System

	@Column(name = "actor_id", columnDefinition = "BINARY(16)")
	private UUID actorId;

	@Column(name = "action", nullable = false)
	private String action;

	@Column(name = "resource")
	private String resource;

	@Column(name = "timestamp", nullable = false)
	private Instant timestamp; // precise UTC timestamp

	@Column(name = "metadata", columnDefinition = "TEXT")
	private String metadata; // JSON or key-value string

	// Getters and setters
	public UUID getTrailId() {
		return trailId;
	}

	public void setTrailId(UUID trailId) {
		this.trailId = trailId;
	}

	public String getActorType() {
		return actorType;
	}

	public void setActorType(String actorType) {
		this.actorType = actorType;
	}

	public UUID getActorId() {
		return actorId;
	}

	public void setActorId(UUID actorId) {
		this.actorId = actorId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
}
