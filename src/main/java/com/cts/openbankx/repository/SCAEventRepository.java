package com.cts.openbankx.repository;

import com.cts.openbankx.model.SCAEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SCAEventRepository extends JpaRepository<SCAEvent, Long> {
    List<SCAEvent> findByUser_UserId(Long userId);
    List<SCAEvent> findByReferenceId(String referenceId);
}
