package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cts.openbankx.model.APIPlan;
import com.cts.openbankx.service.APIPlanService;

@RestController
@RequestMapping("/api/api-plans")
@PreAuthorize("hasRole('ADMIN')")
public class APIPlanController {

    private final APIPlanService apiPlanService;

    public APIPlanController(APIPlanService apiPlanService) {
        this.apiPlanService = apiPlanService;
    }

    /**
     * Get all API plans
     * GET /api/api-plans
     */
    @GetMapping
    public ResponseEntity<List<APIPlan>> getAllPlans() {
        return ResponseEntity.ok(apiPlanService.findAll());
    }

    /**
     * Get API plan by ID
     * GET /api/api-plans/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<APIPlan> getPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(apiPlanService.findById(id));
    }

    /**
     * Create or update an API plan
     * POST /api/api-plans
     */
    @PostMapping
    public ResponseEntity<APIPlan> createPlan(@RequestBody APIPlan apiPlan) {
        APIPlan savedPlan = apiPlanService.save(apiPlan);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    /**
     * Get API plans by product ID
     * GET /api/api-plans/product/{productId}
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<APIPlan>> getPlansByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(apiPlanService.findByProduct(productId));
    }
}