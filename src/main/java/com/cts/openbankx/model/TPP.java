package com.cts.openbankx.model;

import com.cts.openbankx.enums.TPPStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TPP {
	
		
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "TPPID")
	 private Long tPPID;
	 
	 
	 @Column(name = "LegalName", nullable = false, length = 200)
	 private String legalName;
	 @Column(name = "RegistrationNumber", nullable = false, length = 100)
	 private String registrationNumber;
	 @Column(name = "ContactInfo", length = 255)
	 private String contactInfo;
	 @Column(name = "CertificationRef", length = 255)
	 private String certificationRef;
	 @Enumerated(EnumType.STRING)
	 @Column(name = "Status", nullable = false, length = 20)
	 private TPPStatus status;
	 public Long gettPPID() {
		 return tPPID;
	 }
	 public void settPPID(Long tPPID) {
		 this.tPPID = tPPID;
	 }
	 public String getLegalName() {
		 return legalName;
	 }
	 public void setLegalName(String legalName) {
		 this.legalName = legalName;
	 }
	 public String getRegistrationNumber() {
		 return registrationNumber;
	 }
	 public void setRegistrationNumber(String registrationNumber) {
		 this.registrationNumber = registrationNumber;
	 }
	 public String getContactInfo() {
		 return contactInfo;
	 }
	 public void setContactInfo(String contactInfo) {
		 this.contactInfo = contactInfo;
	 }
	 public String getCertificationRef() {
		 return certificationRef;
	 }
	 public void setCertificationRef(String certificationRef) {
		 this.certificationRef = certificationRef;
	 }
	 public TPPStatus getStatus() {
		 return status;
	 }
	 public void setStatus(TPPStatus status) {
		 this.status = status;
	 }
	 
	 
	 
	 

}
