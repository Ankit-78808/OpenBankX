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

import com.cts.openbankx.model.TPP;
import com.cts.openbankx.service.TPPService;

@RestController
@RequestMapping("api/v1/tpp")
public class TPPController {
	
	@Autowired
	private TPPService service;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerTPP(@RequestBody TPP tpp) {
		try {
		TPP savedTPP=service.register(tpp);
		return ResponseEntity.status(201).body(savedTPP);
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(409).body("TPP already exists");
		}
	}
	
	@GetMapping
	public ResponseEntity<List<TPP>> getALLTPP(){
		return ResponseEntity.ok(service.getAllData());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<TPP> getById(@PathVariable Long id){
		TPP fetch=service.getData(id);
		if(fetch==null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(fetch);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTPP(@PathVariable Long id,@RequestBody TPP tpp){
		TPP updated=service.updateData(id,tpp);
		if(updated==null) {
			return ResponseEntity.status(404).body("TPP Not Found");
		}
		else
			return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTPP(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.ok().body("Id deleted");
	}
	
}
