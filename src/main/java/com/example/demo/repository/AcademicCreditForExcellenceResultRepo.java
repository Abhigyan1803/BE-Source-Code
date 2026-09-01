package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicCreditForExcellenceResult;
import com.example.demo.model.AcademicCreditForExcellenceSubjectResult;

@Repository
public interface AcademicCreditForExcellenceResultRepo extends JpaRepository<AcademicCreditForExcellenceResult, Long> {

	Optional<AcademicCreditForExcellenceResult> findByServiceIdAndTermId(String serviceId, int termId);

	AcademicCreditForExcellenceResult save(AcademicCreditForExcellenceSubjectResult creditForExcellenceSubjectResult);

}