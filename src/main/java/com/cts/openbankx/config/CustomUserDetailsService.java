package com.cts.openbankx.config;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.openbankx.model.User;
import com.cts.openbankx.repository.UserRepository;

/**
 * Custom UserDetailsService implementation. Spring Security uses this to load
 * user data during authentication. The "username" here is the user's email
 * address.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), // ✅ Now
																											// exists
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));
	}
}
