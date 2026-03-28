package com.cts.openbankx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cts.openbankx.gateway.ApiGatewayFilter;
import com.cts.openbankx.security.SystemSecurityFilter;

@Configuration
@EnableWebSecurity
// ✅ Method security is unused but harmless if present
// @EnableMethodSecurity
public class SecurityConfig {

    private final SystemSecurityFilter systemSecurityFilter;
    private final ApiGatewayFilter apiGatewayFilter;

    public SecurityConfig(SystemSecurityFilter systemSecurityFilter,
                          ApiGatewayFilter apiGatewayFilter) {
        this.systemSecurityFilter = systemSecurityFilter;
        this.apiGatewayFilter = apiGatewayFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth ->
                    auth.anyRequest().permitAll()
            )
            // ✅ System-managed security FIRST
            .addFilterBefore(
                    systemSecurityFilter,
                    UsernamePasswordAuthenticationFilter.class
            )
            // ✅ Gateway enforcement AFTER
            .addFilterAfter(
                    apiGatewayFilter,
                    SystemSecurityFilter.class
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}