package com.cts.openbankx.model;

import java.time.LocalDate;
import java.util.UUID;

import com.cts.openbankx.enums.SubscriptionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tpp_subscriptions")
public class TPPSubscription {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.UUID)
	    private UUID subscriptionId;
	    
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name = "plan_id", nullable = false)
	    private APIPlan plan;
        
	    @Column(name="subscribed_date")
	    private LocalDate subscribedDate;
	    
		private SubscriptionStatus status; // Active/Suspended/Cancelled

	    
		private UUID tppAppId;

	    
		public TPPSubscription(UUID tppAppId, APIPlan plan, LocalDate subscribedDate, SubscriptionStatus status) {
			this.tppAppId = tppAppId;
			this.plan = plan;
			this.subscribedDate = subscribedDate;
			this.status = status;
		}
	    public UUID getSubscriptionId() {
			return subscriptionId;
		}


		public void setSubscriptionId(UUID subscriptionId) {
			this.subscriptionId = subscriptionId;
		}


		public APIPlan getPlan() {
			return plan;
		}


		public void setPlan(APIPlan plan) {
			this.plan = plan;
		}


		public LocalDate getSubscribedDate() {
			return subscribedDate;
		}


		public void setSubscribedDate(LocalDate subscribedDate) {
			this.subscribedDate = subscribedDate;
		}


		public SubscriptionStatus getStatus() {
			return status;
		}


		public void setStatus(SubscriptionStatus status) {
			this.status = status;
		}


		public UUID getTppAppId() {
			return tppAppId;
		}


		public void setTppAppId(UUID tppAppId) {
			this.tppAppId = tppAppId;
		}

}
