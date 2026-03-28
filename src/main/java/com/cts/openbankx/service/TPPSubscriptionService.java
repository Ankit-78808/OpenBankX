package com.cts.openbankx.service;

import com.cts.openbankx.model.TPPSubscription;
import com.cts.openbankx.repository.TPPSubscriptionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TPPSubscriptionService {
    private final TPPSubscriptionRepository repo;

    public TPPSubscriptionService(TPPSubscriptionRepository repo) {
        this.repo = repo;
    }

    public List<TPPSubscription> findAll() { return repo.findAll(); }
    public TPPSubscription findById(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("TPPSubscription not found: " + id)); }
    public TPPSubscription save(TPPSubscription s) {
        if (s.getSubscribedDate() == null) s.setSubscribedDate(LocalDateTime.now());
        return repo.save(s);
    }
    public List<TPPSubscription> findByTppApp(Long tppAppId) { return repo.findByTppApp_TppAppId(tppAppId); }
}
