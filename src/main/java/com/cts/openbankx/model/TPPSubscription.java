package com.cts.openbankx.model;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
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

	    public UUID getSubscriptionId() {
		return subscriptionId;
	}
	 public void setSubscriptionId(UUID subscriptionId) {
		 this.subscriptionId = subscriptionId;
	 }
	 public UUID getTppAppId() {
		 return tppAppId;
	 }
	 public void setTppAppId(UUID tppAppId) {
		 this.tppAppId = tppAppId;
	 }
	 public APIPlan getPlan() {
		 return plan;
	 }
	 public void setPlan(APIPlan plan) {
		 this.plan = plan;
	 }
	 public Date getSubscribedDate() {
		 return subscribedDate;
	 }
	 public void setSubscribedDate(Date subscribedDate) {
		 this.subscribedDate = subscribedDate;
	 }
	 public String getStatus() {
		 return status;
	 }
	 public void setStatus(String status) {
		 this.status = status;
	 }
		private UUID tppAppId;

	    @ManyToOne
	    @JoinColumn(name = "plan_id", nullable = false)
	    private APIPlan plan;

	    private Date subscribedDate;
	    private String status; // Active/Suspended/Cancelled


}
