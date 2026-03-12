package com.cts.openbankx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public TPP registerTPP(@RequestBody TPP tpp) {
		return service.registerTPP(tpp);
	}

}
