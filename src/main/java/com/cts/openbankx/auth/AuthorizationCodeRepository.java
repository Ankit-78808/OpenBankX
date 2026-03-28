package com.cts.openbankx.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorizationCodeRepository
        extends JpaRepository<AuthorizationCode, Long> {

    Optional<AuthorizationCode> findByCodeAndUsedFalse(String code);
}