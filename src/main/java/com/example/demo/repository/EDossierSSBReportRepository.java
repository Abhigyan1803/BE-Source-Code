package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EDossierSSBReport;

@Repository
public interface EDossierSSBReportRepository extends JpaRepository<EDossierSSBReport, Long> {

	Optional<EDossierSSBReport> findByServiceId(String serviceId);

}
