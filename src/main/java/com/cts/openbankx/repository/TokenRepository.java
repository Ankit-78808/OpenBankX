package com.cts.openbankx.repository;

import com.cts.openbankx.model.Token;
import com.cts.openbankx.enums.TokenStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findByUser_UserId(Long userId);
    List<Token> findByAuthClient_ClientId(Long clientId);
    List<Token> findByStatus(TokenStatus status);
}
