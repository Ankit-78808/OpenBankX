package com.cts.openbankx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.openbankx.model.APIUsageReport;
import com.cts.openbankx.repository.APIUsageReportRepository;

@Service
public class APIUsageReportService {
	
	@Autowired
	private APIUsageReportRepository repo;

	public APIUsageReport addAllData(APIUsageReport r) {
		return repo.save(r);
	}

	public List<APIUsageReport> getAllData() {
		return repo.findAll();
	}

	public ResponseEntity<Optional<APIUsageReport>> getDataById(Long id) {
		// TODO Auto-generated method stub
		Optional<APIUsageReport> api=repo.findById(id);
		
		if(api==null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(api);
		
	}

	public APIUsageReport update(Long id, APIUsageReport r) {
		APIUsageReport api=repo.findById(id).orElse(null);
		
		if(api!=null) {
			
			if(api.getScope()!=null) {
				api.setScope(r.getScope());
			}
			if(api.getMetrics()!=null) {
				api.setMetrics(r.getMetrics());
			}
			return repo.save(api);
		}
		return null;
		
	}

	public void deleteData(Long id) {
		repo.deleteById(id);
		
	}
	
	

}
