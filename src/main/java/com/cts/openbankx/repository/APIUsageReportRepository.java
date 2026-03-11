package com.cts.openbankx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.openbankx.model.APIUsageReport;

public interface APIUsageReportRepository extends JpaRepository<APIUsageReport, Long> {

}
