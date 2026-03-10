package com.cts.openbankx.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import com.cts.openbankx.enums.ReportScopeKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class APIUsageReport {
	
	   @Id
	   @Column(name = "ReportID")
	   private Long ReportID;

	    @Column(name = "Scope")
	    @Enumerated(EnumType.STRING)
	    private ReportScopeKey Scope;

	   
	    @Column(name = "Metrics")
	    private String Metrics;
	    
	    @CreationTimestamp
	    @Column(name = "GeneratedDate", nullable = false)
	    private Instant generatedDate;

		public Long getReportID() {
			return ReportID;
		}

		public void setReportID(Long reportID) {
			ReportID = reportID;
		}

		public ReportScopeKey getScope() {
			return Scope;
		}

		public void setScope(ReportScopeKey scope) {
			Scope = scope;
		}

		public String getMetrics() {
			return Metrics;
		}

		public void setMetrics(String metrics) {
			Metrics = metrics;
		}

		public Instant getGeneratedDate() {
			return generatedDate;
		}

		public void setGeneratedDate(Instant generatedDate) {
			this.generatedDate = generatedDate;
		}
	    
	    
	    

}
