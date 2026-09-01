package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicExamination;

@Repository
public interface AcademicExaminationRepository extends JpaRepository<AcademicExamination, Long> {

	List<AcademicExamination> findAllByTypeAndTermIdAndStatusOrderByIdDesc(String type, Long termId, Integer status);

	List<AcademicExamination> findAllByTypeAndTermIdOrderByIdDesc(String type, Long termId);
}
