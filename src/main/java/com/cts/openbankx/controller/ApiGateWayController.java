package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.dto.ApiGatewayDTO;
import com.cts.openbankx.model.ApiGateWay;
import com.cts.openbankx.service.ApiGateWayService;

@RestController
@RequestMapping("/api/v1/apps")
public class ApiGateWayController 
{
	
	@Autowired
	private ApiGateWayService service;
	
	@PostMapping("/post")
	public ApiGateWay create(@RequestBody ApiGatewayDTO dto)
	{
		return service.create(dto);
	}
	
	
	@GetMapping("/getAll")
	public List<ApiGateWay> getAll()
	{
		return service.getAll();
	}
	
	@GetMapping("/{id}/stats")
	public ApiGateWay getById(@PathVariable("id") Long id)
	{
	    return service.getById(id);
	}
	
}
