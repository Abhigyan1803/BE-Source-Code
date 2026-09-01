package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdAssessmentOqFinal;

@Repository
public interface EdAssessmentOqFinalRepository extends JpaRepository<EdAssessmentOqFinal, Long> {

	EdAssessmentOqFinal findByServiceIdAndTermId(String serviceId, Long termId);

	List<EdAssessmentOqFinal> findByServiceId(String serviceId);

}
