package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.model.SportsResult;
import com.example.demo.model.SportsSubject;
import com.example.demo.model.SportsSubjectResult;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.SportsFilterPayload;
import com.example.demo.payload.SportsPayload;
import com.example.demo.service.SportsResultService;
import com.example.demo.service.SportsSubjectResultService;
import com.example.demo.service.SportsSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/sports-result-controller")
@CrossOrigin
public class SportsResultController {
	@Autowired
	private SportsResultService sportsResultService;
	@Autowired
	private SportsSubjectResultService sportsSubjectResultService;
	@Autowired
	private SportsSubjectService sportsSubjectService;

	@PostMapping(value = "/add_sports_result")
	public ResponseEntity<?> addSportsResult(@RequestBody SportsResult sportsResult) throws MyException {

		if (sportsResult != null) {
			List<SportsSubjectResult> sportsSubjectResult = sportsResult.getSportsSubResult();
			if (sportsSubjectResult != null) {
				for (SportsSubjectResult sportsSubResult : sportsSubjectResult) {
					sportsSubjectResultService.createSubResult(sportsSubResult);
				}
			} else {
				return new ResponseEntity<>("Invalid credit for excellence subject Details ", HttpStatus.NO_CONTENT);
			}
			SportsResult response = sportsResultService.createSportsResult(sportsResult);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid trg eqtn marks Details ", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get_sports_result_check")
	public ResponseEntity<?> getSportsResultById(@RequestParam String serviceId, @RequestParam Long termId,
			@RequestParam String termSession) {
		SportsResult response = sportsResultService.findByServiceIdAndTermIdAndTermSession(serviceId, termId,
				termSession);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			List<SportsSubject> result = sportsSubjectService.getByStatusAndTermSession(1, termSession);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, result), HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_sports_result_by_service_id")
	public ResponseEntity<?> getSportsResultById(@RequestParam String serviceId) {
		List<SportsResult> response = sportsResultService.findByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-sports_result")
	public ResponseEntity<?> updateSportsResult(@RequestBody SportsResult sportsResult) throws MyException {
		SportsResult response = sportsResultService.updateSportsResult(sportsResult);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get_all_cadet_sports_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsSportsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam String termSession, @RequestParam(required = false) String battalion,
			@RequestParam(required = false) String company, @RequestParam(required = false) String serviceId,
			@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		SportsPayload response = sportsResultService.getCadetsSportsByTermIdAndBattaionAndCompany(termId, termSession,
				battalion, company, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_academic_leadership_matrix_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam Long termId, String termSession,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		SportsPayload response = sportsResultService.getCadetsBySearch(termId, termSession, serviceId, pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/save_bulk_sports_result")
	public ResponseEntity<?> updateBulkSportsResult(@RequestBody List<SportsFilterPayload> sportsPayloadList)
			throws MyException {
		String response = sportsResultService.updateBulkSportsResult(sportsPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

}
