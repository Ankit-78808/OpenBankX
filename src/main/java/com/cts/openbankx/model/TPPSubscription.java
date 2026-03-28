package com.cts.openbankx.model;

import com.cts.openbankx.enums.SubscriptionStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tpp_subscription")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TPPSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tpp_app_id", nullable = false)
    private TPPApp tppApp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private APIPlan apiPlan;

    @Column(nullable = false)
    private LocalDateTime subscribedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status;

    public TPPSubscription() {}

    public TPPSubscription(Long subscriptionId, TPPApp tppApp, APIPlan apiPlan, LocalDateTime subscribedDate, SubscriptionStatus status) {
        this.subscriptionId = subscriptionId;
        this.tppApp = tppApp;
        this.apiPlan = apiPlan;
        this.subscribedDate = subscribedDate;
        this.status = status;
    }

    public Long getSubscriptionId() { return subscriptionId; }
    public void setSubscriptionId(Long subscriptionId) { this.subscriptionId = subscriptionId; }

    public TPPApp getTppApp() { return tppApp; }
    public void setTppApp(TPPApp tppApp) { this.tppApp = tppApp; }

    public APIPlan getApiPlan() { return apiPlan; }
    public void setApiPlan(APIPlan apiPlan) { this.apiPlan = apiPlan; }

    public LocalDateTime getSubscribedDate() { return subscribedDate; }
    public void setSubscribedDate(LocalDateTime subscribedDate) { this.subscribedDate = subscribedDate; }

    public SubscriptionStatus getStatus() { return status; }
    public void setStatus(SubscriptionStatus status) { this.status = status; }
}
