package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AcademicAssignment;
import com.example.demo.service.AcademicAssignmentService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController("/api/acadmic_assignment")
public class AcademicAssignmentController {
	@Autowired
	private AcademicAssignmentService service;

	@PostMapping("/add_academic_assignment")
	public ResponseEntity<?> addAcademicAssignment(@RequestBody AcademicAssignment academicAssignment) {
		AcademicAssignment response = service.addAcademicAssignment(academicAssignment);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get_academic_assignment_By_Id")
	public ResponseEntity<?> getById(Long id) {
		AcademicAssignment response = service.getById(id);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_academic_assignment_By_termId_status")
	public ResponseEntity<?> getByTermId(@RequestParam Long termId, @RequestParam Integer status) {
		List<AcademicAssignment> response = service.getByTermId(termId, status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_academic_Assignment_list_by_paper_type_termId")
	public ResponseEntity<?> getAcademicAssignment(@RequestParam String paper, @RequestParam String assignmentType,
			@RequestParam Long termId, @RequestParam Integer status) {
		java.util.List<AcademicAssignment> response = service.getAcademicAssignment(paper, assignmentType, termId,
				status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_academic_Assignment_list_by_paper_type_termId_serviceId")
	public ResponseEntity<?> getAcademicAssignmentAndAnswer(@RequestParam String paper,
			@RequestParam String assignmentType, @RequestParam Long termId, @RequestParam Integer status,
			String serviceId) {
		java.util.List<AcademicAssignment> response = service.getAcademicAssignmentAndAnswer(paper, assignmentType,
				termId, status, serviceId);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping("/update_academic_assignment")
	public ResponseEntity<?> updateAcademicAssignment(AcademicAssignment academicAssignment) {
		AcademicAssignment response = service.updateAcademicAssignment(academicAssignment);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

}
