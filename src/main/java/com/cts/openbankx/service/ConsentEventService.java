package com.cts.openbankx.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.openbankx.enums.EventType;
import com.cts.openbankx.enums.PerformedBy;
import com.cts.openbankx.model.Consent;
import com.cts.openbankx.model.ConsentEvent;
import com.cts.openbankx.repository.ConsentEventRepository;
import com.cts.openbankx.repository.ConsentRepository;

@Service
public class ConsentEventService {
	
	@Autowired
	private ConsentEventRepository consentEventRepo;
	
	@Autowired
	private ConsentRepository consentRepo;
	
//	// CREATE EVENT
//    public ConsentEvent createEvent(Long consentId, EventType type, String notes) {
// 
//        Consent consent = consentRepo.findById(consentId)
//                .orElseThrow(() -> new RuntimeException("Consent not found"));
// 
//        ConsentEvent event = new ConsentEvent();
//        event.setConsent(consent);
//        event.setEventType(type);
//        event.setEventDate(LocalDateTime.now());
//        event.setPerformedBy(PerformedBy.USER);
//        event.setNotes(notes);
// 
//        return consentEventRepo.save(event);
//    }
 
    // GET EVENTS BY CONSENT
    public List<ConsentEvent> getEventsByConsent(Long consentId) {
        return consentEventRepo.findByConsentConsentId(consentId);
    }
	
}
