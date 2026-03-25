package com.cts.openbankx.controller;

import com.cts.openbankx.model.AuthClient;
import com.cts.openbankx.service.AuthClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth-clients")
public class AuthClientController {

    private final AuthClientService service;

    public AuthClientController(AuthClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody AuthClient client) {
        AuthClient saved = service.create(client);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Auth client created successfully");
        response.put("authClient", saved);

        return ResponseEntity.created(URI.create("/api/auth-clients/" + saved.getClientId())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        AuthClient client = service.getById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Auth client fetched successfully");
        response.put("authClient", client);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<AuthClient> clients = service.getAll();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Auth clients fetched successfully");
        response.put("authClients", clients);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody AuthClient input) {
        AuthClient updated = service.update(id, input);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Auth client updated successfully");
        response.put("authClient", updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Auth client deleted successfully");
        response.put("deletedAuthClientId", id);

        return ResponseEntity.ok(response);
    }
}
