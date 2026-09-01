package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CampMarksResult;
import com.example.demo.model.CampSubjectDetails;
import com.example.demo.model.CampSubjectResult;
import com.example.demo.model.GcAppt;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.CampMarksFilterPayload;
import com.example.demo.payload.CampMarksPayload;
import com.example.demo.payload.CampMarksRouteRunBack;
import com.example.demo.service.CampMarksResultsService;
import com.example.demo.service.CampSubjectDetailsService;
import com.example.demo.service.CampSubjectResultService;
import com.example.demo.service.GcApptService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/campMarksResultController")
@CrossOrigin
public class CampMarksResultController {

	@Autowired
	private CampMarksResultsService campMarksResultsService;

	@Autowired
	private CampSubjectResultService campSubjectResultService;

	@Autowired
	private GcApptService gcApptService;

	@Autowired
	CampSubjectDetailsService campSubjectDetailsService;

	@PostMapping(value = "/add-campMarkResult")
	public ResponseEntity<?> addCampMarkResult(@RequestBody CampMarksResult campMarksResult) throws MyException {
		if (!campMarksResult.toString().isEmpty()) {

			if (!campMarksResult.getCampSubjectResult().toString().isEmpty()) {
				List<CampSubjectResult> CampSubResult = campMarksResult.getCampSubjectResult();
				for (CampSubjectResult campSubRslt : CampSubResult) {
					campSubjectResultService.createSubResult(campSubRslt);
				}
			} else {
				return new ResponseEntity<>("Invalid Camp subject Details ", HttpStatus.NO_CONTENT);
			}
			CampMarksResult response = campMarksResultsService.createCampMarkResult(campMarksResult);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CAMP_MARKS_ADDED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid Camp marks Details ", HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get-camp-mark-result-by-id")
	public ResponseEntity<?> getCampMarkRsltById(@RequestParam String serviceId, @RequestParam int termId) {
		Optional<CampMarksResult> list = campMarksResultsService.findByServiceIdAndTermId(serviceId, termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-camp-mark-result-check")
	public ResponseEntity<?> getCampMarkRsltcheck(@RequestParam String serviceId, @RequestParam int termId,
			@RequestParam long exerciseTypeId) throws Exception {
		JSONObject campMarksResults = campMarksResultsService.findByServiceIdAndTermIdAndExerciseTypeId(serviceId,
				termId, exerciseTypeId);
		if (campMarksResults != null) {
			HashMap<String, Object> yourHashMap = new Gson().fromJson(campMarksResults.toString(), HashMap.class);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, yourHashMap),
					HttpStatus.OK);
		} else {
			List<CampSubjectDetails> list = campSubjectDetailsService.getAllSubjectByStatus(1);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, list), HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get-gc-appt-all")
	public ResponseEntity<?> getGcApptAll() throws Exception {
		List<GcAppt> gcApptList = gcApptService.getGcAppt();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, gcApptList),
				HttpStatus.OK);
	}

	@PutMapping(value = "/update-camp_marks_result")
	public ResponseEntity<?> updateCampMarksResult(@RequestBody CampMarksResult campMarksResult) throws MyException {
		CampMarksResult response = campMarksResultsService.updateCampMarkResult(campMarksResult);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CAMP_MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-camp-mark-result-by-service-id")
	public ResponseEntity<?> getCampMarkRsltByServiceId(@RequestParam String serviceId) {
		List<CampMarksResult> list = campMarksResultsService.findByServiceId(serviceId);
		if (list != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/update-camp_marks_ruoteMarch_runback")
	public ResponseEntity<?> updateCampMarksRouteMarchRunbackResult(
			@RequestBody CampMarksRouteRunBack campMarksRouteMarchRunback) throws MyException {
		CampMarksRouteRunBack response = campMarksResultsService
				.updateCampMarksRouteMarchRunback(campMarksRouteMarchRunback);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_campmarksresult_by_termId_battalion_company")
	public ResponseEntity<?> getCampMarkRsltByTermIdAndBattaionAndCompanyAndExerciseTypeId(
			@RequestParam(required = false) Long termId, @RequestParam(required = false) String battalion,
			@RequestParam(required = false) String company, @RequestParam(required = false) String serviceId,
			@RequestParam Integer pageNo, @RequestParam Long exerciseTypeId, @RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		CampMarksPayload response = campMarksResultsService.getCadetsByTermIdAndBattaionAndCompanyAndExerciseTypeId(
				termId, battalion, company, serviceId, exerciseTypeId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/save_bulk_camp_marks_leadership_matrix_result")
	public ResponseEntity<?> updateBulkCampMarkResult(
			@RequestBody List<CampMarksFilterPayload> campMarksFilterPayloadList) throws MyException {
		String response = campMarksResultsService.updateBulkCampMarksResult(campMarksFilterPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_camp_marks_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam Long termId,
			@RequestParam(required = false) String serviceId, @RequestParam Long exerciseTypeId,
			@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		CampMarksPayload response = campMarksResultsService.getCadetsBySearch(termId, serviceId, exerciseTypeId,
				pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}
}
