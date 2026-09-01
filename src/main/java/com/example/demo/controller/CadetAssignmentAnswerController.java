package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CadetAssignmentAnswer;
import com.example.demo.service.CadetAssignmentAnswerService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController("/api/cadet_assignment_answer")
public class CadetAssignmentAnswerController {
	@Autowired
	private CadetAssignmentAnswerService service;

	@PostMapping("/add_cadet_assignment_answer")
	public ResponseEntity<?> addCadetAssignmentAnswer(@RequestBody CadetAssignmentAnswer cadetAssignmentAnswer) {
		CadetAssignmentAnswer response1 = service.getByAcdAsnIdAndServiceId(cadetAssignmentAnswer.getAcdAsnId(),
				cadetAssignmentAnswer.getServiceId());
		if (response1 == null) {
			CadetAssignmentAnswer response = service.addCadetAssignmentAnswer(cadetAssignmentAnswer);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping("/get_cadet_assignment_answer_by_id")
	public ResponseEntity<?> getById(Long id) {
		CadetAssignmentAnswer response = service.getById(id);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_cadet_assignment_answer_by_service_id")
	public ResponseEntity<?> getAcademicAssignment(@RequestParam String serviceId, @RequestParam Integer status) {
		java.util.List<CadetAssignmentAnswer> response = service.getCadetAssignmentAnswerByServiceId(serviceId, status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_cadet_assignment_answer_by_academic_assignment_id_status")
	public ResponseEntity<?> getAcademicAssignmentByAcdAsnId(@RequestParam Long acdAsnId,
			@RequestParam Integer status) {
		java.util.List<CadetAssignmentAnswer> response = service.getCadetAssignmentAnswerByAcdAsnId(acdAsnId, status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_cadet_assignment_answer_by_status")
	public ResponseEntity<?> getByStatus(Integer status) {
		List<CadetAssignmentAnswer> response = service.getByStatus(status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PutMapping("/update_cadet_assignment_answer")
	public ResponseEntity<?> updateCadetAssignmentAnswer(@RequestBody CadetAssignmentAnswer cadetAssignmentAnswer) {
		CadetAssignmentAnswer response = service.updateCadetAssignmentAnswer(cadetAssignmentAnswer);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}
}