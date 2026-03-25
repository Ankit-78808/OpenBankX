package com.cts.openbankx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.openbankx.model.TPP;
import com.cts.openbankx.repository.TPPRepository;


@Service
public class TPPService {

	
	@Autowired
	private TPPRepository repo;
	
	public TPP register(TPP tpp) {
		if(repo.existsByTppID(tpp.getTppID()) || repo.existsByRegistrationNumber(tpp.getRegistrationNumber())) {
			throw new RuntimeException("TPP already exists with same TPP ID or registrationNumber");
		}
		return repo.save(tpp);
	}

	public List<TPP> getAllData() {
		return repo.findAll();
	}

	public TPP updateData(Long id,TPP tpp) {
		TPP exist=repo.findById(id).orElse(null);
		if(exist != null) {
			
			if(tpp.getCertificationRef() != null) {
			exist.setCertificationRef(tpp.getCertificationRef());
			}
			if(tpp.getContactInfo() != null) {
				exist.setContactInfo(tpp.getContactInfo());
			}
			if(tpp.getLegalName()!=null) {
				exist.setLegalName(tpp.getLegalName());
			}
			if(tpp.getRegistrationNumber()!=null) {
				exist.setRegistrationNumber(tpp.getRegistrationNumber());
			}
			if(tpp.getTppID()!=null) {
				exist.setTppID(tpp.getTppID());
			}
			 
			return repo.save(exist);
		}
		return null;
		
	}

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	public TPP getData(Long id) {
		return repo.findById(id).orElse(null);
	}

}
