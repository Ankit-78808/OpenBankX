package com.cts.openbankx.repository;

import com.cts.openbankx.model.ConsentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsentEventRepository extends JpaRepository<ConsentEvent, Long> {
    List<ConsentEvent> findByConsent_ConsentId(Long consentId);
}
