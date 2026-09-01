package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicSyllabus;

@Repository
public interface AcademicSyllabusRepository extends JpaRepository<AcademicSyllabus, Long> {

	List<AcademicSyllabus> findAllByTermIdAndStatusOrderByIdDesc(Long termId, Integer status);

	List<AcademicSyllabus> findAllByStatusOrderByIdDesc(Integer status);

	List<AcademicSyllabus> findAllByOrderByIdDesc();

	List<AcademicSyllabus> findAllByTermIdAndPaperAndSubjectAndStatusOrderByIdDesc(Long termId, String paper,String subject, Integer status);

	List<AcademicSyllabus> findAllByTermIdAndPaperAndSubjectOrderByIdDesc(Long termId, String paper, String subject);

}
