package com.cts.openbankx.service;

import com.cts.openbankx.enums.AppStatus;
import com.cts.openbankx.model.TPPApp;
import com.cts.openbankx.repository.TPPAppRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TPPAppService {

    private final TPPAppRepository repo;

    public TPPAppService(TPPAppRepository repo) {
        this.repo = repo;
    }

    // --- Read operations ---
    public List<TPPApp> findAll() {
        return repo.findAll();
    }

    public TPPApp findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("TPPApp not found: " + id));
    }

    public List<TPPApp> findByTpp(Long tppId) {
        return repo.findByTpp_TppId(tppId);
    }

    // --- Create ---
    public TPPApp save(TPPApp app) {

        // Basic validation
        if (app.getRedirectURIs() == null || app.getRedirectURIs().isEmpty()) {
            throw new RuntimeException("Redirect URI is mandatory");
        }

        if (app.getPublicKeysJWKSet() == null || app.getPublicKeysJWKSet().isEmpty()) {
            throw new RuntimeException("Public JWK Set is mandatory");
        }

        if (app.getScopesRequested() == null || app.getScopesRequested().isEmpty()) {
            throw new RuntimeException("Scopes are mandatory");
        }

        return repo.save(app);
    }

    // --- Approve (only PENDING -> APPROVED) ---
    public TPPApp approve(Long id) {
        TPPApp app = findById(id);

        if (app.getStatus() != AppStatus.PENDING) {
            throw new RuntimeException("Only PENDING apps can be approved");
        }

        app.setStatus(AppStatus.APPROVED);
        return repo.save(app);
    }

    // --- Reject (only PENDING -> REJECTED) ---
    public TPPApp reject(Long id) {
        TPPApp app = findById(id);

        if (app.getStatus() != AppStatus.PENDING) {
            throw new RuntimeException("Only PENDING apps can be rejected");
        }

        app.setStatus(AppStatus.REJECTED);
        return repo.save(app);
    }

    // --- Delete (only allowed for PENDING apps) ---
    public void deleteIfPending(Long id) {
        TPPApp app = findById(id);

        if (app.getStatus() != AppStatus.PENDING) {
            throw new RuntimeException("Only PENDING apps can be deleted");
        }

        repo.delete(app);
    }
}