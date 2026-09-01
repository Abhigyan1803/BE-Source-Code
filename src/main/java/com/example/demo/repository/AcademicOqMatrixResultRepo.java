package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicOqMatrixResult;

@Repository
public interface AcademicOqMatrixResultRepo extends JpaRepository<AcademicOqMatrixResult, Long> {

	Optional<AcademicOqMatrixResult> findByServiceIdAndTermId(String serviceId, int termId);

	List<AcademicOqMatrixResult> findByServiceIdOrderByTermId(String serviceId);

	Optional<AcademicOqMatrixResult> findByServiceIdAndTermIdAndTermType(String serviceId, int termId, String termType);

	List<AcademicOqMatrixResult> findByServiceIdAndTermTypeOrderByTermId(String serviceId, String string);

	// Optional<AcademicOqMatrixResult>
	// findByServiceIdAndTermIdAndExerciseTypeId(String serviceId, int termId,
	// long exerciseTypeId);

}
