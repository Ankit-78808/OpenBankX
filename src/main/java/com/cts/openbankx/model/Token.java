package com.cts.openbankx.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;
/*TokenID 
o ClientID 
o UserID 
o TokenType (Access/Refresh) 
o Scope 
o IssuedAt 
o ExpiresAt 
o Status (Active/Revoked) */

@Entity
@Table(name = "Token") // keep exact table name
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TokenID")
	private Long tokenID;

	// --------- FOREIGN KEYS (ManyToOne) ----------
	// Token.ClientID -> AuthClient.ClientID (FK)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ClientID", // FK column in Token table
			referencedColumnName = "ClientID", // PK column in AuthClient
			foreignKey = @ForeignKey(name = "FK_Token_AuthClient"))
	private AuthClient client;

	// Token.UserID -> User.UserID (FK)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID", // FK column in Token table
			referencedColumnName = "UserID", // PK column in User
			foreignKey = @ForeignKey(name = "FK_Token_User"))
	private User user;

	// ---------------------------------------------

	public enum TokenType {
		Access, Refresh
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TokenType", nullable = false, length = 20)
	private TokenType tokenType;

	@Column(name = "Scope", nullable = false, length = 255)
	private String scope;

	@CreationTimestamp
	@Column(name = "IssuedAt", nullable = false, updatable = false)
	private Instant issuedAt;

	@Column(name = "ExpiresAt", nullable = false)
	private Instant expiresAt;

	public enum Status {
		Active, Revoked
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "Status", nullable = false, length = 20)
	private Status status = Status.Active;

	// ---- Constructors
	protected Token() {
	}

	public Token(AuthClient client, User user, TokenType tokenType, String scope,Instant issuedAt, Instant expiresAt, Status status) {
		this.client = client;
		this.user = user;
		this.tokenType = tokenType;
		this.scope = scope;
		this.issuedAt = issuedAt;
		this.expiresAt = expiresAt;
		this.status = status;
	}

	// ---- Getters & Setters
	public Long getTokenID() {
		return tokenID;
	}

	public void setTokenID(Long tokenID) {
		this.tokenID = tokenID;
	}

	public AuthClient getClient() {
		return client;
	}

	public void setClient(AuthClient client) {
		this.client = client;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Instant getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Instant issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Instant getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}