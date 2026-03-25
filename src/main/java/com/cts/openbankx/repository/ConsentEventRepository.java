package com.cts.openbankx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.ConsentEvent;

public interface ConsentEventRepository extends JpaRepository<ConsentEvent, Long> {
	
	List<ConsentEvent> findByConsentConsentId(Long consentId);
}
