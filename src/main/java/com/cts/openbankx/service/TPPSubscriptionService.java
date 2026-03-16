package com.cts.openbankx.service;

import com.cts.openbankx.enums.SubscriptionStatus;
import com.cts.openbankx.model.APIPlan;
import com.cts.openbankx.model.TPPSubscription;
import com.cts.openbankx.repository.APIPlanRepository;
import com.cts.openbankx.repository.TPPSubscriptionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TPPSubscriptionService {
    
    private final TPPSubscriptionRepository repository;
    private final APIPlanRepository apiPlanRepository;
    
    public TPPSubscription subscribe(Long planId, UUID tppAppId) {
       
        APIPlan plan = apiPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("APIPlan not found: " + planId));
        
    
        TPPSubscription subscription = TPPSubscription.builder()
                .tppAppId(tppAppId)
                .plan(plan)
                .subscribedDate(LocalDate.now())
                .status(SubscriptionStatus.ACTIVE)
                .build();
                
        return repository.save(subscription);
    }
    
    public List<TPPSubscription> getAllSubscription() {
        return repository.findAll();
    }
    
    public List<TPPSubscription> getSubscriptionByTpp(UUID tppAppId) {
        return repository.findByTppAppId(tppAppId);
    }
    
    public TPPSubscription updateStatus(UUID subscriptionId, SubscriptionStatus status) {
        TPPSubscription subscription = getSubscriptionById(subscriptionId);
        subscription.setStatus(status);
        return repository.save(subscription);
    }
    
    public void cancelSubscription(UUID subscriptionId) {
        TPPSubscription subscription = getSubscriptionById(subscriptionId);
        subscription.setStatus(SubscriptionStatus.CANCELLED);
        repository.save(subscription);
        
    }

    private TPPSubscription getSubscriptionById(UUID subscriptionId) {
        return repository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found: " + subscriptionId));
    }
}