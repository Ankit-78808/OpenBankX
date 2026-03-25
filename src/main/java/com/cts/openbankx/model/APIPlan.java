package com.cts.openbankx.model;




import com.cts.openbankx.enums.PlanEnvironment;
import com.cts.openbankx.enums.SLATier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="api_plan")
public class APIPlan {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id",nullable = false)
    private APIProduct product;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "environment",nullable = false)
    private PlanEnvironment environment; // Enum: SANDBOX, PRODUCTION
   
    @Column(name = "rateLimit_per_min",nullable = false)
    private Integer rateLimitPerMin;
    
    @Column(name = "daily_Quota",nullable = false)
    private Long dailyQuota;

 

	@Enumerated(EnumType.STRING)
    private SLATier sla; // Enum for the SLA levels
	
    public Long getPlanId() {
		return planId;
	}



	public void setPlanId(Long planId) {
		this.planId = planId;
	}



	public APIProduct getProduct() {
		return product;
	}



	public void setProduct(APIProduct product) {
		this.product = product;
	}



	public PlanEnvironment getEnvironment() {
		return environment;
	}



	public void setEnvironment(PlanEnvironment environment) {
		this.environment = environment;
	}



	public Integer getRateLimitPerMin() {
		return rateLimitPerMin;
	}



	public void setRateLimitPerMin(Integer rateLimitPerMin) {
		this.rateLimitPerMin = rateLimitPerMin;
	}



	public Long getDailyQuota() {
		return dailyQuota;
	}



	public void setDailyQuota(Long dailyQuota) {
		this.dailyQuota = dailyQuota;
	}



	public SLATier getSla() {
		return sla;
	}



	public void setSla(SLATier sla) {
		this.sla = sla;
	}
  
}
