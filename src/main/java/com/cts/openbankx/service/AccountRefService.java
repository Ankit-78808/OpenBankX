package com.cts.openbankx.service;

import com.cts.openbankx.model.AccountRef;
import com.cts.openbankx.repository.AccountRefRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRefService {

	@Autowired
    private AccountRefRepository accountRefRepository;


	 public AccountRef getById(Long id) {
	    return accountRefRepository.findById(id).orElse(null);
	 }

	 public List<AccountRef> getAll() {
	    return accountRefRepository.findAll();
	 }
	
    public AccountRef create(AccountRef account) {
        return accountRefRepository.save(account);
    }

    public AccountRef update(Long id, AccountRef account) {
        account.setAccountId(id);
        return accountRefRepository.save(account);
    }

    public void delete(Long id) {
        accountRefRepository.deleteById(id);
    }
}