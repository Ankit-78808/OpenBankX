package com.cts.openbankx.service;

import com.cts.openbankx.model.AuthClient;
<<<<<<< HEAD
import com.cts.openbankx.model.TPPApp;
import com.cts.openbankx.repository.AuthClientRepository;
import com.cts.openbankx.repository.TPPAppRepository;
=======
import com.cts.openbankx.repository.AuthClientRepository;
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthClientService {

    private final AuthClientRepository repo;
<<<<<<< HEAD
    private final TPPAppRepository tppAppRepository;

    public AuthClientService(AuthClientRepository repo, TPPAppRepository tppAppRepository) {
        this.repo = repo;
        this.tppAppRepository = tppAppRepository;
    }

    public AuthClient create(AuthClient client) {
        client.setTppApp(resolveTppApp(client.getTppAppId()));
=======

    public AuthClientService(AuthClientRepository repo) {
        this.repo = repo;
    }

    public AuthClient create(AuthClient client) {
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
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
<<<<<<< HEAD
            existing.setTppApp(resolveTppApp(input.getTppAppId()));
=======
            existing.setTppAppId(input.getTppAppId());
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
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
<<<<<<< HEAD

    private TPPApp resolveTppApp(Long tppAppId) {
        if (tppAppId == null) {
            throw new IllegalArgumentException("TPPAppID is required");
        }

        return tppAppRepository.findById(tppAppId)
                .orElseThrow(() -> new IllegalArgumentException("TPPApp not found: " + tppAppId));
    }
}
=======
}
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
