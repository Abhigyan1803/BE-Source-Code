package com.example.demo.service;

import java.util.List;

import com.example.demo.model.OqSubjectDetails1;

public interface OqSubjectDetailsService1 {

	OqSubjectDetails1 createSubject(OqSubjectDetails1 oqSubjectDetails1);

	// Set<CampSubjectDetails> getSubjectByTerm(Long termId);

	OqSubjectDetails1 getSubjectById(Long id);

	List<OqSubjectDetails1> getAllSubjectByStatus(Integer status);

	OqSubjectDetails1 updateSubject(OqSubjectDetails1 oqSubjectDetails1);

	OqSubjectDetails1 findBySubject(String subjectName);

	OqSubjectDetails1 validateSubject(OqSubjectDetails1 oqSubjectDetails1);

}
