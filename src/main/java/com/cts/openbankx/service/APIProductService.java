package com.cts.openbankx.service;

import com.cts.openbankx.model.APIProduct;
import com.cts.openbankx.repository.APIProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class APIProductService {
    private final APIProductRepository repo;

    public APIProductService(APIProductRepository repo) {
        this.repo = repo;
    }

    public List<APIProduct> findAll() { return repo.findAll(); }
    public APIProduct findById(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("APIProduct not found: " + id)); }
    public APIProduct save(APIProduct p) { return repo.save(p); }
    public void delete(Long id) { repo.deleteById(id); }
}
