package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.openbankx.model.AccountRef;

public interface AccountRefRepository extends JpaRepository<AccountRef, Long> {
}