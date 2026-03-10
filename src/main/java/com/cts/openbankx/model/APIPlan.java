package com.cts.openbankx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_plans")
public class APIPlan {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID planId;

    public UUID getPlanId() {
		return planId;
	}

	public void setPlanId(UUID planId) {
		this.planId = planId;
	}

	public APIProduct getProduct() {
		return product;
	}

	public void setProduct(APIProduct product) {
		this.product = product;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public int getRateLimitPerMin() {
		return rateLimitPerMin;
	}

	public void setRateLimitPerMin(int rateLimitPerMin) {
		this.rateLimitPerMin = rateLimitPerMin;
	}

	public int getDailyQuota() {
		return dailyQuota;
	}

	public void setDailyQuota(int dailyQuota) {
		this.dailyQuota = dailyQuota;
	}

	public int getSla() {
		return sla;
	}

	public void setSla(int sla) {
		this.sla = sla;
	}

	public List<TPPSubscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<TPPSubscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private APIProduct product;

    private String environment; // Sandbox/Production
    private int rateLimitPerMin;
    private int dailyQuota;
    private int sla; // SLA in ms

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TPPSubscription> subscriptions = new ArrayList<>();

}
