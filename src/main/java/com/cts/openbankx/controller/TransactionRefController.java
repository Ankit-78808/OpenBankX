package com.cts.openbankx.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cts.openbankx.model.TransactionRef;
import com.cts.openbankx.service.TransactionRefService;

@RestController
@RequestMapping("/api/v1/aisp/accounts")
public class TransactionRefController {

    private final TransactionRefService service;

    public TransactionRefController(TransactionRefService service) {
        this.service = service;
    }

    /* ============================================================
       ✅ AISP – READ‑ONLY TRANSACTION HISTORY
       ============================================================ */

    /**
     * Fetch transaction history for an account (AISP)
     * GET /api/v1/aisp/accounts/{id}/transactions
     */
    @GetMapping("/{id}/transactions")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<TransactionRef>> getTransactions(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.findByAccountId(id));
    }

    /* ============================================================
       ✅ ADMIN / BANK – CREATE TRANSACTION
       ============================================================ */

    /**
     * Create a new transaction for an account
     * POST /api/v1/aisp/accounts/{id}/transactions
     */
    @PostMapping("/{id}/transactions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransactionRef> createTransaction(
            @PathVariable Long id,
            @RequestBody TransactionRef txn) {

        // Ensure account is correctly linked
        txn.getAccountRef().setAccountId(id);

        // Default txn date if not provided
        if (txn.getTxnDate() == null) {
            txn.setTxnDate(LocalDateTime.now());
        }

        TransactionRef saved = service.save(txn);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /* ============================================================
       ✅ ADMIN – VIEW ALL TRANSACTIONS
       ============================================================ */

    /**
     * Get all transactions (audit / reconciliation)
     * GET /api/v1/aisp/accounts/transactions/all
     */
    @GetMapping("/transactions/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TransactionRef>> getAllTransactions() {
        return ResponseEntity.ok(service.findAll());
    }
}