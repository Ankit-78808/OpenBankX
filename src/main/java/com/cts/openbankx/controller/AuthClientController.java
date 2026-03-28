package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.openbankx.model.AuthClient;
import com.cts.openbankx.service.AuthClientService;

@RestController
@RequestMapping("/api/auth-clients")
public class AuthClientController {

    private final AuthClientService authClientService;

    public AuthClientController(AuthClientService authClientService) {
        this.authClientService = authClientService;
    }

    /**
     * Get all Auth Clients
     * GET /api/auth-clients
     */
    @GetMapping
    public ResponseEntity<List<AuthClient>> getAllClients() {
        return ResponseEntity.ok(authClientService.findAll());
    }

    /**
     * Get Auth Client by ID
     * GET /api/auth-clients/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthClient> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(authClientService.findById(id));
    }

    /**
     * Create or update an Auth Client
     * POST /api/auth-clients
     */
    @PostMapping
    public ResponseEntity<AuthClient> createClient(@RequestBody AuthClient authClient) {
        AuthClient savedClient = authClientService.save(authClient);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    /**
     * Get Auth Clients by TPP Application ID
     * GET /api/auth-clients/tpp/{tppAppId}
     */
    @GetMapping("/tpp/{tppAppId}")
    public ResponseEntity<List<AuthClient>> getClientsByTppApp(
            @PathVariable Long tppAppId) {
        return ResponseEntity.ok(authClientService.findByTppApp(tppAppId));
    }
}