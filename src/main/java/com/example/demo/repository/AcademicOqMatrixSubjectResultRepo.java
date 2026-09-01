package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicOqMatrixSubjectResult;

@Repository
public interface AcademicOqMatrixSubjectResultRepo extends JpaRepository<AcademicOqMatrixSubjectResult, Long> {

}
