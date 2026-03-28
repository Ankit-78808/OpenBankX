package com.cts.openbankx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.openbankx.model.AccountRef;
import com.cts.openbankx.model.Token;
import com.cts.openbankx.repository.AccountRefRepository;
import com.cts.openbankx.security.RequestSecurityContext;

@Service
public class AccountRefService {

    private final AccountRefRepository repo;

    public AccountRefService(AccountRefRepository repo) {
        this.repo = repo;
    }

    /**
     * AISP FLOW:
     * Return accounts for the user resolved via system security context.
     */
    public List<AccountRef> findAccountsForAisp() {

        Token token = RequestSecurityContext.getToken();

        if (token == null || token.getUser() == null) {
            throw new RuntimeException("No security token or user available");
        }

        return repo.findByUser_UserId(
                token.getUser().getUserId()
        );
    }
}