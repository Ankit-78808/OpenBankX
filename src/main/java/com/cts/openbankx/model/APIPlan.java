package com.cts.openbankx.model;



import java.util.UUID;

import com.cts.openbankx.enums.PlanEnvironment;
import com.cts.openbankx.enums.ProductStatus;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="api_plan")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
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

    
   
  
}
