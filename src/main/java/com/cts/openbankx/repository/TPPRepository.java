package com.cts.openbankx.repository;

import com.cts.openbankx.model.TPP;
import com.cts.openbankx.enums.TPPStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TPPRepository extends JpaRepository<TPP, Long> {
    List<TPP> findByStatus(TPPStatus status);
}
