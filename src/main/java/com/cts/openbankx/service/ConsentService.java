package com.cts.openbankx.service;
 
import java.time.LocalDateTime;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.openbankx.enums.ConsentStatus;
import com.cts.openbankx.enums.EventType;
import com.cts.openbankx.enums.PerformedBy;
import com.cts.openbankx.model.*;
import com.cts.openbankx.repository.*;
 
@Service
public class ConsentService {
 
    @Autowired
    private ConsentRepository consentRepo;
 
    @Autowired
    private UserRepository userRepo;
 
    @Autowired
    private TPPAppRepository tppRepo;
 
    @Autowired
    private ConsentEventRepository consentEventRepo;
 
    // CREATE CONSENT
    @Transactional
    public Consent createConsent(Consent consent) {
 
        // 1. Extract IDs from incoming request
        Long userId = consent.getUser().getUserId();
        Long tppAppId = consent.getTppApp().getTPPAppID();
 
        // 2. Fetch actual objects from DB
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
 
        TPPApp tppApp = tppRepo.findById(tppAppId)
                .orElseThrow(() -> new RuntimeException("TPP App not found"));
 
        // 3. Replace objects
        consent.setUser(user);
        consent.setTppApp(tppApp);
 
        // 4. Set system fields
        consent.setCreatedDate(LocalDateTime.now());
        consent.setStatus(ConsentStatus.ACTIVE);
 
        // 5. Save consent
        Consent savedConsent = consentRepo.save(consent);
 
        // 6. Automatically create event (BEST PRACTICE)
        ConsentEvent event = new ConsentEvent();
        event.setConsent(savedConsent);
        event.setEventType(EventType.CREATE);
        event.setEventDate(LocalDateTime.now());
        event.setPerformedBy(PerformedBy.USER);
        event.setNotes("Consent created");
 
        ConsentEvent savedEvent = consentEventRepo.save(event);
        savedConsent.setConsentEvents(List.of(savedEvent));

        return savedConsent;
    }
 
    // GET ALL
    public List<Consent> getAllConsents() {
        return consentRepo.findAll();
    }
 
    // GET BY ID
    public Consent getConsentById(Long id) {
        return consentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Consent not found"));
    }
 
    // REVOKE CONSENT
    @Transactional
    public Consent revokeConsent(Long id) {
 
        Consent consent = consentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Consent not found"));
 
        consent.setStatus(ConsentStatus.REVOKED);
 
        // Create event
        ConsentEvent event = new ConsentEvent();
        event.setConsent(consent);
        event.setEventType(EventType.REVOKE);
        event.setEventDate(LocalDateTime.now());
        event.setPerformedBy(PerformedBy.USER);
        event.setNotes("Consent revoked");
 
        ConsentEvent savedEvent = consentEventRepo.save(event);
        consent.setConsentEvents(List.of(savedEvent));

        return consentRepo.save(consent);
    }
}
 
