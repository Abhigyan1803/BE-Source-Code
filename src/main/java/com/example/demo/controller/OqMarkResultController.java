package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OqBnResult;
import com.example.demo.model.OqCoyResult;
import com.example.demo.model.OqMarksResult;
import com.example.demo.model.OqPlResult;
import com.example.demo.model.OqSubjectDetails1;
import com.example.demo.model.OqSubjectResult;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.OqMarksFilterPayload;
import com.example.demo.payload.OqMarksPayload;
import com.example.demo.service.OqBnResultService;
import com.example.demo.service.OqCoyResultService;
import com.example.demo.service.OqMarksResultService;
import com.example.demo.service.OqPlResultService;
import com.example.demo.service.OqSubjectDetailsService1;
import com.example.demo.service.OqSubjectResultService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/oqMarkResultController")
public class OqMarkResultController {

	@Autowired
	OqPlResultService oqPlResultService;

	@Autowired
	OqCoyResultService oqCoyResultService;

	@Autowired
	OqBnResultService oqBnResultService;

	@Autowired
	private OqSubjectResultService oqSubjectResultService;

	@Autowired
	private OqMarksResultService oqMarksResultService;

	@Autowired
	private OqSubjectDetailsService1 oqSubjectDetailsService1;

	@PostMapping(value = "/saveOqPlMarks")
	public ResponseEntity<?> addOqPlResult(@RequestBody List<OqPlResult> result) throws MyException {
		List<OqPlResult> response = oqPlResultService.addOqPlResult(result);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getOqPlResult")
	public ResponseEntity<?> getOqPlResult(@RequestParam String serviceId, @RequestParam Long termId) {
		List<OqPlResult> response = oqPlResultService.getOqPlResult(serviceId, termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "updateOqPlResult")
	public ResponseEntity<?> updateOqPlResult(@RequestBody List<OqPlResult> result) throws MyException {
		List<OqPlResult> response = oqPlResultService.updateResult(result);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/saveOqCoyMarks")
	public ResponseEntity<?> addOqCoyResult(@RequestBody List<OqCoyResult> result) throws MyException {
		List<OqCoyResult> response = oqCoyResultService.addOqCoyResult(result);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getOqCoyResult")
	public ResponseEntity<?> getOqCoyResult(@RequestParam String serviceId, @RequestParam Long termId) {
		List<OqCoyResult> response = oqCoyResultService.getOqCoyResult(serviceId, termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "updateOqCoyResult")
	public ResponseEntity<?> updateOqCoyResult(@RequestBody List<OqCoyResult> result) throws MyException {
		List<OqCoyResult> response = oqCoyResultService.updateResult(result);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/saveOqBnMarks")
	public ResponseEntity<?> addOqBnResult(@RequestBody List<OqBnResult> result) throws MyException {
		List<OqBnResult> response = oqBnResultService.addOqBnResult(result);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getOqBnResult")
	public ResponseEntity<?> getOqBnResult(@RequestParam String serviceId, @RequestParam Long termId) {
		List<OqBnResult> response = oqBnResultService.getOqBnResult(serviceId, termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "updateOqBnResult")
	public ResponseEntity<?> updateBnResult(@RequestBody List<OqBnResult> result) throws MyException {
		List<OqBnResult> response = oqBnResultService.updateResult(result);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/add-oqMarkResult")
	public ResponseEntity<?> addOqMarkResult(@RequestBody OqMarksResult oqMarksResult) throws MyException {
		if (!oqMarksResult.toString().isEmpty()) {

			if (!oqMarksResult.getOqSubjectResult().toString().isEmpty()) {
				List<OqSubjectResult> oqSubjectResult = oqMarksResult.getOqSubjectResult();
				for (OqSubjectResult oqSubjectRslt : oqSubjectResult) {
					oqSubjectResultService.createSubResult(oqSubjectRslt);
				}
			} else {
				return new ResponseEntity<>("Invalid Oq subject Details ", HttpStatus.NO_CONTENT);
			}
			OqMarksResult response = oqMarksResultService.createOqMarkResult(oqMarksResult);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OQ_MARKS_ADDED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid Oq marks Details ", HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get-oq-mark-result-check")
	public ResponseEntity<?> getOqMarkRsltcheck(@RequestParam String serviceId, @RequestParam int termId,
			@RequestParam long entryTypeId) throws Exception {
		OqMarksResult oqMarkResultResponse = oqMarksResultService.findByServiceIdAndTermIdAndEntryTypeId(serviceId,
				termId, entryTypeId);
		if (oqMarkResultResponse != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, oqMarkResultResponse), HttpStatus.OK);
		} else {

			List<OqSubjectDetails1> list = oqSubjectDetailsService1.getAllSubjectByStatus(1);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, list), HttpStatus.OK);
		}

	}

	@PutMapping(value = "/update-oq-mark-result")
	public ResponseEntity<?> updateOqMarkResult(@RequestBody OqMarksResult oqMarksResult) throws MyException {
		OqMarksResult response = oqMarksResultService.updateOqMarkResult(oqMarksResult);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OQ_MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-oq-marks-result-by-serviceid")
	public ResponseEntity<?> getOqMarksRsltCheck(@RequestParam String serviceId) throws Exception {
		List<OqMarksResult> oqMarkResultResponse = oqMarksResultService.findByServiceIdOrderBySubjectId(serviceId);
		if (oqMarkResultResponse != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, oqMarkResultResponse),
					HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_oq-marks_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam Long entryTypeId, @RequestParam(required = false) String serviceId,
			@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		OqMarksPayload response = oqMarksResultService.getCadetsByTermIdAndBattaionAndCompanyAndEntryTypeId(termId,
				battalion, company, entryTypeId, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/save_bulk_oq_marks_result")
	public ResponseEntity<?> updateBulkOqMarksResult(@RequestBody List<OqMarksFilterPayload> orMarksPayloadList)
			throws MyException {
		String response = oqMarksResultService.updateBulkOqMarksResult(orMarksPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_oq_marks_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam(required = false) String serviceId,
			@RequestParam Long termId, @RequestParam Long entryTypeId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		OqMarksPayload response = oqMarksResultService.getCadetsBySearch(serviceId, termId, entryTypeId, pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}
}
