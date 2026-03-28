package com.cts.openbankx.model;

import com.cts.openbankx.enums.Environment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "api_plan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class APIPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private APIProduct apiProduct;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Environment environment;

    @Column(nullable = false)
    private Integer rateLimitPerMin;

    @Column(nullable = false)
    private Integer dailyQuota;

    @Column(nullable = false)
    private Integer sla;

    public APIPlan() {}

    public APIPlan(Long planId, APIProduct apiProduct, Environment environment, Integer rateLimitPerMin, Integer dailyQuota, Integer sla) {
        this.planId = planId;
        this.apiProduct = apiProduct;
        this.environment = environment;
        this.rateLimitPerMin = rateLimitPerMin;
        this.dailyQuota = dailyQuota;
        this.sla = sla;
    }

    public Long getPlanId() { return planId; }
    public void setPlanId(Long planId) { this.planId = planId; }

    public APIProduct getApiProduct() { return apiProduct; }
    public void setApiProduct(APIProduct apiProduct) { this.apiProduct = apiProduct; }

    public Environment getEnvironment() { return environment; }
    public void setEnvironment(Environment environment) { this.environment = environment; }

    public Integer getRateLimitPerMin() { return rateLimitPerMin; }
    public void setRateLimitPerMin(Integer rateLimitPerMin) { this.rateLimitPerMin = rateLimitPerMin; }

    public Integer getDailyQuota() { return dailyQuota; }
    public void setDailyQuota(Integer dailyQuota) { this.dailyQuota = dailyQuota; }

    public Integer getSla() { return sla; }
    public void setSla(Integer sla) { this.sla = sla; }
}
