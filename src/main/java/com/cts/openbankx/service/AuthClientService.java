package com.cts.openbankx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.openbankx.model.AuthClient;
import com.cts.openbankx.repository.AuthClientRepository;

@Service
public class AuthClientService {
	private final AuthClientRepository repo;

	public AuthClientService(AuthClientRepository repo) {
		this.repo = repo;
	}

	public List<AuthClient> findAll() {
		return repo.findAll();
	}

	public AuthClient findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("AuthClient not found: " + id));
	}

	public AuthClient save(AuthClient c) {
		return repo.save(c);
	}

	public List<AuthClient> findByTppApp(Long tppAppId) {
		return repo.findByTppApp_TppAppId(tppAppId);
	}
}
