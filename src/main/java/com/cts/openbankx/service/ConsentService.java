package com.cts.openbankx.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.openbankx.enums.ConsentEventType;
import com.cts.openbankx.enums.ConsentStatus;
import com.cts.openbankx.enums.PerformedBy;
import com.cts.openbankx.model.Consent;
import com.cts.openbankx.model.ConsentEvent;
import com.cts.openbankx.model.TPPApp;
import com.cts.openbankx.model.User;
import com.cts.openbankx.repository.ConsentEventRepository;
import com.cts.openbankx.repository.ConsentRepository;
import com.cts.openbankx.repository.TPPAppRepository;
import com.cts.openbankx.repository.UserRepository;

@Service
public class ConsentService {

	private final ConsentRepository consentRepo;
	private final ConsentEventRepository eventRepo;
	private final UserRepository userRepo;
	private final TPPAppRepository tppAppRepo;

	// ✅ Constructor Injection
	public ConsentService(ConsentRepository consentRepo, ConsentEventRepository eventRepo, UserRepository userRepo,
			TPPAppRepository tppAppRepo) {
		this.consentRepo = consentRepo;
		this.eventRepo = eventRepo;
		this.userRepo = userRepo;
		this.tppAppRepo = tppAppRepo;
	}

	// ✅ Get all consents
	public List<Consent> findAll() {
		return consentRepo.findAll();
	}

	// ✅ Get by ID
	public Consent findById(Long id) {
		return consentRepo.findById(id).orElseThrow(() -> new RuntimeException("Consent not found with id: " + id));
	}

	// ✅ Get by User
	public List<Consent> findByUser(Long userId) {
		return consentRepo.findByUser_UserId(userId);
	}

	// ✅ Get by TPP App
	public List<Consent> findByTppApp(Long tppAppId) {
		return consentRepo.findByTppApp_TppAppId(tppAppId);
	}

	// ✅ CREATE CONSENT (🔥 FIXED METHOD)
	public Consent create(Consent c) {

		// 🔥 Fetch existing USER (important fix)
		User user = userRepo.findById(c.getUser().getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		// 🔥 Fetch existing TPP APP (important fix)
		TPPApp tppApp = tppAppRepo.findById(c.getTppApp().getTppAppId())
				.orElseThrow(() -> new RuntimeException("TPP App not found"));

		// 🔥 Attach managed entities
		c.setUser(user);
		c.setTppApp(tppApp);

		// ✅ Set system fields
		c.setCreatedDate(LocalDateTime.now());
		c.setStatus(ConsentStatus.ACTIVE);

		// ✅ Save consent
		Consent saved = consentRepo.save(c);

		// ✅ Log event
		logEvent(saved, ConsentEventType.CREATE, PerformedBy.USER, "Consent created");

		return saved;
	}

	// ✅ REVOKE CONSENT
	public Consent revoke(Long id, PerformedBy by) {
		Consent c = findById(id);

		c.setStatus(ConsentStatus.REVOKED);

		Consent saved = consentRepo.save(c);

		logEvent(saved, ConsentEventType.REVOKE, by, "Consent revoked");

		return saved;
	}

	// ✅ LOG EVENT (no change needed)
	private void logEvent(Consent c, ConsentEventType type, PerformedBy by, String notes) {
		ConsentEvent evt = new ConsentEvent();
		evt.setConsent(c);
		evt.setEventType(type);
		evt.setEventDate(LocalDateTime.now());
		evt.setPerformedBy(by);
		evt.setNotes(notes);

		eventRepo.save(evt);
	}
}
