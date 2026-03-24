package com.cts.openbankx.controller;

import com.cts.openbankx.model.FundsCheck;
import com.cts.openbankx.service.FundsCheckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fundscheck")
public class FundsCheckController {

	@Autowired
    private FundsCheckService service;

    @GetMapping("/getByFundsCheckById/{id}")
    public FundsCheck getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/getAllFundsCheck")
    public List<FundsCheck> getAll() {
        return service.getAll();
    }

    @PostMapping("/createFundsCheck")
    public FundsCheck create(@RequestBody FundsCheck fc) {
        return service.create(fc);
    }

    @PutMapping("/updateFundsCheckById/{id}")
    public FundsCheck update(@PathVariable Long id, @RequestBody FundsCheck fc) {
        return service.update(id, fc);
    }

    @DeleteMapping("/deleteFundsCheck/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}