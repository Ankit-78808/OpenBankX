package com.cts.openbankx.controller;

import java.util.List;

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

import com.cts.openbankx.model.Incident;
import com.cts.openbankx.service.IncidentService;

@RestController
@RequestMapping("/api/v1/incident")
public class IncidentController {
	
	@Autowired
	private IncidentService service;
	
	@PostMapping
	public Incident register(@RequestBody Incident incident) {
		return service.registerData(incident);
		
	}
	@GetMapping
	public List<Incident> getAllData(){
		return service.getAllData();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getElementById(@PathVariable Long id){
		Incident in=service.getElementById(id);
		
		if(in==null) {
			return ResponseEntity.status(404).body("Id not available");
		}
		return ResponseEntity.ok(in);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateData(@PathVariable Long id,@RequestBody Incident incident){
		Incident in=service.updateData(id,incident);
		
		if(in==null)
			return ResponseEntity.status(404).build();
		return ResponseEntity.ok(in);
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {
		service.deleteData(id);
		return "Data Deleted";
	}
	

}
