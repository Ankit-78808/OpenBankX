package com.cts.openbankx.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import com.cts.openbankx.enums.PlanEnvironment;
import com.cts.openbankx.enums.SLATier;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tpp_subscriptions")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
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


}
