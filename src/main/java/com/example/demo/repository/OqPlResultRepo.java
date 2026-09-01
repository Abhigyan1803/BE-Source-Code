package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.OqPlResult;


@Repository
public interface OqPlResultRepo extends JpaRepository<OqPlResult,Long>{

	List<OqPlResult> findByServiceIdAndTermId(String serviceId, Long termId);

	Optional<OqPlResult> findByServiceIdAndTermIdAndOqSubjectAttributeId(String serviceId, Long termId, Integer id);

	
	
}
