package com.cts.openbankx.repository;

import com.cts.openbankx.model.TransactionRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRefRepository extends JpaRepository<TransactionRef, Long> {
    List<TransactionRef> findByAccountRef_AccountId(Long accountId);
}
