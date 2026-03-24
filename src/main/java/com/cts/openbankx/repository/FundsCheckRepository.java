package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.openbankx.model.FundsCheck;

public interface FundsCheckRepository extends JpaRepository<FundsCheck, Long> {
}