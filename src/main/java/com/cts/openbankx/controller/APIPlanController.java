package com.cts.openbankx.controller;

import com.cts.openbankx.enums.PlanEnvironment;
import com.cts.openbankx.model.APIPlan;
import com.cts.openbankx.service.APIPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class APIPlanController {

    private APIPlanService planService;

    @PostMapping
    public ResponseEntity<APIPlan> createPlan(@RequestBody APIPlan plan, @RequestParam UUID productId) {
        return new ResponseEntity<>(planService.createPlan(plan, productId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<APIPlan>> getAllPlans() {
        return ResponseEntity.ok(planService.getAllPlans());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<APIPlan>> getByProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(planService.getPlansByProduct(productId));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<APIPlan>> getByProductAndEnv(
            @RequestParam UUID productId, 
            @RequestParam PlanEnvironment env) {
        return ResponseEntity.ok(planService.getPlansByProductAndEnv(productId, env));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIPlan> updatePlan(@PathVariable Long id, @RequestBody APIPlan plan) {
        return ResponseEntity.ok(planService.updatePlan(plan, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}