package com.cts.openbankx.dto;

public class ApiGatewayDTO {
	
	private Long TPPAppID;  //primary key from TPPApp 
	private Long clientId; //primary key from AuthClient 
	private String endPoint;
	private String method;
	private Integer statusCode;
	private Long latencyMs;
	
	
	public Long getTPPAppID() {
		return TPPAppID;
	}
	public void setTPPAppID(Long tPPAppID) {
		TPPAppID = tPPAppID;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
	
	
	 
}
