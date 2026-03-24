package com.cts.openbankx.controller;

import com.cts.openbankx.model.TransactionRef;
import com.cts.openbankx.service.TransactionRefService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionRefController {

	@Autowired
    private TransactionRefService service;

    @GetMapping("/getByTransactionId/{id}")
    public TransactionRef getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/getAllTransaction")
    public List<TransactionRef> getAll() {
        return service.getAll();
    }

    @PostMapping("/createTransaction")
    public TransactionRef create(@RequestBody TransactionRef txn) {
        return service.create(txn);
    }

    @PutMapping("/updateTransaction/{id}")
    public TransactionRef update(@PathVariable Long id, @RequestBody TransactionRef txn) {
        return service.update(id, txn);
    }

    @DeleteMapping("/deleteTransaction/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}