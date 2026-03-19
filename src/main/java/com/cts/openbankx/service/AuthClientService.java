package com.cts.openbankx.service;

import com.cts.openbankx.dto.TokenRequest;
import com.cts.openbankx.dto.TokenResponse;
import com.cts.openbankx.model.AuthClient;
import com.cts.openbankx.model.Token;
import com.cts.openbankx.repository.AuthClientRepository;
import com.cts.openbankx.repository.TokenRepository;
import com.cts.openbankx.security.JWTUtil;

import io.jsonwebtoken.lang.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthClientService {
	
	@Autowired
	private TokenRepository tokenrepo;
	
	@Autowired
	private JWTUtil jwtutil;

    private final AuthClientRepository repo;

    public AuthClientService(AuthClientRepository repo) {
        this.repo = repo;
    }

    public AuthClient create(AuthClient client) {
        return repo.save(client);
    }

    public AuthClient getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<AuthClient> getAll() {
        return repo.findAll();
    }

    public AuthClient update(Long id, AuthClient input) {
        return repo.findById(id).map(existing -> {
            existing.setTppAppId(input.getTppAppId());
            existing.setClientType(input.getClientType());
            existing.setRedirectUris(input.getRedirectUris());
            existing.setScopesAllowed(input.getScopesAllowed());
            existing.setStatus(input.getStatus());
            return repo.save(existing);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
    
    public TokenResponse generateToken(TokenRequest request) {
        
       

        
        AuthClient client = repo.findById(request.getClient_id())
                .orElseThrow(() -> new RuntimeException("Invalid client: " + request.getClient_id()));

     
        if (client.getStatus() != AuthClient.Status.Active) {
            throw new RuntimeException("Client is inactive");
        }

        
        List<String> uris = java.util.Arrays.stream(client.getRedirectUris().split(","))
                                .map(String::trim) 
                                .toList();

        if (!uris.contains(request.getRedirect_uri().trim())) {
            throw new RuntimeException("Invalid redirect URI. Expected one of: " + uris);
        }

        String scope = client.getScopesAllowed();

        String accessToken = jwtutil.generateToken(client.getClientId().toString(), scope);


        Token token = new Token();
        token.setClient(client);
        token.setUser(null);
        token.setTokenType(Token.TokenType.Access);
        token.setScope(scope);
        token.setIssuedAt(java.time.Instant.now());
        token.setExpiresAt(java.time.Instant.now().plusSeconds(3600)); 
        token.setStatus(Token.Status.Active);
        
        tokenrepo.save(token);

        return new TokenResponse(
                accessToken, 
                Token.TokenType.Access, 
                3600, 
                scope
            );
    }
}