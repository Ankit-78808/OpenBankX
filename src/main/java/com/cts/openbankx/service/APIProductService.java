package com.cts.openbankx.service;

import com.cts.openbankx.model.APIProduct;
import com.cts.openbankx.repository.APIProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class APIProductService {

    private final APIProductRepository repository;

    public APIProductService(APIProductRepository repository) {
        this.repository = repository;
    }

    public APIProduct createProduct(APIProduct product) {
        return repository.save(product);
    }

    public List<APIProduct> getAllProducts() {
        return repository.findAll();
    }

    public APIProduct getProductById(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
