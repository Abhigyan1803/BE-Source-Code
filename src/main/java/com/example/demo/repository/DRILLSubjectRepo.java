package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DRILLSubject;

@Repository
public interface DRILLSubjectRepo extends JpaRepository<DRILLSubject, Long> {

	List<DRILLSubject> findByStatus(Integer status);

	List<DRILLSubject> findByTermId(Long termid);

	DRILLSubject findBySubjectNameAndTermId(String subjectName, Long termId);

	List<DRILLSubject> findByTermIdAndStatus(Long termid, Integer status);

	List<DRILLSubject> findByTermIdOrderById(Long termid);

	List<DRILLSubject> findByStatusAndStatusNotIn(Integer status, Integer[] deletedStatus);

	List<DRILLSubject> findAllByStatusNotIn(Integer[] deletedStatus);

}
