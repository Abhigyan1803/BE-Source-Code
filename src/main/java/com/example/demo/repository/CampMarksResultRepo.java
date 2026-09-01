package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CampMarksResult;

@Repository
public interface CampMarksResultRepo extends JpaRepository<CampMarksResult, Long> {

	Optional<CampMarksResult> findByServiceIdAndTermId(String serviceId, int termId);

	Optional<CampMarksResult> findByServiceIdAndTermIdAndExerciseTypeId(String serviceId, int termId,
			long exerciseTypeId);

	List<CampMarksResult> findByServiceIdOrderByTermId(String serviceId);

}
