package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OqSubjectDetails1;

@Repository
public interface OqSubjectDetailsRepo1 extends JpaRepository<OqSubjectDetails1, Long> {

	List<OqSubjectDetails1> findByStatus(Integer status);

	OqSubjectDetails1 findBySubjectName(String subjectName);

}
