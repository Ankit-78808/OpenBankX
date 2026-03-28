package com.cts.openbankx.repository;

import com.cts.openbankx.model.TPPSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TPPSubscriptionRepository extends JpaRepository<TPPSubscription, Long> {
    List<TPPSubscription> findByTppApp_TppAppId(Long tppAppId);
}
