package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicLeadershipMatrixResult;

@Repository
public interface AcademicLeadershipMatrixResultRepo extends JpaRepository<AcademicLeadershipMatrixResult, Long> {

	Optional<AcademicLeadershipMatrixResult> findByServiceIdAndTermId(String serviceId, int termId);

	List<AcademicLeadershipMatrixResult> findByServiceId(String serviceId);

	List<AcademicLeadershipMatrixResult> findByServiceIdOrderByTermId(String serviceId);

}
