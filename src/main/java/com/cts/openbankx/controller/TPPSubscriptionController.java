package com.cts.openbankx.controller;

import com.cts.openbankx.enums.SubscriptionStatus;
import com.cts.openbankx.model.TPPSubscription;
import com.cts.openbankx.service.TPPSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class TPPSubscriptionController {

    private final TPPSubscriptionService subscriptionService;


    @PostMapping
    public ResponseEntity<TPPSubscription> subscribe(
            @RequestParam Long planId,
            @RequestParam UUID tppAppId) {
        TPPSubscription subscription = subscriptionService.subscribe(planId, tppAppId);
        return new ResponseEntity<>(subscription, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<TPPSubscription>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscription());
    }

    
    @GetMapping("/tpp/{tppAppId}")
    public ResponseEntity<List<TPPSubscription>> getSubscriptionsByTpp(@PathVariable UUID tppAppId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionByTpp(tppAppId));
    }

    
    @PatchMapping("/{id}/status")
    public ResponseEntity<TPPSubscription> updateStatus(
            @PathVariable UUID id,
            @RequestParam SubscriptionStatus status) {
        return ResponseEntity.ok(subscriptionService.updateStatus(id, status));
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable UUID id) {
        subscriptionService.cancelSubscription(id);
        return ResponseEntity.noContent().build();
    }
}