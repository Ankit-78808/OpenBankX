package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.APIPlan;
import com.cts.openbankx.model.APIProduct;
import com.cts.openbankx.service.APIPlanService;
import com.cts.openbankx.service.APIProductService;

@RestController
@RequestMapping("/api/v1/products")
@PreAuthorize("hasRole('ADMIN')")
public class ProductCatalogController {
	private final APIProductService productService;
	private final APIPlanService planService;

	public ProductCatalogController(APIProductService productService, APIPlanService planService) {
		this.productService = productService;
		this.planService = planService;
	}

	// --- Products ---
	@GetMapping
	public List<APIProduct> getAllProducts() {
		return productService.findAll();
	}

	@GetMapping("/{id}")
	public APIProduct getProduct(@PathVariable Long id) {
		return productService.findById(id);
	}

	@PostMapping
	public APIProduct createProduct(@RequestBody APIProduct product) {
		return productService.save(product);
	}

	@PutMapping("/{id}")
	public APIProduct updateProduct(@PathVariable Long id, @RequestBody APIProduct product) {
		product.setProductId(id);
		return productService.save(product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// --- Plans ---
	@GetMapping("/{productId}/plans")
	public List<APIPlan> getPlans(@PathVariable Long productId) {
		return planService.findByProduct(productId);
	}

	@GetMapping("/plans")
	public List<APIPlan> getAllPlans() {
		return planService.findAll();
	}

	@PostMapping("/plans")
	public APIPlan createPlan(@RequestBody APIPlan plan) {
		return planService.save(plan);
	}
}
