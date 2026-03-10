package com.cts.openbankx.model;

import com.cts.openbankx.enums.TPPAppStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class TPPApp {
		
		@Id
		@Column(name = "TPPAppID")
	    private Long TPPAppID;
		
		@Column(name = "TPPID", nullable = false)
	    private Long TPPID;
		 @Column(name = "AppName", nullable = false)
		 private String AppName;
		 @Column(name = "RedirectURIs")
		 private String RedirectURIs;
		 @Column(name = "PublicKeysJWKSet")
		 private String PublicKeysJWKSet;
		 @Column(name = "ScopesRequested")
		 private String ScopesRequested;
		 
		 @Enumerated(EnumType.STRING)
		 private TPPAppStatus Status;

		 public Long getTPPAppID() {
			 return TPPAppID;
		 }

		 public void setTPPAppID(Long tPPAppID) {
			 TPPAppID = tPPAppID;
		 }

		 public Long getTPPID() {
			 return TPPID;
		 }

		 public void setTPPID(Long tPPID) {
			 TPPID = tPPID;
		 }

		 public String getAppName() {
			 return AppName;
		 }

		 public void setAppName(String appName) {
			 AppName = appName;
		 }

		 public String getRedirectURIs() {
			 return RedirectURIs;
		 }

		 public void setRedirectURIs(String redirectURIs) {
			 RedirectURIs = redirectURIs;
		 }

		 public String getPublicKeysJWKSet() {
			 return PublicKeysJWKSet;
		 }

		 public void setPublicKeysJWKSet(String publicKeysJWKSet) {
			 PublicKeysJWKSet = publicKeysJWKSet;
		 }

		 public String getScopesRequested() {
			 return ScopesRequested;
		 }

		 public void setScopesRequested(String scopesRequested) {
			 ScopesRequested = scopesRequested;
		 }

		 public TPPAppStatus getStatus() {
			 return Status;
		 }

		 public void setStatus(TPPAppStatus s) {
			 Status = s;
		 }
		 
		 

}
