package com.cts.openbankx.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.APIProduct;

public interface APIProductRepository extends JpaRepository<APIProduct, UUID> {

}
