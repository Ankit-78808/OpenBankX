package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.enums.PaymentStatus;
import com.cts.openbankx.model.PaymentInitiation;
import com.cts.openbankx.service.PaymentInitiationService;

@RestController
@RequestMapping("/api/v1/pisp/payments")
public class PaymentInitiationController {
	private final PaymentInitiationService service;

	public PaymentInitiationController(PaymentInitiationService service) {
		this.service = service;
	}

	@GetMapping // PISP: List payments
	public List<PaymentInitiation> list() {
		return service.findAll();
	}

	@PostMapping // PISP: Initiate payment
	public PaymentInitiation create(@RequestBody PaymentInitiation p) {
		return service.initiate(p);
	}

	@PatchMapping("/{id}/status")
	public PaymentInitiation updateStatus(@PathVariable Long id, @RequestParam PaymentStatus status) {
		return service.updateStatus(id, status);
	}
}