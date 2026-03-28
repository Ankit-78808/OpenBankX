package com.cts.openbankx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.openbankx.model.APILog;
import com.cts.openbankx.repository.APILogRepository;

@Service
public class APILogService {

    private final APILogRepository repo;

    public APILogService(APILogRepository repo) {
        this.repo = repo;
    }

    // CREATE (used by gateway filter)
    public APILog save(APILog log) {
        return repo.save(log);
    }

    // READ
    public List<APILog> findAll() {
        return repo.findAll();
    }

    public APILog findById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("APILog not found"));
    }

    public List<APILog> findByTppApp(Long appId) {
        return repo.findByTppApp_TppAppId(appId);
    }

    // DELETE (admin only)
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
