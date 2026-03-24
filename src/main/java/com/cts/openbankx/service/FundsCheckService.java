package com.cts.openbankx.service;

import com.cts.openbankx.model.FundsCheck;
import com.cts.openbankx.repository.FundsCheckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundsCheckService {

	@Autowired
    private FundsCheckRepository fundsCheckRepository;

	public FundsCheck getById(Long id) {
        return fundsCheckRepository.findById(id).orElse(null);
    }

    public List<FundsCheck> getAll() {
        return fundsCheckRepository.findAll();
    }
	
    public FundsCheck create(FundsCheck fc) {
        return fundsCheckRepository.save(fc);
    }

    public FundsCheck update(Long id, FundsCheck fc) {
        fc.setFundsCheckId(id);
        return fundsCheckRepository.save(fc);
    }

    public void delete(Long id) {
        fundsCheckRepository.deleteById(id);
    }
}