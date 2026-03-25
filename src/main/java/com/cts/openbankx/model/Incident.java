package com.cts.openbankx.model;

import java.time.Instant;

import com.cts.openbankx.enums.IncidentCategory;
import com.cts.openbankx.enums.IncidentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Incident {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IncidentID")
    private Long incidentID;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "Category", nullable = false, length = 20)
    private IncidentCategory category;
	
	 @Column(name = "Description", nullable = false, length = 2000)
	 private String description;
	 

	 @Column(name = "DetectedDate", nullable = false)
	 private Instant detectedDate;

	 
	 @Enumerated(EnumType.STRING)
	 @Column(name = "Status", nullable = false, length = 20)
	 private IncidentStatus status;


	 public Long getIncidentID() {
		 return incidentID;
	 }


	 public void setIncidentID(Long incidentID) {
		 this.incidentID = incidentID;
	 }


	 public IncidentCategory getCategory() {
		 return category;
	 }


	 public void setCategory(IncidentCategory category) {
		 this.category = category;
	 }


	 public String getDescription() {
		 return description;
	 }


	 public void setDescription(String description) {
		 this.description = description;
	 }


	 public Instant getDetectedDate() {
		 return detectedDate;
	 }


	 public void setDetectedDate(Instant detectedDate) {
		 this.detectedDate = detectedDate;
	 }


	 public IncidentStatus getStatus() {
		 return status;
	 }


	 public void setStatus(IncidentStatus status) {
		 this.status = status;
	 }

	
	 
	 
	 
	 
	 
	 

}
