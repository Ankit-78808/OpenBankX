package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

}
