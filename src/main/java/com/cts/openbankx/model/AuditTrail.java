package com.cts.openbankx.model;

import java.time.LocalDate;
import java.util.UUID;
import java.sql.Types; 
import org.hibernate.type.SqlTypes;
import com.cts.openbankx.enums.ActorType;
import org.hibernate.annotations.JdbcTypeCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "audit_trails")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "trail_id")
    private UUID trailId;
    
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

    @JdbcTypeCode(SqlTypes.JSON) 
    @Column(name = "metadata")
    private String metadata;
    
    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDate.now();
    }
}