package com.cts.openbankx.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

  

	private static final String SECRET = "openbankx-secret-key-32-char-long";
    private static final long EXPIRATION_TIME = 3600000; 

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String client_id, String scope) {
        return Jwts.builder()
                .subject(client_id)                     
                .claim("scope", scope)                 
                .issuedAt(new Date())                  
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())            
                .compact();
    }
}