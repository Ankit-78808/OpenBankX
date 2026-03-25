// UserService.java
package com.cts.openbankx.service;

import com.cts.openbankx.model.User;
import com.cts.openbankx.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public User create(User u) { return repo.save(u); }

    public User getById(Long id) { return repo.findById(id).orElse(null); }

    public List<User> getAll() { return repo.findAll(); }

    public User update(Long id, User input) {
        return repo.findById(id).map(existing -> {
            // Simple field copy (adjust to your fields)
            existing.setName(input.getName());
            existing.setRole(input.getRole());
            existing.setEmail(input.getEmail());
            existing.setPhone(input.getPhone());
            existing.setStatus(input.getStatus());
            return repo.save(existing);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}