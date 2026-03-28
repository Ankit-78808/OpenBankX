package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.enums.TPPStatus;
import com.cts.openbankx.model.TPP;
import com.cts.openbankx.service.TPPAppService;
import com.cts.openbankx.service.TPPService;

@RestController
@RequestMapping("/api/v1/tpp")
@PreAuthorize("hasRole('ADMIN')")
public class TPPController {

	private final TPPService tppService;
	private final TPPAppService tppAppService;

	public TPPController(TPPService tppService, TPPAppService tppAppService) {
		this.tppService = tppService;
		this.tppAppService = tppAppService;
	}

	// --- Register TPP (Sandbox) ---
	@PostMapping("/register")
	public TPP registerTPP(@RequestBody TPP tpp) {
		tpp.setStatus(TPPStatus.SANDBOX);
		return tppService.save(tpp);
	}

	// --- Get all TPPs ---
	@GetMapping
	public List<TPP> getAllTPPs() {
		return tppService.findAll();
	}

	// --- Get TPP by id ---
	@GetMapping("/{id}")
	public TPP getTPP(@PathVariable Long id) {
		return tppService.findById(id);
	}

	// --- Update TPP (partial update supported) ---
	@PutMapping("/{id}")
	public TPP updateTPP(@PathVariable Long id, @RequestBody TPP request) {
		return tppService.updateTPP(id, request);
	}

	// --- Delete TPP ---
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTPP(@PathVariable Long id) {
		tppService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// --- Get apps by TPP ---
//    @GetMapping("/{tppId}/apps")
//    public List<TPPApp> getAppsByTPP(@PathVariable Long tppId) {
//        return tppAppService.findByTpp(tppId);
//    }
}
