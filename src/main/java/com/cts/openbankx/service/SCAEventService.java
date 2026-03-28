package com.cts.openbankx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.openbankx.model.SCAEvent;
import com.cts.openbankx.repository.SCAEventRepository;

@Service
public class SCAEventService {
	private final SCAEventRepository repo;

	public SCAEventService(SCAEventRepository repo) {
		this.repo = repo;
	}

	public List<SCAEvent> findAll() {
		return repo.findAll();
	}

	public SCAEvent findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("SCAEvent not found: " + id));
	}

	public SCAEvent save(SCAEvent e) {
		return repo.save(e);
	}

	public List<SCAEvent> findByUser(Long userId) {
		return repo.findByUser_UserId(userId);
	}
}
