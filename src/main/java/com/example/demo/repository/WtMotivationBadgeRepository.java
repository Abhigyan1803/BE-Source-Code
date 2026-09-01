package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.WtMotivationBadge;

@Repository
public interface WtMotivationBadgeRepository extends JpaRepository<WtMotivationBadge, Long> {

	WtMotivationBadge findByServiceIdAndTermId(String serviceId, Long termId);

	// List<WtMotivationBadge> findByServiceIdOrderById(String serviceId);

	Optional<WtMotivationBadge> findByServiceId(String serviceId);

}
