package com.cts.openbankx.controller;

import com.cts.openbankx.enums.ProductStatus;
import com.cts.openbankx.model.APIProduct;
import com.cts.openbankx.service.APIProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class APIProductController {

    private APIProductService productService;

    @PostMapping
    public ResponseEntity<APIProduct> createProduct(@RequestBody APIProduct product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<APIProduct>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIProduct> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<APIProduct>> getByStatus(@PathVariable ProductStatus status) {
        return ResponseEntity.ok(productService.getByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIProduct> updateProduct(@PathVariable UUID id, @RequestBody APIProduct product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<APIProduct> updateStatus(@PathVariable UUID id, @RequestParam ProductStatus status) {
        return ResponseEntity.ok(productService.updateStatus(status, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}