package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicTerm;

@Repository
public interface AcademicTermRepository extends JpaRepository<AcademicTerm, Long> {

	List<AcademicTerm> findAllByPaperAndTermIdAndSubjectNameOrderByAcademicTermIdDesc(String paper, Long termId,
			String subjectName);

	List<AcademicTerm> findAllByPaperAndSubjectNameOrderByAcademicTermIdDesc(String paper, String subjectName);

	List<AcademicTerm> findAllByPaperAndTermIdAndSubjectNameAndStatusNotInOrderByAcademicTermIdDesc(String paper,
			Long termId, String subjectName, Integer[] status);

	List<AcademicTerm> findAllByPaperAndSubjectNameAndStatusNotInOrderByAcademicTermIdDesc(String paper,
			String subjectName, Integer[] status);


	Optional<AcademicTerm> findByAcademicTermIdAndStatus(Long id, Integer status);
}
