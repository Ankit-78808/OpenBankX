package com.cts.openbankx.dto;

import com.cts.openbankx.model.Token.TokenType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {
	private String access_token;
	private TokenType token_type;
	private int expires_in;
	private String scope;

}
