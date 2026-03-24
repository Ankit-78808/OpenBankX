package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.openbankx.model.PaymentInitiation;

public interface PaymentInitiationRepository extends JpaRepository<PaymentInitiation, Long> {
}