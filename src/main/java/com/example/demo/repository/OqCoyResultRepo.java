package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CadetCampCoyCdrResult;
import com.example.demo.model.CadetCampPlCdrResult;
import com.example.demo.model.OqCoyResult;

@Repository
public interface OqCoyResultRepo extends JpaRepository<OqCoyResult,Long> {

	Optional<OqCoyResult> findByServiceIdAndTermIdAndOqSubjectAttributeId(String serviceId, Long termId, Integer id);

	List<OqCoyResult> findByServiceIdAndTermId(String serviceId, Long termId);

	
	
}
