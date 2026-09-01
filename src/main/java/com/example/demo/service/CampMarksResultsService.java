package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.CampMarksResult;
import com.example.demo.payload.CampMarksFilterPayload;
import com.example.demo.payload.CampMarksPayload;
import com.example.demo.payload.CampMarksRouteRunBack;

public interface CampMarksResultsService {

	CampMarksResult createCampMarkResult(CampMarksResult campMarksResult);

	// Set<CampSubjectDetails> getSubjectByTerm(Long termId);

	// CampMarksResult getCampMarkResultById(Long id);

	Optional<CampMarksResult> findByServiceIdAndTermId(String serviceId, int termId);

	// List<CampMarksResult> getCampMarkResultAll();

	CampMarksResult updateCampMarkResult(CampMarksResult campMarksResult);

	JSONObject findByServiceIdAndTermIdAndExerciseTypeId(String serviceId, int termId, long exerciseTypeId)
			throws Exception;

	List<CampMarksResult> findByServiceId(String serviceId);

	CampMarksRouteRunBack updateCampMarksRouteMarchRunback(CampMarksRouteRunBack campMarksRouteMarchRunback);

	CampMarksPayload getCadetsByTermIdAndBattaionAndCompanyAndExerciseTypeId(Long termId, String battalion,
			String company, String serviceId, Long exerciseTypeId, Pageable pageable);

	String updateBulkCampMarksResult(List<CampMarksFilterPayload> campMarksFilterPayloadList);

	CampMarksPayload getCadetsBySearch(Long termId, String serviceId, Long exerciseTypeId, Pageable pageable);

}
