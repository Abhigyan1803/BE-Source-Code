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

import com.example.demo.model.IntellectualSkillsResult;
import com.example.demo.model.IntellectualSkillsSubject;
import com.example.demo.model.IntellectualSkillsSubjectResult;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.IntellectualSkillsFilterPayload;
import com.example.demo.payload.IntellectualSkillsPayload;
import com.example.demo.service.IntellectualSkillsResultService;
import com.example.demo.service.IntellectualSkillsSubjectResultService;
import com.example.demo.service.IntellectualSkillsSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/intellectual_skills_result_controller")
@CrossOrigin
public class IntellectualSkillsResultController {
	@Autowired
	private IntellectualSkillsResultService intellectualSkillsResultService;
	@Autowired
	private IntellectualSkillsSubjectResultService intellectualSkillsSubjectResultService;
	@Autowired
	private IntellectualSkillsSubjectService intellectualSkillsSubjectService;

	@PostMapping(value = "/add_intellectualskills_result")
	public ResponseEntity<?> addIntellectualSkillsResult(@RequestBody IntellectualSkillsResult intellectualSkillsResult)
			throws MyException {

		if (intellectualSkillsResult != null) {
			List<IntellectualSkillsSubjectResult> intellectualSkillsSubjectResult = intellectualSkillsResult
					.getIntellectualSkillsSubResult();
			if (intellectualSkillsSubjectResult != null) {
				for (IntellectualSkillsSubjectResult intellectualSkillsSubResult : intellectualSkillsSubjectResult) {
					intellectualSkillsSubjectResultService.createSubResult(intellectualSkillsSubResult);
				}
			} else {
				return new ResponseEntity<>("Invalid credit for excellence subject Details ", HttpStatus.NO_CONTENT);
			}
			IntellectualSkillsResult response = intellectualSkillsResultService
					.createIntellectualSkillsResult(intellectualSkillsResult);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid trg eqtn marks Details ", HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get_intellectualSkills_result_check")
	public ResponseEntity<?> getIntellectualSkillsResultById(@RequestParam String serviceId,
			@RequestParam Long termId) {
		IntellectualSkillsResult response = intellectualSkillsResultService.findByServiceIdAndTermId(serviceId, termId);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			List<IntellectualSkillsSubject> result = intellectualSkillsSubjectService.getByStatusAndTermId(1, termId);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, result), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-intellectualSkills_result")
	public ResponseEntity<?> updateIntellectualSkillsResult(
			@RequestBody IntellectualSkillsResult intellectualSkillsResult) throws MyException {
		IntellectualSkillsResult response = intellectualSkillsResultService
				.updateIntellectualSkillsResult(intellectualSkillsResult);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get_intellectualSkills_result_by_service_id")
	public ResponseEntity<?> getIntellectualSkillsResulttsResultById(@RequestParam String serviceId) {
		List<IntellectualSkillsResult> response = intellectualSkillsResultService.findByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_intellectualSkills_result_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		IntellectualSkillsPayload response = intellectualSkillsResultService
				.getCadetsByTermIdAndBattaionAndCompany(termId, battalion, company, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/save_bulk_intellectualSkills_result")
	public ResponseEntity<?> updateBulkIntellectualSkillsResult(
			@RequestBody List<IntellectualSkillsFilterPayload> intellectualSkillsPayloadList) throws MyException {
		String response = intellectualSkillsResultService
				.updateBulkIntellectualSkillsResult(intellectualSkillsPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_intellectualSkills_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam(required = false) String serviceId,
			@RequestParam Long termId, @RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		IntellectualSkillsPayload response = intellectualSkillsResultService.getCadetsBySearch(serviceId, termId,
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
