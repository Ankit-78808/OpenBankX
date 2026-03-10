package com.cts.openbankx.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.openbankx.model.Consent;

public interface ConsentRepository extends JpaRepository<Consent, Long>{

}
