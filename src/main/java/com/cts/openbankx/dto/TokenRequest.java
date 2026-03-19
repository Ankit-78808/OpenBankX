package com.cts.openbankx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TokenRequest {
	@JsonProperty("client_id")
	private Long client_id;
	private String redirect_uri;

}
