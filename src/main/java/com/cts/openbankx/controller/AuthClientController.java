package com.cts.openbankx.controller;

import com.cts.openbankx.model.AuthClient;
import com.cts.openbankx.service.AuthClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/authclients")
public class AuthClientController {

    private final AuthClientService service;

    public AuthClientController(AuthClientService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody AuthClient client) {

        AuthClient saved = service.create(client);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "AuthClient Created Successfully");
        response.put("client", saved);

        return ResponseEntity
                .created(URI.create("/api/authclients/" + saved.getClientId()))
                .body(response);
    }

    // READ by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {

        AuthClient client = service.getById(id);

        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Fetch Successful");
        response.put("client", client);

        return ResponseEntity.ok(response);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<AuthClient> list = service.getAll();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "All Clients Fetched Successfully");
        response.put("clients", list);

        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody AuthClient input) {

        AuthClient updated = service.update(id, input);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "AuthClient Updated Successfully");
        response.put("client", updated);

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {

        boolean deleted = service.delete(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "AuthClient Deleted Successfully");
        response.put("deletedClientId", id.toString());

        return ResponseEntity.ok(response);
    }
}