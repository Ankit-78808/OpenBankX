package com.cts.openbankx.service;

import com.cts.openbankx.model.TransactionRef;
import com.cts.openbankx.repository.TransactionRefRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionRefService {

	@Autowired
    private TransactionRefRepository transactionRefRepository;

	public TransactionRef getById(Long id) {
        return transactionRefRepository.findById(id).orElse(null);
    }

    public List<TransactionRef> getAll() {
        return transactionRefRepository.findAll();
    }
	
    public TransactionRef create(TransactionRef txn) {
        return transactionRefRepository.save(txn);
    }

    public TransactionRef update(Long id, TransactionRef txn) {
        txn.setTxnRefId(id);
        return transactionRefRepository.save(txn);
    }

    public void delete(Long id) {
        transactionRefRepository.deleteById(id);
    }
}