package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.openbankx.model.TransactionRef;

public interface TransactionRefRepository extends JpaRepository<TransactionRef, Long> {
}