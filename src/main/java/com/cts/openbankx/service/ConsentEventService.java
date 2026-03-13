package com.cts.openbankx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.openbankx.model.ConsentEvent;
import com.cts.openbankx.repository.ConsentEventRepository;

@Service
public class ConsentEventService {
	
	@Autowired
	private ConsentEventRepository consentEventRepo;
	
	private ConsentEvent createEvent(ConsentEvent event) {
		return consentEventRepo.save(event);
	}
	
	public List<ConsentEvent> getAllEvents(){
		return consentEventRepo.findAll();
	}
}
