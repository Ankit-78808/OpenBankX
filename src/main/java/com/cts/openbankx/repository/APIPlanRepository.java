package com.cts.openbankx.repository;

import com.cts.openbankx.model.APIPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface APIPlanRepository extends JpaRepository<APIPlan, Long> {
    List<APIPlan> findByApiProduct_ProductId(Long productId);
}
