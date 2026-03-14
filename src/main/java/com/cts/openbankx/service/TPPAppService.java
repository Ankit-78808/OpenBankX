package com.cts.openbankx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.openbankx.model.TPPApp;
import com.cts.openbankx.repository.TPPAppRepository;


@Service
public class TPPAppService {

	
	@Autowired
	private TPPAppRepository repo;
	
	public TPPApp registerData(TPPApp tppapp) {
		return repo.save(tppapp);
	}

	public List<TPPApp> getData() {
		return repo.findAll();
	}

	public TPPApp getDataById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public TPPApp updatedData(Long id, TPPApp tpp)
	{
		TPPApp exist=repo.findById(id).orElse(null);
		if(exist!=null) {
			if(tpp.getAppName()!=null) {
				exist.setAppName(tpp.getAppName());
			}
			if(tpp.getRedirectURIs()!=null) {
				exist.setRedirectURIs(tpp.getRedirectURIs());
			}
			if(tpp.getScopesRequested()!=null) {
				exist.setScopesRequested(tpp.getScopesRequested());
			}
			return repo.save(exist);
		}
		return null;
	}

	public void deleteData(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}
	
	

}
