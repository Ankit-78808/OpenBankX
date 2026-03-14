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
@RequestMapping("/api/v1/tppapp")
public class TPPAppController {
	
	@Autowired
	private TPPAppService service;
	
	@PostMapping
	public TPPApp register(@RequestBody TPPApp tppapp) {
		return service.registerData(tppapp);
	}
	
	@GetMapping
	public List<TPPApp> getData(){
		return service.getData();
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
	public String delete(@PathVariable Long id) {
		service.deleteData(id);
		return "Data deleted";
	}

}
