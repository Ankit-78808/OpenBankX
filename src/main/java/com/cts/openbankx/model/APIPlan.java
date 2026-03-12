package com.cts.openbankx.model;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "api_plans")
public class APIPlan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID planId;

    // Mapping as a simple ID instead of a Foreign Key relationship
    @Column(name = "product_id")
    private UUID productId;

    private String environment; 
    private int rateLimitPerMin;
    private int dailyQuota;
    private int sla; 

    // Getters and Setters
    public UUID getPlanId() { return planId; }
    public void setPlanId(UUID planId) { this.planId = planId; }
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }
    public int getRateLimitPerMin() { return rateLimitPerMin; }
    public void setRateLimitPerMin(int rateLimitPerMin) { this.rateLimitPerMin = rateLimitPerMin; }
    public int getDailyQuota() { return dailyQuota; }
    public void setDailyQuota(int dailyQuota) { this.dailyQuota = dailyQuota; }
    public int getSla() { return sla; }
    public void setSla(int sla) { this.sla = sla; }
}