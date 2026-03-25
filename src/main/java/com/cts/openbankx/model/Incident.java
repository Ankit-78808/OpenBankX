package com.cts.openbankx.model;

import java.time.Instant;

import com.cts.openbankx.enums.IncidentCategory;
import com.cts.openbankx.enums.IncidentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Incident {
	
	@Id
	@Column(name = "IncidentID")
    private Long IncidentID;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "Category", nullable = false, length = 20)
    private IncidentCategory Category;
	
	 @Column(name = "Description", nullable = false, length = 2000)
	 private String Description;
	 

	 @Column(name = "DetectedDate", nullable = false)
	 private Instant detectedDate;

	 
	 @Enumerated(EnumType.STRING)
	 @Column(name = "Status", nullable = false, length = 20)
	 private IncidentStatus Status;

	 public Long getIncidentID() {
		 return IncidentID;
	 }

	 public void setIncidentID(Long incidentID) {
		 IncidentID = incidentID;
	 }

	 public Instant getDetectedDate() {
		return detectedDate;
	}

	 public void setDetectedDate(Instant detectedDate) {
		 this.detectedDate = detectedDate;
	 }

	 public IncidentCategory getCategory() {
		 return Category;
	 }

	 public void setCategory(IncidentCategory category) {
		 Category = category;
	 }

	 public String getDescription() {
		 return Description;
	 }

	 public void setDescription(String description) {
		 Description = description;
	 }

	 public IncidentStatus getStatus() {
		 return Status;
	 }

	 public void setStatus(IncidentStatus status) {
		 Status = status;
	 }
	 
	 
	 
	 
	 
	 

}
