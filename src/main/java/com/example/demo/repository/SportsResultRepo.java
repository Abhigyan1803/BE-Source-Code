package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SportsResult;

@Repository
public interface SportsResultRepo extends JpaRepository<SportsResult, Long> {

	// Optional<SportsResult> findByServiceIdAndTermId(String serviceId, Long
	// termId);

	List<SportsResult> findByServiceId(String serviceId);

	Optional<SportsResult> findByServiceIdAndTermIdAndTermSession(String serviceId, Long termId, String termSession);

	List<SportsResult> findByServiceIdOrderByTermId(String serviceId);

}
