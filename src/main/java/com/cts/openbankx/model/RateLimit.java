package com.cts.openbankx.model;


import java.time.LocalDateTime;

import com.cts.openbankx.enums.LimitWindow;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="RateLimitCounter")
public class RateLimit 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	@JsonProperty("CounterID")
	private Long CounterID;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TPPAppID",
	nullable=false,
	foreignKey=@ForeignKey(name="fk_RateLimitCounter_TPPApp"))
	private TPPApp tppApp;
	
	@Column(nullable = false)
	@JsonProperty("PeriodStart")
	private LocalDateTime PeriodStart;
	
	@Column(nullable = false)
	@JsonProperty("Count")
	private Integer Count;
	
	@Enumerated(EnumType.STRING)
	private LimitWindow limitWindow;

	public Long getCounterID() {
		return CounterID;
	}

	public void setCounterID(Long counterID) {
		CounterID = counterID;
	}

	public TPPApp getTPPApp() {
		return tppApp;
	}

	public void setTPPApp(TPPApp tPPApp) {
		this.tppApp = tPPApp;
	}

	public LocalDateTime getPeriodStart() {
		return PeriodStart;
	}

	public void setPeriodStart(LocalDateTime periodStart) {
		PeriodStart = periodStart;
	}

	public Integer getCount() {
		return Count;
	}

	public void setCount(Integer count) {
		Count = count;
	}

	public LimitWindow getLimitWindow() {
		return limitWindow;
	}

	public void setLimitWindow(LimitWindow limitWindow) {
		this.limitWindow = limitWindow;
	}
	
	
	
	

}
