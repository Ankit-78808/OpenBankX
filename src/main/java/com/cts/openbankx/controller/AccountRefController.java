package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.AccountRef;
import com.cts.openbankx.service.AccountRefService;

@RestController
@RequestMapping("/api/v1/aisp/accounts")
public class AccountRefController {

    private final AccountRefService service;

    public AccountRefController(AccountRefService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AccountRef>> getAccounts() {
        return ResponseEntity.ok(
                service.findAccountsForAisp()
        );
    }
}