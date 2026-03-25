package com.cts.openbankx.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.openbankx.dto.ApiGatewayDTO;
import com.cts.openbankx.model.ApiGateWay;
import com.cts.openbankx.model.AuthClient;
import com.cts.openbankx.model.TPPApp;
import com.cts.openbankx.repository.ApiGateWayRepo;
import com.cts.openbankx.repository.AuthClientRepository;
import com.cts.openbankx.repository.TPPAppRepository;

@Service
public class ApiGateWayService
{
	@Autowired
	private ApiGateWayRepo repo;
	
	@Autowired
	private TPPAppRepository tppAppRepo;
	
	@Autowired
	private AuthClientRepository authClientRepo;
	

	public ApiGateWay create(ApiGatewayDTO dto)
	{
		TPPApp tppApp = tppAppRepo.findById(dto.getTPPAppID()).orElseThrow(()->new RuntimeException("TPPApp not found with ID:"+dto.getTPPAppID()));
		
		AuthClient authClient = authClientRepo.findById(dto.getClientId()).orElseThrow(()->new RuntimeException("AuthClient not found with ID:"+dto.getClientId()));
		
		ApiGateWay log = new ApiGateWay();
		
		log.setTppApp(tppApp);
		log.setAuthclient(authClient);
		log.setEndPoint(dto.getEndPoint());
		log.setMethod(dto.getMethod());
		log.setStatusCode(dto.getStatusCode());
		log.setLatencyMs(dto.getLatencyMs());
		log.setTimestamp(LocalDateTime.now());
		
		return repo.save(log);
	}
	
	public List<ApiGateWay> getAll()
	{
		return repo.findAll();
	}
	
	public ApiGateWay getById(Long id)
	{
	    return repo.findById(id)
	               .orElseThrow(() -> new RuntimeException("ApiGateway record not found with ID: " + id));
	}
		
	
}
	
	
