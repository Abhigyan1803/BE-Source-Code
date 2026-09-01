package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.AcademicLeadershipMatrixResult;
import com.example.demo.payload.LeadershipFilterPayload;
import com.example.demo.payload.LeadershipPayload;

public interface AcademicLeadershipMatrixResultService {

	AcademicLeadershipMatrixResult createAcademicLeadershipMatrixResult(
			AcademicLeadershipMatrixResult academicLeadershipMatrixResult);

	// Set<CampSubjectDetails> getSubjectByTerm(Long termId);

	// CampMarksResult getCampMarkResultById(Long id);

	AcademicLeadershipMatrixResult findByServiceIdAndTermId(String serviceId, int termId);

	// List<CampMarksResult> getCampMarkResultAll();

	AcademicLeadershipMatrixResult updateAcademicLeadershipMatrixResult(
			AcademicLeadershipMatrixResult academicLeadershipMatrixResult);

	List<AcademicLeadershipMatrixResult> findByServiceId(String serviceId);

	LeadershipPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			String serviceId, Pageable pageable);

	String updateBulkAcademicLeadershipMatrixResult(List<LeadershipFilterPayload> leadershipPayloadList);

	LeadershipPayload getCadetsBySearch(Long termId, String serviceId, Pageable pageable);

//	JSONObject findByServiceIdAndTermIdAndExerciseTypeId(String serviceId, int termId, long exerciseTypeId)
//			throws Exception;

}
