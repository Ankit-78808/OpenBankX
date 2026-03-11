package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.RateLimit;

public interface RateLimitRepo extends JpaRepository<RateLimit, Long> {

}
