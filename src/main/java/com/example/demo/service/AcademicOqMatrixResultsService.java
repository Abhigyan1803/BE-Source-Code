package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.AcademicOqMatrixResult;
import com.example.demo.payload.OqMatrixFilterPayload;
import com.example.demo.payload.OqMatrixPayload;
import com.example.demo.payload.OqMatrixTermPayload;

public interface AcademicOqMatrixResultsService {

	AcademicOqMatrixResult createAcademicOqMarkResult(AcademicOqMatrixResult academicOqMatrixResult);

	// Set<CampSubjectDetails> getSubjectByTerm(Long termId);

	// CampMarksResult getCampMarkResultById(Long id);

	AcademicOqMatrixResult findByServiceIdAndTermIdAndTermType(String serviceId, int termId, String termType);

	// List<CampMarksResult> getCampMarkResultAll();

	// CampMarksResult updateCampMarkResult(CampMarksResult campMarksResult);

	// JSONObject findByServiceIdAndTermIdAndExerciseTypeId(String serviceId, int
	// termId, long exerciseTypeId)
	// throws Exception;

	AcademicOqMatrixResult updateAcademicOqMarkResult(AcademicOqMatrixResult academicOqMatrixResult);

	String updateBulkAcademicOqMarkResult(List<OqMatrixFilterPayload> oqMatrixPayloadList);

	OqMatrixPayload getCadetsBySearch(Long termId, String termType, String serviceId, Pageable pageable);

	OqMatrixTermPayload findOqMatrixDrillEqtn(String serviceId);

	OqMatrixPayload getCadetsByTermIdAndTermTypeAndBattaionAndCompany(Long termId, String termType, String battalion,
			String company, String serviceId, Pageable pageable);

	OqMatrixTermPayload findOqMatrixDrillEqtnAndTermType(String serviceId, String termType);

}
