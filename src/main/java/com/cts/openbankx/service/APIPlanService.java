package com.cts.openbankx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.openbankx.model.APIPlan;
import com.cts.openbankx.repository.APIPlanRepository;

@Service
public class APIPlanService {
	private final APIPlanRepository repo;

	public APIPlanService(APIPlanRepository repo) {
		this.repo = repo;
	}

	public List<APIPlan> findAll() {
		return repo.findAll();
	}

	public APIPlan findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("APIPlan not found: " + id));
	}

	public APIPlan save(APIPlan p) {
		return repo.save(p);
	}

	public List<APIPlan> findByProduct(Long productId) {
		return repo.findByApiProduct_ProductId(productId);
	}
}
