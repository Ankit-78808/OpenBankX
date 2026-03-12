package com.cts.openbankx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.openbankx.model.TPP;
import com.cts.openbankx.repository.TPPRepository;


@Service
public class TPPService {

	
	@Autowired
	private TPPRepository repo;
	
	public TPP registerTPP(TPP tpp) {
		// TODO Auto-generated method stub
		return repo.save(tpp);
	}

}
