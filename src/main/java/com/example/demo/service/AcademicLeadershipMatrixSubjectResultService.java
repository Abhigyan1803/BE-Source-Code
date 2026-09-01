package com.example.demo.service;

import com.example.demo.model.AcademicLeadershipMatrixSubjectResult;

public interface AcademicLeadershipMatrixSubjectResultService {

	AcademicLeadershipMatrixSubjectResult createSubResult(AcademicLeadershipMatrixSubjectResult leadershipSubRslt);

	// Set<CampSubjectDetails> getSubjectByTerm(Long termId);

	AcademicLeadershipMatrixSubjectResult getSubResultById(Long id);

	// List<AcademicLeadershipMatrixSubjectResult> getSubResultAll();

//	CampSubjectResult updateSubResult(CampSubjectResult campSubjectResult);

	// AcademicLeadershipMatrixResult findByServiceIdAndTermId(String serviceId, int
	// termId);

	AcademicLeadershipMatrixSubjectResult updateSubResult(
			AcademicLeadershipMatrixSubjectResult academicLeadershipMatrixSubjectResult);

//	AcademicLeadershipMatrixSubjectResult getSubResultById(Long id);

}
