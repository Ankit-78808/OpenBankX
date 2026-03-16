package com.cts.openbankx.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.enums.ProductStatus;
import com.cts.openbankx.model.APIProduct;

public interface APIProductRepository extends JpaRepository<APIProduct, UUID> {
	
	List<APIProduct> findByStatus(ProductStatus status);
	boolean existsByName(String name);
	
	
	

}
