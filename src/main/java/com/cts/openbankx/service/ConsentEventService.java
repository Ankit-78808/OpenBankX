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
