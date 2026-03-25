package com.cts.openbankx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.openbankx.model.Incident;
import com.cts.openbankx.repository.IncidentRepository;


@Service
public class IncidentService {
	
	
	@Autowired
	private IncidentRepository repo;

	public Incident registerData(Incident incident) {
		return repo.save(incident);
	}

	public List<Incident> getAllData() {
		return repo.findAll();
	}

	public Incident getElementById(Long id) {
		return repo.findById(id).orElse(null);
		
	}

	public Incident updateData(Long id, Incident incident) {
		Incident in=repo.findById(id).orElse(null);
		
		if(in!=null) {
			if(incident.getCategory()!=null) {
				in.setCategory(incident.getCategory());
			}
			if(incident.getDescription()!=null) {
				in.setDescription(incident.getDescription());
			}
			
			return repo.save(in);
		}
		return null;
	}

	public void deleteData(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

}
