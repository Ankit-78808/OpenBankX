package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.TPPSubscription;
import com.cts.openbankx.service.TPPSubscriptionService;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {
	private final TPPSubscriptionService service;

	public SubscriptionController(TPPSubscriptionService service) {
		this.service = service;
	}

	@GetMapping
	public List<TPPSubscription> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public TPPSubscription getById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping("/app/{tppAppId}")
	public List<TPPSubscription> getByApp(@PathVariable Long tppAppId) {
		return service.findByTppApp(tppAppId);
	}

	@PostMapping
	public TPPSubscription create(@RequestBody TPPSubscription sub) {
		return service.save(sub);
	}
}
