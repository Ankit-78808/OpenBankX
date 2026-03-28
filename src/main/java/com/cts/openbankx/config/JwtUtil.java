package com.cts.openbankx.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final String secret;
	private final long expirationMs;

	public JwtUtil(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration-ms}") long expirationMs) {
		this.secret = secret;
		this.expirationMs = expirationMs;
	}

	/** Generate JWT */
	public String generateToken(String email, String role) {
		return Jwts.builder().setSubject(email) // ✅ OLD API
				.claim("role", role).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationMs))
				.signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8)).compact();
	}

	/** Extract email */
	public String getEmailFromToken(String token) {
		return getClaims(token).getSubject();
	}

	/** Extract role */
	public String getRoleFromToken(String token) {
		return getClaims(token).get("role", String.class);
	}

	/** Validate JWT */
	public boolean validateToken(String token) {
		try {
			getClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)) // ✅ OLD API
				.parseClaimsJws(token).getBody();
	}
}