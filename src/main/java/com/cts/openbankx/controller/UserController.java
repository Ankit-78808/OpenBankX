//UserController.java
package com.cts.openbankx.controller;

import com.cts.openbankx.model.User;
import com.cts.openbankx.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	// CREATE
	@PostMapping
	public ResponseEntity<Map<String, Object>> create(@RequestBody User user) {

		User saved = service.create(user);

		// 🔥 Very simple response body
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Register Successful");
		response.put("user", saved);

<<<<<<< HEAD
		return ResponseEntity.created(URI.create("/api/users/" + saved.getUserId())).body(response);
=======
		return ResponseEntity.created(URI.create("/api/users/" + saved.getUserID())).body(response);
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
	}

	// READ by ID
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
		User user = service.getById(id);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Fetch Successful");
		response.put("user", user);

		return ResponseEntity.ok(response);
	}

	// READ all
	@GetMapping
	public ResponseEntity<Map<String, Object>> getAll() {
		List<User> users = service.getAll();

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Fetch All Successful");
		response.put("users", users);

		return ResponseEntity.ok(response);
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody User input) {

		User updated = service.update(id, input);

		if (updated == null) {
			return ResponseEntity.notFound().build();
		}

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Update Successful");
		response.put("user", updated);

		return ResponseEntity.ok(response);
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
		boolean deleted = service.delete(id);

		if (!deleted) {
			return ResponseEntity.notFound().build();
		}

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Delete Successful");
		response.put("deletedUserId", id);

		return ResponseEntity.ok(response);
	}
}