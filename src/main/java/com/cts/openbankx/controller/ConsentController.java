package com.cts.openbankx.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
import com.cts.openbankx.model.Consent;
import com.cts.openbankx.service.ConsentService;
 
@RestController
@RequestMapping("/api/v1/consents")
public class ConsentController {
 
    @Autowired
    private ConsentService consentService;
 
    // CREATE CONSENT
    @PostMapping
    public Consent createConsent(@RequestBody Consent consent) {
        return consentService.createConsent(consent);
    }
 
    // GET ALL CONSENTS (for testing/admin)
    @GetMapping
    public List<Consent> getAllConsents() {
        return consentService.getAllConsents();
    }
 
    // GET CONSENT BY ID
    @GetMapping("/{id}")
    public Consent getConsentById(@PathVariable Long id) {
        return consentService.getConsentById(id);
    }
 
    // REVOKE CONSENT
    @PatchMapping("/{id}/revoke")
    public Consent revokeConsent(@PathVariable Long id) {
        return consentService.revokeConsent(id);
    }
}
 