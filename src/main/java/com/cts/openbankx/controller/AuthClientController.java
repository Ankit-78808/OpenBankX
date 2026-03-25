package com.cts.openbankx.controller;

import com.cts.openbankx.model.AuthClient;
import com.cts.openbankx.service.AuthClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth-clients")
=======
import java.util.*;

@RestController
@RequestMapping("/api/authclients")
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
public class AuthClientController {

    private final AuthClientService service;

    public AuthClientController(AuthClientService service) {
        this.service = service;
    }

<<<<<<< HEAD
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
=======
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

>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
<<<<<<< HEAD
        response.put("message", "Auth client fetched successfully");
        response.put("authClient", client);
=======
        response.put("message", "Fetch Successful");
        response.put("client", client);
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53

        return ResponseEntity.ok(response);
    }

<<<<<<< HEAD
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<AuthClient> clients = service.getAll();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Auth clients fetched successfully");
        response.put("authClients", clients);
=======
    // READ ALL
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<AuthClient> list = service.getAll();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "All Clients Fetched Successfully");
        response.put("clients", list);
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53

        return ResponseEntity.ok(response);
    }

<<<<<<< HEAD
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody AuthClient input) {
        AuthClient updated = service.update(id, input);
=======
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody AuthClient input) {

        AuthClient updated = service.update(id, input);

>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
<<<<<<< HEAD
        response.put("message", "Auth client updated successfully");
        response.put("authClient", updated);
=======
        response.put("message", "AuthClient Updated Successfully");
        response.put("client", updated);
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53

        return ResponseEntity.ok(response);
    }

<<<<<<< HEAD
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
=======
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {

        boolean deleted = service.delete(id);

>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

<<<<<<< HEAD
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Auth client deleted successfully");
        response.put("deletedAuthClientId", id);

        return ResponseEntity.ok(response);
    }
}
=======
        Map<String, String> response = new HashMap<>();
        response.put("message", "AuthClient Deleted Successfully");
        response.put("deletedClientId", id.toString());

        return ResponseEntity.ok(response);
    }
}
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
