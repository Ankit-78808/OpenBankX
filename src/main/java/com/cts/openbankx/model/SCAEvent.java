package com.cts.openbankx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/*SCAEvent 
o SCAEventID 
o UserID 
o Method (OTP/Device/App) 
o Result (Pass/Fail) 
o EventTime 
o ReferenceID (ConsentID/PaymentID) 
*/
@Entity
@Table(name = "SCAEvent") // exact table name
public class SCAEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SCAEventID") // exact column name
	private Long scaEventID;

	// -------- FOREIGN KEY: SCAEvent.UserID -> User.UserID --------
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID", // FK column in SCAEvent table
			referencedColumnName = "UserID", // PK column in User table
			foreignKey = @ForeignKey(name = "FK_SCAEvent_User"))
	private User user;

	// Method (OTP/Device/App)
	public enum Method {
		OTP, Device, App
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Method", nullable = false, length = 20)
	private Method method;

	// Result (Pass/Fail)
	public enum Result {
		Pass, Fail
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Result", nullable = false, length = 10)
	private Result result;

	// Auto-set event time at insert (UTC). Remove @CreationTimestamp if you want to
	// set manually.
	@CreationTimestamp
	@Column(name = "EventTime", nullable = false, updatable = false)
	private Instant eventTime;

	// ReferenceID (ConsentID/PaymentID)
	@NotBlank
	@Column(name = "ReferenceID", nullable = false, length = 100)
	private String referenceID;

	// ----- Constructors -----
<<<<<<< HEAD
	public SCAEvent() {
=======
	protected SCAEvent() {
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
	}

	public SCAEvent(Long scaEventID, User user, Method method, Result result, Instant eventTime, String referenceID) {
		this.scaEventID = scaEventID; // normally JPA sets this; include only if you must
		this.user = user;
		this.method = method;
		this.result = result;
		this.eventTime = eventTime; // normally @CreationTimestamp sets this
		this.referenceID = referenceID;
	}

	// ----- Getters & Setters -----
	public Long getScaEventID() {
		return scaEventID;
	}

	public void setScaEventID(Long scaEventID) {
		this.scaEventID = scaEventID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Instant getEventTime() {
		return eventTime;
	}

	public void setEventTime(Instant eventTime) {
		this.eventTime = eventTime;
	} // usually not needed when using @CreationTimestamp

	public String getReferenceID() {
		return referenceID;
	}

	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
