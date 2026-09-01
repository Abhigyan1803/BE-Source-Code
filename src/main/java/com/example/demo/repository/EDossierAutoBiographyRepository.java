package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EDossierAutoBiography;

@Repository
public interface EDossierAutoBiographyRepository extends JpaRepository<EDossierAutoBiography, Long> {

	Optional<EDossierAutoBiography> findByServiceId(String serviceId);

//	List<AcademicAssignment> findAllByPaperAndAssignmentTypeAndTermIdAndStatusOrderByIdDesc(String paper,
//			String assignmentType, Long termId, Integer status);
//
//	List<AcademicAssignment> findAllByPaperAndAssignmentTypeAndTermIdOrderByIdDesc(String paper, String assignmentType,
//			Long termId);
//
//
//	List<AcademicAssignment> findByTermIdAndStatusOrderByIdDesc(Long termId, Integer status);
}
