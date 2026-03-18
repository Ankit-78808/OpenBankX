package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.TPP;

public interface TPPRepository extends JpaRepository<TPP, Long> {
	
	boolean existsByTppID(Long tppID);
	
	boolean existsByRegistrationNumber(String registrationNumber);

}
