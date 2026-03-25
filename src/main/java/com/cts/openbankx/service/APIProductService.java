package com.cts.openbankx.service;

import com.cts.openbankx.enums.ProductStatus;
import com.cts.openbankx.model.APIProduct;
import com.cts.openbankx.repository.APIProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class APIProductService {

    private APIProductRepository repository;

    public APIProduct createProduct(APIProduct product) {
    	
    	if(repository.existsByName(product.getName())) {
    		throw new RuntimeException("Product with name already exists" +product.getName());
    	}
        return repository.save(product);
    }
   
    @Transactional(readOnly=true)
    public List<APIProduct> getAllProducts() {
        return repository.findAll();
    }

    public APIProduct getProductById(UUID productid) {
        return repository.findById(productid)
        		.orElseThrow(()-> new RuntimeException("API not Found" +productid));
    }
    
    public List<APIProduct> getByStatus(ProductStatus status){
    	return repository.findByStatus(status);
    }
    
    public APIProduct updateProduct(UUID productid,APIProduct updated) {
    	APIProduct existing=getProductById(productid);
    	existing.setName(updated.getName());
    	existing.setDescription(updated.getDescription());
    	existing.setEndpointsJSON(updated.getEndpointsJSON());
    	existing.setStatus(updated.getStatus());
    	return repository.save(existing);
    }
    
    public APIProduct updateStatus(ProductStatus status, UUID productid) {
       
        APIProduct existing = getProductById(productid);
        existing.setStatus(status);
        return repository.save(existing); 
    }
    
    public void deleteProduct(UUID productid) {
    	repository.deleteById(productid);
    }
}
