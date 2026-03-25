package com.cts.openbankx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Maps to table: User Columns: UserID, Name, Role, Email, Phone, Status
 */
@Entity
@Table(name = "Users") // exact table name as per your spec
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID") // exact column name
	private Long userId;

	@NotBlank
	@Column(name = "Name", nullable = false) // exact column name
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING) // store the enum text
	@Column(name = "Role", nullable = false, length = 20) // exact column name
	private Role role; // Customer/TPP/Operations/Admin

	@NotBlank
	@Email
	@Column(name = "Email", nullable = false, unique = true, length = 255) // exact
	private String email;

	@NotBlank
	@Column(name = "Phone", nullable = false, unique = true, length = 15) // exact
	private String phone;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Status", nullable = false, length = 10) // exact column name
	private Status status = Status.Active; // Active/Locked

	// ---- Required no-args constructor for JPA
	protected User() {
	}

	// ---- Optional convenience constructor
	public User(String name, Role role, String email, String phone, Status status) {
		this.name = name;
		this.role = role;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}

	// ---- Getters & Setters (Java field names can be camelCase; DB columns are
	// exact)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	// ---- Enums with exact labels (match your spec)
	public enum Role {
		Customer, TPP, Operations, Admin
	}

	public enum Status {
		Active, Locked
	}

<<<<<<< HEAD
}
=======
	public String getUserID() {
		// TODO Auto-generated method stub
		return null;
	}
}
>>>>>>> f13903c99553a308165b9b0e140d3c632674bb53
