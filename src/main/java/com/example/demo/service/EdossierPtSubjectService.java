package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EdossierPtSubject;

public interface EdossierPtSubjectService {

	List<EdossierPtSubject> getPtSubjectList(Integer status, String subjectType, Long termId);

	EdossierPtSubject getSubjectById(Long subjectId);

	List<EdossierPtSubject> getAllSubjectByStatus(Integer i);

	// EdossierPtSubjectResult getSubResultById(Long id);

	// EdossierPtSubjectResult updateSubResult(EdossierPtSubjectResult
	// edossierPtSubjectResult);

}
