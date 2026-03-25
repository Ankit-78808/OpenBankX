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

import com.cts.openbankx.model.TPPApp;
import com.cts.openbankx.service.TPPAppService;

@RestController
@RequestMapping("/api/v1/apps")
public class TPPAppController {
	
	@Autowired
	private TPPAppService service;
	
	@PostMapping("/{tppId}")
	public ResponseEntity<TPPApp> register(@PathVariable Long tppId,@RequestBody TPPApp tppapp) {
		return ResponseEntity.status(201).body(service.registerData(tppId,tppapp));
	}
	
	@GetMapping
	public ResponseEntity<List<TPPApp>> getData(){
		return ResponseEntity.ok(service.getData());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDataById(@PathVariable Long id)
	{
		TPPApp fetch=service.getDataById(id);
		if(fetch==null) {
			return ResponseEntity.status(404).body("Id not available");
		}
		else
			return ResponseEntity.ok(fetch);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatedData(@PathVariable Long id,@RequestBody TPPApp tpp){
		TPPApp update=service.updatedData(id,tpp);
		if(update==null)
		{
			return ResponseEntity.status(404).body("TPPAppId Not Found");
		}
			return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteData(id);
		return ResponseEntity.noContent().build();
	}

}
