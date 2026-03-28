package com.cts.openbankx.model;

import com.cts.openbankx.enums.SCAMethod;
import com.cts.openbankx.enums.SCAResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sca_event")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SCAEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scaEventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SCAMethod method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SCAResult result;

    @Column(nullable = false)
    private LocalDateTime eventTime;

    private String referenceId;

    public SCAEvent() {}

    public SCAEvent(Long scaEventId, User user, SCAMethod method, SCAResult result, LocalDateTime eventTime, String referenceId) {
        this.scaEventId = scaEventId;
        this.user = user;
        this.method = method;
        this.result = result;
        this.eventTime = eventTime;
        this.referenceId = referenceId;
    }

    public Long getScaEventId() { return scaEventId; }
    public void setScaEventId(Long scaEventId) { this.scaEventId = scaEventId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public SCAMethod getMethod() { return method; }
    public void setMethod(SCAMethod method) { this.method = method; }

    public SCAResult getResult() { return result; }
    public void setResult(SCAResult result) { this.result = result; }

    public LocalDateTime getEventTime() { return eventTime; }
    public void setEventTime(LocalDateTime eventTime) { this.eventTime = eventTime; }

    public String getReferenceId() { return referenceId; }
    public void setReferenceId(String referenceId) { this.referenceId = referenceId; }
}
