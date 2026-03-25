package com.cts.openbankx.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.ApiGateWay;
import com.cts.openbankx.model.TPPApp;

public interface ApiGateWayRepo extends JpaRepository<ApiGateWay, Long>
{
	
	//List<ApiGateWay> findByTppApp_TppAppID(Long tppAppId);
	
}
