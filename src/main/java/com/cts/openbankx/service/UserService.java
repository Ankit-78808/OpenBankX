package com.cts.openbankx.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.openbankx.config.JwtUtil;
import com.cts.openbankx.enums.UserRole;
import com.cts.openbankx.model.User;
import com.cts.openbankx.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;   // ✅ MISSING FIELD FIXED

    public UserService(UserRepository repo,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {   // ✅ INJECTED
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;      // ✅ ASSIGNED
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    /**
     * Create or update user.
     * Encodes password only if it is new or changed.
     * DEV ONLY: auto-generate JWT for ADMIN users.
     */
    public User save(User user) {

        // ✅ Encode password safely
        if (user.getPassword() != null
                && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User savedUser = repo.save(user);

        // ✅ DEV SUPPORT: Auto-generate JWT ONLY for ADMIN
        if (savedUser.getRole() == UserRole.ADMIN) {

            String token = jwtUtil.generateToken(
                    savedUser.getEmail(),
                    savedUser.getRole().name()
            );

            // ✅ DEV ONLY LOG (REMOVE BEFORE PROD)
            System.out.println(
                "\n================ DEV ADMIN JWT ================\n"
                + token +
                "\n==============================================\n"
            );
        }

        return savedUser;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
    }
}