package com.cts.openbankx.service;

import com.cts.openbankx.model.TPPSubscription;
import com.cts.openbankx.repository.TPPSubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TPPSubscriptionService {

    private final TPPSubscriptionRepository repository;

    public TPPSubscriptionService(TPPSubscriptionRepository repository) {
        this.repository = repository;
    }

    public TPPSubscription createSubscription(TPPSubscription subscription) {
        return repository.save(subscription);
    }

    public List<TPPSubscription> getAllSubscriptions() {
        return repository.findAll();
    }

    public TPPSubscription getSubscriptionById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<TPPSubscription> getSubscriptionsByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<TPPSubscription> getSubscriptionsByTppAppId(UUID tppAppId) {
        return repository.findByTppAppId(tppAppId);
    }
}
