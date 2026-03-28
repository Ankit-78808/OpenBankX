package com.cts.openbankx.service;

import com.cts.openbankx.model.TPP;
import com.cts.openbankx.repository.TPPRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TPPService {
    private final TPPRepository repo;

    public TPPService(TPPRepository repo) {
        this.repo = repo;
    }

    public List<TPP> findAll() { return repo.findAll(); }
    
    public TPP findById(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("TPP not found: " + id)); }
    
    public TPP save(TPP t) { return repo.save(t); }
    
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("TPP not found with id: " + id);
        }
        repo.deleteById(id);
    }
    
    public TPP updateTPP(Long id, TPP request) {

        TPP existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("TPP not found with id: " + id));

        if (request.getLegalName() != null) {
            existing.setLegalName(request.getLegalName());
        }

        if (request.getRegistrationNumber() != null) {
            existing.setRegistrationNumber(request.getRegistrationNumber());
        }

        if (request.getContactInfo() != null) {
            existing.setContactInfo(request.getContactInfo());
        }

        if (request.getCertificationRef() != null) {
            existing.setCertificationRef(request.getCertificationRef());
        }

        return repo.save(existing);
    }
    
}
