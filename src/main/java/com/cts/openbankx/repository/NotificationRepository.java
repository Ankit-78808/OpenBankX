package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
