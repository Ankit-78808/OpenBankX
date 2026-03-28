package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.enums.IncidentStatus;
import com.cts.openbankx.model.Incident;
import com.cts.openbankx.service.IncidentService;

@RestController
@RequestMapping("/api/v1/incidents")
public class IncidentController {

	private final IncidentService incidentService;

	public IncidentController(IncidentService incidentService) {
		this.incidentService = incidentService;
	}

	// ✅ Create new incident
	@PostMapping
	public Incident createIncident(@RequestBody Incident incident) {
		return incidentService.save(incident);
	}

	// ✅ Get all incidents
	@GetMapping
	public List<Incident> getAllIncidents() {
		return incidentService.findAll();
	}

	// ✅ Get incident by ID
	@GetMapping("/{id}")
	public Incident getIncident(@PathVariable Long id) {
		return incidentService.findById(id);
	}

	// ✅ Get incidents by status
	@GetMapping("/status/{status}")
	public List<Incident> getByStatus(@PathVariable IncidentStatus status) {
		return incidentService.findByStatus(status);
	}

	// ✅ Mitigate incident
	@PutMapping("/{id}/mitigate")
	public Incident mitigateIncident(@PathVariable Long id) {
		return incidentService.mitigate(id);
	}

	// ✅ Close incident
	@PutMapping("/{id}/close")
	public Incident closeIncident(@PathVariable Long id) {
		return incidentService.close(id);
	}
}