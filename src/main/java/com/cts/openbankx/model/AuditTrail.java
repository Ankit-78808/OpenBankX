package com.cts.openbankx.model;

import java.time.LocalDate;
import java.util.UUID;
import org.hibernate.type.SqlTypes;
import com.cts.openbankx.enums.ActorType;
import org.hibernate.annotations.JdbcTypeCode;
import jakarta.persistence.*;

@Entity
@Table(name = "audit_trails")
public class AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "trail_id")
    private UUID trailId;
    
    public UUID getTrailId() {
		return trailId;
	}

	public void setTrailId(UUID trailId) {
		this.trailId = trailId;
	}

	public ActorType getActorType() {
		return actorType;
	}

	public void setActorType(ActorType actorType) {
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

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	@Enumerated(EnumType.STRING)
    @Column(name = "actor_type", nullable = false)
    private ActorType actorType; 

    @Column(name = "actor_id")
    private UUID actorId;

    @Column(name = "action", nullable = false, length=200)
    private String action;

    @Column(name = "resource", nullable=false, length=300)
    private String resource;

    @Column(name = "timestamp", nullable = false)
    private LocalDate timestamp; 

    @JdbcTypeCode(SqlTypes.JSON) // Replaces columnDefinition="JSON"
    @Column(name = "metadata")
    private String metadata;
    
    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDate.now();
    }
}