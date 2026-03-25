package com.cts.openbankx.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.openbankx.enums.SubscriptionStatus; // Use SubscriptionStatus, not ProductStatus
import com.cts.openbankx.model.TPPSubscription;

public interface TPPSubscriptionRepository extends JpaRepository<TPPSubscription, UUID> {

    List<TPPSubscription> findByStatus(SubscriptionStatus status);
    
    List<TPPSubscription> findByTppAppId(UUID tppAppId);
    
    Optional<TPPSubscription> findByTppAppIdAndSubscriptionId(UUID tppAppId, UUID subscriptionId);
    
    boolean existsByTppAppIdAndSubscriptionIdAndStatus(UUID tppAppId, UUID subscriptionId, SubscriptionStatus status);
}