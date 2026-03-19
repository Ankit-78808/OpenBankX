package com.cts.openbankx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.dto.TokenRequest;
import com.cts.openbankx.dto.TokenResponse;
import com.cts.openbankx.service.AuthClientService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthClientController{
	
	@Autowired
	private AuthClientService authClientService;
	
	@PostMapping("/token")
	public ResponseEntity<TokenResponse> getToken(@RequestBody TokenRequest request){
		TokenResponse response= authClientService.generateToken(request);
		
		return ResponseEntity.ok(response);
	}
	
	
}