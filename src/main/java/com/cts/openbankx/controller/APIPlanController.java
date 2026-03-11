package com.cts.openbankx.controller;

import com.cts.openbankx.model.APIPlan;
import com.cts.openbankx.service.APIPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/plans")
public class APIPlanController {

    private final APIPlanService service;

    public APIPlanController(APIPlanService service) {
        this.service = service;
    }

    // Create new API plan
    @PostMapping
    public APIPlan createPlan(@RequestBody APIPlan plan) {
        return service.createPlan(plan);
    }

    // Fetch all API plans
    @GetMapping
    public List<APIPlan> getAllPlans() {
        return service.getAllPlans();
    }

    // Fetch API plan by ID
    @GetMapping("/{id}")
    public APIPlan getPlanById(@PathVariable UUID id) {
        return service.getPlanById(id);
    }

    // Fetch API plans by environment (Sandbox/Production)
    @GetMapping("/environment/{env}")
    public List<APIPlan> getPlansByEnvironment(@PathVariable String env) {
        return service.getPlansByEnvironment(env);
    }
}
