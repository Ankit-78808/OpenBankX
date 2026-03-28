package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.User;
import com.cts.openbankx.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public List<User> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public User getById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public User create(@RequestBody User user) {
		return service.save(user);
	}

	@PutMapping("/{id}")
	public User update(@PathVariable Long id, @RequestBody User user) {
		User existingUser = service.findById(id);

		// Map updated fields
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPhone(user.getPhone());
		existingUser.setRole(user.getRole());
		existingUser.setStatus(user.getStatus());
		return service.save(existingUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
