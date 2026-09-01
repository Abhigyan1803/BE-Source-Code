package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CampSubjectDetails;

@Repository
public interface CampSubjectDetailsRepo extends JpaRepository<CampSubjectDetails, Long> {

	List<CampSubjectDetails> findByStatus(Integer status);

	CampSubjectDetails findBySubjectName(String subjectName);

	List<CampSubjectDetails> findByStatusOrderByIdDesc(Integer status);

	List<CampSubjectDetails> findByStatusAndStatusNotIn(Integer status, Integer[] deletedStatus);

	List<CampSubjectDetails> findAllByStatusNotIn(Integer[] deletedStatus);

}
