package com.cts.openbankx.config;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT Authentication Filter.
 *
 * This filter runs BEFORE every request: 1. Checks if the request has an
 * "Authorization: Bearer <token>" header 2. If yes, validates the token and
 * sets the security context 3. If no, lets the request pass through (might be
 * an unauthenticated endpoint)
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
	private final JwtUtil jwtUtil;

	public JwtAuthFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Step 1: Get the Authorization header
		String authHeader = request.getHeader("Authorization");

		// Step 2: Check if it starts with "Bearer "
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7); // Remove "Bearer " prefix

			// Step 3: Validate the token
			if (jwtUtil.validateToken(token)) {
				String email = jwtUtil.getEmailFromToken(token);
				String role = jwtUtil.getRoleFromToken(token);

				// Step 4: Create authentication object and set it in Security Context
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,
						null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				logger.debug("Authenticated user: {} with role: {}", email, role);
			} else {
				logger.warn("Invalid JWT token received");
			}
		}

		// Step 5: Continue the filter chain
		filterChain.doFilter(request, response);
	}
}
