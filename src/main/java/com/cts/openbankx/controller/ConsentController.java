package com.cts.openbankx.controller;

import com.cts.openbankx.enums.PerformedBy;
import com.cts.openbankx.model.Consent;
import com.cts.openbankx.model.ConsentEvent;
import com.cts.openbankx.service.ConsentService;
import com.cts.openbankx.service.ConsentEventService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/consents")
public class ConsentController {
    private final ConsentService consentService;
    private final ConsentEventService consentEventService;

    public ConsentController(ConsentService consentService, ConsentEventService consentEventService) {
        this.consentService = consentService;
        this.consentEventService = consentEventService;
    }

    @GetMapping
    public List<Consent> getAll() { return consentService.findAll(); }

    @GetMapping("/{id}")
    public Consent getById(@PathVariable Long id) { return consentService.findById(id); }

    @GetMapping("/user/{userId}")
    public List<Consent> getByUser(@PathVariable Long userId) { return consentService.findByUser(userId); }

    @GetMapping("/app/{tppAppId}")
    public List<Consent> getByApp(@PathVariable Long tppAppId) { return consentService.findByTppApp(tppAppId); }

    @PostMapping
    public Consent create(@RequestBody Consent consent) { return consentService.create(consent); }

    @PutMapping("/{id}/revoke")
    public Consent revoke(@PathVariable Long id, @RequestParam(defaultValue = "USER") PerformedBy by) {
        return consentService.revoke(id, by);
    }

    // --- Consent Events ---
    @GetMapping("/{consentId}/events")
    public List<ConsentEvent> getEvents(@PathVariable Long consentId) {
        return consentEventService.findByConsent(consentId);
    }
}
