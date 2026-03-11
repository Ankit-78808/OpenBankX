package com.cts.openbankx.controller;

import com.cts.openbankx.model.TPPSubscription;
import com.cts.openbankx.service.TPPSubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
public class TPPSubscriptionController {

    private final TPPSubscriptionService service;

    public TPPSubscriptionController(TPPSubscriptionService service) {
        this.service = service;
    }

    // Create new subscription
    @PostMapping
    public TPPSubscription createSubscription(@RequestBody TPPSubscription subscription) {
        return service.createSubscription(subscription);
    }

    // Fetch all subscriptions
    @GetMapping
    public List<TPPSubscription> getAllSubscriptions() {
        return service.getAllSubscriptions();
    }

    // Fetch subscription by ID
    @GetMapping("/{id}")
    public TPPSubscription getSubscriptionById(@PathVariable UUID id) {
        return service.getSubscriptionById(id);
    }

    // Fetch subscriptions by status
    @GetMapping("/status/{status}")
    public List<TPPSubscription> getSubscriptionsByStatus(@PathVariable String status) {
        return service.getSubscriptionsByStatus(status);
    }

    // Fetch subscriptions by TPP App ID
    @GetMapping("/tpp/{tppAppId}")
    public List<TPPSubscription> getSubscriptionsByTppAppId(@PathVariable UUID tppAppId) {
        return service.getSubscriptionsByTppAppId(tppAppId);
    }
}
