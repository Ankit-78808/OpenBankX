package com.cts.openbankx.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.APIUsageReport;
import com.cts.openbankx.service.APIUsageReportService;

@RestController
@RequestMapping("/api/v1/apiusage")
public class APIUsageReportController {
	
	@Autowired
	private APIUsageReportService service;
	
	
	@PostMapping
	public APIUsageReport register(@RequestBody APIUsageReport r) {
		return service.addAllData(r);
	}
	
	@GetMapping
	public List<APIUsageReport> getAll(){
		return service.getAllData();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<APIUsageReport>> getById(@PathVariable Long id){
		return service.getDataById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody APIUsageReport r){
		APIUsageReport api=service.update(id,r);
		if(api==null) {
			return ResponseEntity.status(404).body("Id not available");
		}
		return ResponseEntity.ok(api);
	}
	
	@DeleteMapping("/{id}")
	public String deleteId(@PathVariable Long id) {
		service.deleteData(id);
		return "Data deleted";
	}

}
