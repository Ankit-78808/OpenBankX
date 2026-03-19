package com.cts.openbankx.repository;

import com.cts.openbankx.model.AuthClient;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthClientRepository extends JpaRepository<AuthClient, Long> {

	
}