package com.cts.openbankx.service;

import com.cts.openbankx.enums.NotificationStatus;
import com.cts.openbankx.model.Notification;
import com.cts.openbankx.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository repo;

    public NotificationService(NotificationRepository repo) {
        this.repo = repo;
    }

    public List<Notification> findAll() { return repo.findAll(); }
    public Notification findById(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Notification not found: " + id)); }
    public List<Notification> findByRecipient(String recipientId) { return repo.findByRecipientId(recipientId); }
    public List<Notification> findUnreadByRecipient(String recipientId) { return repo.findByRecipientIdAndStatus(recipientId, NotificationStatus.UNREAD); }

    public Notification send(Notification n) {
        n.setStatus(NotificationStatus.UNREAD);
        if (n.getCreatedDate() == null) n.setCreatedDate(LocalDateTime.now());
        return repo.save(n);
    }

    public Notification markRead(Long id) {
        Notification n = findById(id);
        n.setStatus(NotificationStatus.READ);
        return repo.save(n);
    }

    public Notification dismiss(Long id) {
        Notification n = findById(id);
        n.setStatus(NotificationStatus.DISMISSED);
        return repo.save(n);
    }
}
