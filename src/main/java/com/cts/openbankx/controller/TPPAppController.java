package com.cts.openbankx.controller;

import com.cts.openbankx.enums.AppStatus;
import com.cts.openbankx.model.TPPApp;
import com.cts.openbankx.service.TPPAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apps")
@PreAuthorize("hasRole('ADMIN')")
public class TPPAppController {

    private final TPPAppService tppAppService;

    public TPPAppController(TPPAppService tppAppService) {
        this.tppAppService = tppAppService;
    }

    // --- Register a new TPP App (default status = PENDING) ---
    @PostMapping
    public TPPApp registerApp(@RequestBody TPPApp app) {
        app.setStatus(AppStatus.PENDING);
        return tppAppService.save(app);
    }

    // --- Get all apps ---
    @GetMapping
    public List<TPPApp> getAllApps() {
        return tppAppService.findAll();
    }

    // --- Get app by id ---
    @GetMapping("/{id}")
    public TPPApp getApp(@PathVariable Long id) {
        return tppAppService.findById(id);
    }
    
    
    
    // --- Approve app (only if PENDING) ---
    @PutMapping("/{id}/approve")
    public TPPApp approveApp(@PathVariable Long id) {
        return tppAppService.approve(id);
    }

    // --- Reject app (only if PENDING) ---
    @PutMapping("/{id}/reject")
    public TPPApp rejectApp(@PathVariable Long id) {
        return tppAppService.reject(id);
    }

    // --- Delete app (allowed only if PENDING) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApp(@PathVariable Long id) {
        tppAppService.deleteIfPending(id);
        return ResponseEntity.noContent().build();
    }
}