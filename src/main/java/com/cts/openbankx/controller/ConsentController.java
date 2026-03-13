package com.cts.openbankx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.Consent;
import com.cts.openbankx.service.ConsentService;

@RestController
@RequestMapping("/api/v1/consents")
public class ConsentController {
	
	@Autowired
	private ConsentService consentService;
	
	@PostMapping
	public Consent createConsent(@RequestBody Consent consent) {
		return consentService.createConsent(consent);
	}
	
}
