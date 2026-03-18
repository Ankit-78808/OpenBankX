package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.ConsentEvent;
import com.cts.openbankx.service.ConsentEventService;

@RestController
@RequestMapping("/api/v1/consent-events")
public class ConsentEventController {
	
	@Autowired
	private ConsentEventService consentEventService;
	
//	//Create Event
//	@PostMapping("/{consentId}")
//	public ConsentEvent createEvent(@PathVariable Long consentId) {
//		return consentEventService.createEvent(consentId, EventType.CREATE,"Manual Event");
//	}
	
	//Get all events
	@GetMapping("/{consentId}")
	public List<ConsentEvent> getAllEvents(@PathVariable Long consentId){
		return consentEventService.getEventsByConsent(consentId);
	}
}
