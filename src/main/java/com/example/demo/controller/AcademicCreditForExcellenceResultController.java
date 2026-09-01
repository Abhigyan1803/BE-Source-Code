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

import com.example.demo.model.AcademicCreditForExcellenceResult;
import com.example.demo.model.AcademicCreditForExcellenceSubject;
import com.example.demo.model.AcademicCreditForExcellenceSubjectResult;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.CreditExcellenceFilterPayload;
import com.example.demo.payload.CreditExcellencePayload;
import com.example.demo.service.AcademicCreditForExcellenceResultService;
import com.example.demo.service.AcademicCreditForExcellenceSubjectResultService;
import com.example.demo.service.AcademicCreditForExcellenceSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/academic-credit-for-excellence-result-controller")
@CrossOrigin
public class AcademicCreditForExcellenceResultController {
	@Autowired
	private AcademicCreditForExcellenceResultService academicCreditForExcellenceResultService;
	@Autowired
	private AcademicCreditForExcellenceSubjectResultService academicCreditForExcellenceSubjectResultService;

	@Autowired
	private AcademicCreditForExcellenceSubjectService academicCreditForExcellenceSubjectService;

	@PostMapping(value = "/add_academic_credit_for_excellence_result")
	public ResponseEntity<?> addAcademicCreditForExcellenceResult(
			@RequestBody AcademicCreditForExcellenceResult academicCreditForExcellenceResult) throws MyException {
		if (academicCreditForExcellenceResult != null) {

			List<AcademicCreditForExcellenceSubjectResult> academicCreditForExcellenceSubjectResult = academicCreditForExcellenceResult
					.getCreditExcellenceSubResult();
			if (academicCreditForExcellenceSubjectResult != null) {
				for (AcademicCreditForExcellenceSubjectResult CreditForExcellenceSubRslt : academicCreditForExcellenceSubjectResult) {
					academicCreditForExcellenceSubjectResultService.createSubResult(CreditForExcellenceSubRslt);
				}
			} else {
				return new ResponseEntity<>("Invalid credit for excellence subject Details ", HttpStatus.NO_CONTENT);
			}
			AcademicCreditForExcellenceResult response = academicCreditForExcellenceResultService
					.createAcademicCreditForExcellenceResult(academicCreditForExcellenceResult);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid Academic credit for excellence marks Details ", HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get_academic_credit_for_exellence_result_check")
	public ResponseEntity<?> getAcademicCreditForExcellenceResultById(@RequestParam String serviceId,
			@RequestParam int termId) {
		AcademicCreditForExcellenceResult response = academicCreditForExcellenceResultService
				.findByServiceIdAndTermId(serviceId, termId);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			List<AcademicCreditForExcellenceSubject> result = academicCreditForExcellenceSubjectService.getBystatus(1);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, result), HttpStatus.OK);
		}

	}

	@PutMapping(value = "/update-academic_credit_for_exellence_result")
	public ResponseEntity<?> updateAcademicCreditForExcellenceResult(
			@RequestBody AcademicCreditForExcellenceResult academicCreditForExcellenceResult) throws MyException {
		AcademicCreditForExcellenceResult response = academicCreditForExcellenceResultService
				.updateAcademicCreditForExcellenceResult(academicCreditForExcellenceResult);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get_all_cadet_credit_for_excellence_result_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		CreditExcellencePayload response = academicCreditForExcellenceResultService
				.getCadetsByTermIdAndBattaionAndCompany(termId, battalion, company, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/save_bulk_credit_for_excellence_result")
	public ResponseEntity<?> updateBulkAcademicOqMarkResult(
			@RequestBody List<CreditExcellenceFilterPayload> creditExcellencePayloadList) throws MyException {
		String response = academicCreditForExcellenceResultService
				.updateBulkAcademicCreditForExcellenceResult(creditExcellencePayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_credit_for_excellence_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam Long termId,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		CreditExcellencePayload response = academicCreditForExcellenceResultService.getCadetsBySearch(termId, serviceId,
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
