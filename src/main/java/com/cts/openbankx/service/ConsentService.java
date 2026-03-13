package com.cts.openbankx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.openbankx.model.Consent;
import com.cts.openbankx.repository.ConsentRepository;

@Service
public class ConsentService {
	
	@Autowired
	private ConsentRepository consentRepo;
	
	public Consent createConsent(Consent consent) {
		return consentRepo.save(consent);
	}
	
	public List<Consent> getAllConsents(){
		return consentRepo.findAll();
	}
	
	public Consent getConsentById(Long id) {
		return consentRepo.findById(id).orElse(null);
	}
	
	public void deleteConsent(Long id) {
		consentRepo.deleteById(id);
	}
}
