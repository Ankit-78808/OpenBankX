package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.RateLimit;
//import com.cts.openbankx.repository.RateLimitRepo;
import com.cts.openbankx.service.RateLimitService;

@RestController
@RequestMapping("/api/v1")
public class RateLimitController
{

	    @Autowired
	    private RateLimitService service;

	    // CREATE
	    @PostMapping("/create")
	    public RateLimit create(@RequestBody RateLimit rl) {
	        return service.create(rl);
	    }

	    // READ by ID
	    @GetMapping("/getId/{id}")
	    public RateLimit getById(@PathVariable Long id) {
	        return service.getById(id);
	    }

	    // READ all
	    @GetMapping("/get")
	    public List<RateLimit> getAll() {
	        return service.getAll();
	    }

//	    // UPDATE
//	    @PutMapping("/{id}")
//	    public RateLimit update(@PathVariable Long id, @RequestBody RateLimit rl) {
//	        return service.update(id, rl);
//	    }

	    // DELETE
	    @DeleteMapping("/deleteID/{id}")
	    public String delete(@PathVariable Long id) {
	        service.delete(id);
	        return "Deleted RateLimit with id: " + id;
	    }
	}

