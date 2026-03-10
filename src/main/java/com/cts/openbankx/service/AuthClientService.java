package com.cts.openbankx.service;

import com.cts.openbankx.model.AuthClient;
import com.cts.openbankx.repository.AuthClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthClientService {

    private final AuthClientRepository repo;

    public AuthClientService(AuthClientRepository repo) {
        this.repo = repo;
    }

    public AuthClient create(AuthClient client) {
        return repo.save(client);
    }

    public AuthClient getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<AuthClient> getAll() {
        return repo.findAll();
    }

    public AuthClient update(Long id, AuthClient input) {
        return repo.findById(id).map(existing -> {
            existing.setTppAppId(input.getTppAppId());
            existing.setClientType(input.getClientType());
            existing.setRedirectUris(input.getRedirectUris());
            existing.setScopesAllowed(input.getScopesAllowed());
            existing.setStatus(input.getStatus());
            return repo.save(existing);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}