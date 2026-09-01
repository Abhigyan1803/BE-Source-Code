package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicAssignment;

@Repository
public interface AcademicAssignmentRepository extends JpaRepository<AcademicAssignment, Long> {

	List<AcademicAssignment> findAllByPaperAndAssignmentTypeAndTermIdAndStatusOrderByIdDesc(String paper,
			String assignmentType, Long termId, Integer status);

	List<AcademicAssignment> findAllByPaperAndAssignmentTypeAndTermIdOrderByIdDesc(String paper, String assignmentType,
			Long termId);

	// Optional<AcademicAssignment> findByTermId(Long termId);

	List<AcademicAssignment> findByTermIdAndStatusOrderByIdDesc(Long termId, Integer status);
}
