package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.APILog;
import com.cts.openbankx.service.APILogService;

@RestController
@RequestMapping("/api/v1/logs")
@PreAuthorize("hasRole('ADMIN')")
public class APILogController {

	private final APILogService logService;

	public APILogController(APILogService logService) {
		this.logService = logService;
	}

	/* READ ALL */
	@GetMapping
	public List<APILog> getAllLogs() {
		return logService.findAll();
	}

	/* READ BY ID */
	@GetMapping("/{id}")
	public APILog getLogById(@PathVariable Long id) {
		return logService.findById(id);
	}

	/* READ BY APP */
	@GetMapping("/apps/{appId}")
	public List<APILog> getLogsByApp(@PathVariable Long appId) {
		return logService.findByTppApp(appId);
	}

	/* DELETE (optional, admin only) */
	@DeleteMapping("/{id}")
	public void deleteLog(@PathVariable Long id) {
		logService.delete(id);
	}
}
