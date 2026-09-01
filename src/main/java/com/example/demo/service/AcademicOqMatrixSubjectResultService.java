package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AcademicOqMatrixSubjectResult;

public interface AcademicOqMatrixSubjectResultService {

	AcademicOqMatrixSubjectResult createSubResult(AcademicOqMatrixSubjectResult oqSubRslt);

	// Set<CampSubjectDetails> getSubjectByTerm(Long termId);

	AcademicOqMatrixSubjectResult getSubResultById(Long id);

	// List<CampSubjectResult> getSubResultAll();

	AcademicOqMatrixSubjectResult updateSubResult(AcademicOqMatrixSubjectResult academicOqMatrixSubjectResult);

	List<AcademicOqMatrixSubjectResult> getSubResultAll();

}
