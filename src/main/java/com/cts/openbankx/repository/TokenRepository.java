package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

}
