package com.cts.openbankx.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.openbankx.enums.PlanEnvironment;
import com.cts.openbankx.model.APIPlan;

public interface APIPlanRepository extends JpaRepository<APIPlan, Long> {
    
   
    List<APIPlan> findByProduct_ProductId(UUID productId);
    
    List<APIPlan> findByEnvironment(PlanEnvironment environment);
    
   
    List<APIPlan> findByProduct_ProductIdAndEnvironment(UUID productId, PlanEnvironment environment);
}