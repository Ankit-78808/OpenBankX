package com.cts.openbankx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.openbankx.model.Notification;
import com.cts.openbankx.service.NotificationService;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationsController {
	private final NotificationService service;

	public NotificationsController(NotificationService service) {
		this.service = service;
	}

	@GetMapping
	public List<Notification> getAll() {
		return service.findAll();
	}

	@GetMapping("/recipient/{recipientId}")
	public List<Notification> getByRecipient(@PathVariable String recipientId) {
		return service.findByRecipient(recipientId);
	}

	@GetMapping("/recipient/{recipientId}/unread")
	public List<Notification> getUnread(@PathVariable String recipientId) {
		return service.findUnreadByRecipient(recipientId);
	}

	@PostMapping
	public Notification send(@RequestBody Notification notification) {
		return service.send(notification);
	}

	@PutMapping("/{id}/read")
	public Notification markRead(@PathVariable Long id) {
		return service.markRead(id);
	}

	@PutMapping("/{id}/dismiss")
	public Notification dismiss(@PathVariable Long id) {
		return service.dismiss(id);
	}
}
