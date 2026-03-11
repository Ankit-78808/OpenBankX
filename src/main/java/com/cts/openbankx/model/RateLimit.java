package com.cts.openbankx.model;

import java.time.LocalDateTime;

import com.cts.openbankx.enums.LimitWindow;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="RateLimitCounter")
public class RateLimit 
{
	@Id
	@Column(nullable = false)
	private Long CounterID;
	
	@Column(nullable = false)
	private Long TPPAppID;
	
	@Column(nullable = false)
	private LocalDateTime PeriodStart;
	
	@Column(nullable = false)
	private Integer Count;
	
	@Enumerated(EnumType.STRING)
	private LimitWindow limitWindow;

	public Long getCounterID() {
		return CounterID;
	}

	public void setCounterID(Long counterID) {
		CounterID = counterID;
	}

	public Long getTPPAppID() {
		return TPPAppID;
	}

	public void setTPPAppID(Long tPPAppID) {
		TPPAppID = tPPAppID;
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
