package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CampSubjectResult;

public interface CampSubjectResultService {

	CampSubjectResult createSubResult(CampSubjectResult campSubjectResult);

	// Set<CampSubjectDetails> getSubjectByTerm(Long termId);

	CampSubjectResult getSubResultById(Long id);

	List<CampSubjectResult> getSubResultAll();

	CampSubjectResult updateSubResult(CampSubjectResult campSubjectResult);

}
