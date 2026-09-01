package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdossierPtResult;

@Repository
public interface EdossierPtResultRepository extends JpaRepository<EdossierPtResult, Long> {

	Optional<EdossierPtResult> findByServiceIdAndTermIdAndSubjectType(String serviceId, Long termId,
			String subjectType);

	List<EdossierPtResult> findByServiceIdOrderByTermId(String serviceId);

	List<EdossierPtResult> findByServiceIdAndSubjectTypeOrderByTermId(String serviceId, String subjectType);

}
