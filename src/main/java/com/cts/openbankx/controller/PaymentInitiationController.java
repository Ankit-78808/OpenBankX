package com.cts.openbankx.controller;

import com.cts.openbankx.model.PaymentInitiation;
import com.cts.openbankx.service.PaymentInitiationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentInitiationController {

	@Autowired
    private PaymentInitiationService service;

    @GetMapping("/getPaymentById/{id}")
    public PaymentInitiation getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/getAllPayment")
    public List<PaymentInitiation> getAll() {
        return service.getAll();
    }

    @PostMapping("/createPayment")
    public PaymentInitiation create(@RequestBody PaymentInitiation payment) {
        return service.create(payment);
    }

    @PutMapping("/updatePayment/{id}")
    public PaymentInitiation update(@PathVariable Long id, @RequestBody PaymentInitiation payment) {
        return service.update(id, payment);
    }

    @DeleteMapping("/deletePayment/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}