package com.cts.openbankx.controller;

import com.cts.openbankx.model.APIProduct;
import com.cts.openbankx.service.APIProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class APIProductController {

    private final APIProductService service;

    public APIProductController(APIProductService service) {
        this.service = service;
    }

    // Create new API product
    @PostMapping
    public APIProduct createProduct(@RequestBody APIProduct product) {
        return service.createProduct(product);
    }

    // Fetch all API products
    @GetMapping
    public List<APIProduct> getAllProducts() {
        return service.getAllProducts();
    }

    // Fetch API product by ID
    @GetMapping("/{id}")
    public APIProduct getProductById(@PathVariable UUID id) {
        return service.getProductById(id);
    }
}
