package com.cts.openbankx.repository;

import com.cts.openbankx.model.AccountRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRefRepository extends JpaRepository<AccountRef, Long> {
    List<AccountRef> findByUser_UserId(Long userId);
    Optional<AccountRef> findByAccountNumberMasked(String accountNumberMasked);
}
