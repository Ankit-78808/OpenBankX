package com.cts.openbankx.model;

import com.cts.openbankx.enums.TPPStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class TPP {
	
		
	 @Id
	 @Column(name = "TPPID")
	 private Long TPPID;
	 @Column(name = "LegalName", nullable = false, length = 200)
	 private String LegalName;
	 @Column(name = "RegistrationNumber", nullable = false, length = 100)
	 private String RegistrationNumber;
	 @Column(name = "ContactInfo", length = 255)
	 private String ContactInfo;
	 @Column(name = "CertificationRef", length = 255)
	 private String CertificationRef;
	 @Enumerated(EnumType.STRING)
	 @Column(name = "Status", nullable = false, length = 20)
	 private TPPStatus Status;
	 public Long getTPPID() {
		 return TPPID;
	 }
	 public void setTPPID(Long tPPID) {
		 TPPID = tPPID;
	 }
	 public String getLegalName() {
		 return LegalName;
	 }
	 public void setLegalName(String legalName) {
		 LegalName = legalName;
	 }
	 public String getRegistrationNumber() {
		 return RegistrationNumber;
	 }
	 public void setRegistrationNumber(String registrationNumber) {
		 RegistrationNumber = registrationNumber;
	 }
	 public String getContactInfo() {
		 return ContactInfo;
	 }
	 public void setContactInfo(String contactInfo) {
		 ContactInfo = contactInfo;
	 }
	 public String getCertificationRef() {
		 return CertificationRef;
	 }
	 public void setCertificationRef(String certificationRef) {
		 CertificationRef = certificationRef;
	 }
	 public TPPStatus getTppstatus() {
		 return Status;
	 }
	 public void setTppstatus(TPPStatus tppstatus) {
		 this.Status = tppstatus;
	 }
	 
	 

}
