package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.ConsentEvent;

public interface ConsentEventRepository extends JpaRepository<ConsentEvent, Long> {

}
