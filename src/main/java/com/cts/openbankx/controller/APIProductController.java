package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cts.openbankx.model.APIProduct;
import com.cts.openbankx.service.APIProductService;

@RestController
@RequestMapping("/api/api-products")
@PreAuthorize("hasRole('ADMIN')")
public class APIProductController {

    private final APIProductService apiProductService;

    public APIProductController(APIProductService apiProductService) {
        this.apiProductService = apiProductService;
    }

    /**
     * Get all API products
     * GET /api/api-products
     */
    @GetMapping
    public ResponseEntity<List<APIProduct>> getAllProducts() {
        return ResponseEntity.ok(apiProductService.findAll());
    }

    /**
     * Get API product by ID
     * GET /api/api-products/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<APIProduct> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(apiProductService.findById(id));
    }

    /**
     * Create a new API product
     * POST /api/api-products
     */
    @PostMapping
    public ResponseEntity<APIProduct> createProduct(@RequestBody APIProduct product) {
        APIProduct savedProduct = apiProductService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    /**
     * Update an existing API product
     * PUT /api/api-products/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<APIProduct> updateProduct(
            @PathVariable Long id,
            @RequestBody APIProduct product) {

        // Ensure the product exists
        apiProductService.findById(id);

        product.setProductId(id);
        return ResponseEntity.ok(apiProductService.save(product));
    }

    /**
     * Delete API product
     * DELETE /api/api-products/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        apiProductService.delete(id);
        return ResponseEntity.noContent().build();
    }
}