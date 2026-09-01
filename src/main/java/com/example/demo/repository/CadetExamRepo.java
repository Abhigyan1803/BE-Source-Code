package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CadetOtherExams;

@Repository
public interface CadetExamRepo extends JpaRepository<CadetOtherExams,Long> {

	CadetOtherExams findByExamName(String examName);

}
