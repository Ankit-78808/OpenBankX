package com.cts.openbankx.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.openbankx.enums.PlanEnvironment;
import com.cts.openbankx.model.APIPlan;
import com.cts.openbankx.model.APIProduct;
import com.cts.openbankx.repository.APIPlanRepository;
import com.cts.openbankx.repository.APIProductRepository; // Ensure this exists

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class APIPlanService {
    
    private  APIPlanRepository repository;
    private  APIProductRepository apiProductRepository; 
    
    public APIPlan createPlan(APIPlan plan, UUID productid) {
        APIProduct product = apiProductRepository.findById(productid)
            .orElseThrow(() -> new RuntimeException("APIProduct not found: " + productid));
        plan.setProduct(product);
        return repository.save(plan);
    }
    
    public List<APIPlan> getAllPlans() {
        return repository.findAll();
    }

    public List<APIPlan> getPlansByProduct(UUID productId) {
        return repository.findByProduct_ProductId(productId);
    }
    
    public List<APIPlan> getPlansByEnvironment(PlanEnvironment environment) {
        return repository.findByEnvironment(environment);
    }
    
    public APIPlan updatePlan(APIPlan updated, Long planId) {
        APIPlan existing = getPlanById(planId);
        existing.setEnvironment(updated.getEnvironment());
        existing.setDailyQuota(updated.getDailyQuota());
        existing.setRateLimitPerMin(updated.getRateLimitPerMin());
        existing.setSla(updated.getSla());
        return repository.save(existing);
    }

    public void deletePlan(Long planid) {
        repository.deleteById(planid); 
    }

    private APIPlan getPlanById(Long planId) {

        return repository.findById(planId)
            .orElseThrow(() -> new RuntimeException("APIPlan not found: " + planId));
    }

    public List<APIPlan> getPlansByProductAndEnv(UUID productId, PlanEnvironment env) {
        return repository.findByProduct_ProductIdAndEnvironment(productId, env);
    }
}