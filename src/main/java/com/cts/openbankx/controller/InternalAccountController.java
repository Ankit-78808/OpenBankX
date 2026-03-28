package com.cts.openbankx.controller;

import org.springframework.web.bind.annotation.*;

import com.cts.openbankx.model.AccountRef;
import com.cts.openbankx.repository.AccountRefRepository;

@RestController
@RequestMapping("/api/v1/internal/accounts")
public class InternalAccountController {

    private final AccountRefRepository repo;

    public InternalAccountController(AccountRefRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public AccountRef create(@RequestBody AccountRef account) {
        return repo.save(account);
    }
}