package com.cts.openbankx.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="APILOG")
public class ApiGateWay 
{
	@Id
	@Column(nullable=false)
	private Long APILogID;
	
	@Column(nullable = false)
	private Long TPPAppID;
	
	@Column(nullable = false)
	private Long ClientID;
	
	@Column(nullable = false,length = 255)
	private String Endpoint;
	
	@Column(nullable = false,length=20)
	private String Method;
	
	@Column(nullable = false)
	private Integer StatusCode;
	
	@Column(nullable = false)
	private Long LatencyMS;
	
	@Column(nullable = false)
	private LocalDateTime Timestamp;

	public Long getAPILogID() {
		return APILogID;
	}

	public void setAPILogID(Long aPILogID) {
		APILogID = aPILogID;
	}

	public Long getTPPAppID() {
		return TPPAppID;
	}

	public void setTPPAppID(Long tPPAppID) {
		TPPAppID = tPPAppID;
	}

	public Long getClientID() {
		return ClientID;
	}

	public void setClientID(Long clientID) {
		ClientID = clientID;
	}

	public String getEndpoint() {
		return Endpoint;
	}

	public void setEndpoint(String endpoint) {
		Endpoint = endpoint;
	}

	public String getMethod() {
		return Method;
	}

	public void setMethod(String method) {
		Method = method;
	}

	public Integer getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(Integer statusCode) {
		StatusCode = statusCode;
	}

	public Long getLatencyMS() {
		return LatencyMS;
	}

	public void setLatencyMS(Long latencyMS) {
		LatencyMS = latencyMS;
	}

	public LocalDateTime getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		Timestamp = timestamp;
	}
	
	

}
