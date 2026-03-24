package com.cts.openbankx.controller;

import com.cts.openbankx.model.AccountRef;
import com.cts.openbankx.service.AccountRefService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRefController {

	@Autowired
    private AccountRefService service;

    @GetMapping("/getById/{id}")
    public AccountRef getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("getAll")
    public List<AccountRef> getAll() {
        return service.getAll();
    }

    @PostMapping("/createUser")
    public AccountRef create(@RequestBody AccountRef account) {
        return service.create(account);
    }

    @PutMapping("/updateAccount/{id}")
    public AccountRef update(@PathVariable Long id, @RequestBody AccountRef account) {
        return service.update(id, account);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}