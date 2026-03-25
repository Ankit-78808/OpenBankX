package com.cts.openbankx.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import com.cts.openbankx.enums.ReportScopeKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class APIUsageReport {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name = "ReportID")
	   private Long reportID;

	    @Column(name = "Scope")
	    @Enumerated(EnumType.STRING)
	    private ReportScopeKey scope;

	   
	    @Column(name = "Metrics")
	    private String metrics;
	    
	    @CreationTimestamp
	    @Column(name = "GeneratedDate", nullable = false)
	    private Instant generatedDate;

		public Long getReportID() {
			return reportID;
		}

		public void setReportID(Long reportID) {
			this.reportID = reportID;
		}

		public ReportScopeKey getScope() {
			return scope;
		}

		public void setScope(ReportScopeKey scope) {
			this.scope = scope;
		}

		public String getMetrics() {
			return metrics;
		}

		public void setMetrics(String metrics) {
			this.metrics = metrics;
		}

		public Instant getGeneratedDate() {
			return generatedDate;
		}

		public void setGeneratedDate(Instant generatedDate) {
			this.generatedDate = generatedDate;
		}

		
	    
	    

}
