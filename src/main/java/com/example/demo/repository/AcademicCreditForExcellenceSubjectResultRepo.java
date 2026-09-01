package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicCreditForExcellenceSubjectResult;

@Repository
public interface AcademicCreditForExcellenceSubjectResultRepo
		extends JpaRepository<AcademicCreditForExcellenceSubjectResult, Long> {

}
