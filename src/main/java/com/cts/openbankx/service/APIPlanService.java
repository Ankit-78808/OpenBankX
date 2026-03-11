package com.cts.openbankx.service;

import com.cts.openbankx.model.APIPlan;
import com.cts.openbankx.repository.APIPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class APIPlanService {

    private final APIPlanRepository repository;

    public APIPlanService(APIPlanRepository repository) {
        this.repository = repository;
    }

    public APIPlan createPlan(APIPlan plan) {
        return repository.save(plan);
    }

    public List<APIPlan> getAllPlans() {
        return repository.findAll();
    }

    public APIPlan getPlanById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<APIPlan> getPlansByEnvironment(String environment) {
        return repository.findByEnvironment(environment);
    }
}
