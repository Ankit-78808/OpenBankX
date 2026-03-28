package com.cts.openbankx.repository;

import com.cts.openbankx.model.APIProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APIProductRepository extends JpaRepository<APIProduct, Long> {
}
