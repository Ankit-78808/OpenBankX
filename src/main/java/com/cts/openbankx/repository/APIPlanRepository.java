package com.cts.openbankx.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.APIPlan;

public interface APIPlanRepository extends JpaRepository<APIPlan, UUID> {

	List<APIPlan> findByEnvironment(String environment);

}
