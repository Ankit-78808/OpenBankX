package com.cts.openbankx.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="APILOG")
public class ApiGateWay 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="APILogID",nullable=false)
	private Long apiLogID;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="TPPAppID",
	nullable=false,
	foreignKey=@ForeignKey(name="fk_APILOG_TPPApp"))
	@JsonIgnore
	private TPPApp tppApp;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClientID",
	nullable=false,
	foreignKey=@ForeignKey(name="fk_APILOG_AuthClient"))
	@JsonIgnore
	private AuthClient authclient;

	@Column(name="EndPoint",nullable = false,length = 255)
	private String endPoint;
	
	@Column(name="Method",nullable = false,length=20)
	private String method;
	

	@Column(name="StatusCode",nullable = false)
	private Integer statusCode;
	
	@Column(name="LatencyMS",nullable = false)
	private Long latencyMs;
	
	@Column(name="TimeStamp",nullable = false)
	private LocalDateTime timestamp;
	
	
	

	public Long getApiLogID() {
		return apiLogID;
	}

	public void setApiLogID(Long apiLogID) {
		this.apiLogID = apiLogID;
	}

	public TPPApp getTppApp() {
		return tppApp;
	}

	public void setTppApp(TPPApp tppApp) {
		this.tppApp = tppApp;
	}

	public AuthClient getAuthclient() {
		return authclient;
	}

	public void setAuthclient(AuthClient authclient) {
		this.authclient = authclient;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Long getLatencyMs() {
		return latencyMs;
	}

	public void setLatencyMs(Long latencyMs) {
		this.latencyMs = latencyMs;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
	
	
	
	
	
	

}
