package com.cts.openbankx.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.TPPSubscription;

public interface TPPSubscriptionRepository extends JpaRepository<TPPSubscription, UUID> {

	List<TPPSubscription> findByStatus(String status);

	List<TPPSubscription> findByTppAppId(UUID tppAppId);

}
