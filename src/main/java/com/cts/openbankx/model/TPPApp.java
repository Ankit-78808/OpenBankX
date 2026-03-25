package com.cts.openbankx.model;

import com.cts.openbankx.enums.TPPAppStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;


@Entity
public class TPPApp {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "TPPAppID")
	    private Long tppAppID;
		
//		@Column(name = "TPPID", nullable = false)
//	    private Long tppID;
		
		
		 @Column(name = "AppName", nullable = false)
		 private String appName;
		 @Column(name = "RedirectURIs")
		 private String redirectURIs;
		 @Column(name = "PublicKeysJWKSet")
		 private String publicKeysJWKSet;
		 @Column(name = "ScopesRequested")
		 private String scopesRequested;
		 
		 @Enumerated(EnumType.STRING)
		 private TPPAppStatus status;
		 
		 @ManyToOne
		 @JoinColumn(name = "TPPID", nullable = false,foreignKey = @ForeignKey(name = "fk_tppapp_tpp"))
		 @JsonIgnore
		 private TPP tpp;
		 
		 
		 
		 public TPP getTpp() {
			return tpp;
		}

		 public void setTpp(TPP tpp) {
			 this.tpp = tpp;
		 }

		 public Long getTppAppID() {
			return tppAppID;
		}

		 public void setTppAppID(Long tppAppID) {
			 this.tppAppID = tppAppID;
		 }

//		 public Long getTppID() {
//			 return tppID;
//		 }
//
//		 public void setTppID(Long tppID) {
//			 this.tppID = tppID;
//		 }

		 public String getAppName() {
			 return appName;
		 }

		 public void setAppName(String appName) {
			 this.appName = appName;
		 }

		 public String getRedirectURIs() {
			 return redirectURIs;
		 }

		 public void setRedirectURIs(String redirectURIs) {
			 this.redirectURIs = redirectURIs;
		 }

		 public String getPublicKeysJWKSet() {
			 return publicKeysJWKSet;
		 }

		 public void setPublicKeysJWKSet(String publicKeysJWKSet) {
			 this.publicKeysJWKSet = publicKeysJWKSet;
		 }

		 public String getScopesRequested() {
			 return scopesRequested;
		 }

		 public void setScopesRequested(String scopesRequested) {
			 this.scopesRequested = scopesRequested;
		 }

		 public TPPAppStatus getStatus() {
			 return status;
		 }

		 public void setStatus(TPPAppStatus status) {
			 this.status = status;
		 }

		 		 
		 

}
