package com.cts.openbankx.model;

import java.time.LocalDateTime;

import com.cts.openbankx.enums.NotificationRecipientType;
import com.cts.openbankx.enums.NotificationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Notification")
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notificationId;
	
	@Enumerated(EnumType.STRING)
	private NotificationRecipientType recipientType;
	
	private Long recipientId;
	
	private String message;
	
	@Enumerated(EnumType.STRING)
	private NotificationRecipientType category;
	
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;
	
	private LocalDateTime createdDate;

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public NotificationRecipientType getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(NotificationRecipientType recipientType) {
		this.recipientType = recipientType;
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotificationRecipientType getCategory() {
		return category;
	}

	public void setCategory(NotificationRecipientType category) {
		this.category = category;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
}
