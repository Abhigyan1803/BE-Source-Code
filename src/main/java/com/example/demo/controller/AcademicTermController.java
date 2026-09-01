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

import com.example.demo.model.AcademicTerm;
import com.example.demo.service.AcademicTermService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin("*")
@RestController("acadmic_Term_Controller")
public class AcademicTermController {
	@Autowired
	private AcademicTermService service;

	@PostMapping("/add_academic_subject")
	public ResponseEntity<?> addAcademicTerm(@RequestBody AcademicTerm academicTerm) {
		AcademicTerm response = service.addAcademicTerm(academicTerm);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get_academic_subject_By_Id")
	public ResponseEntity<?> getById(Long id) {
		AcademicTerm response = service.getById(id);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_academic_subject_List")
	public ResponseEntity<?> getAcademicTermList1(@RequestParam String paper,
			@RequestParam (required = false) Long termId, //Akash V1 03/08/2023
			@RequestParam String subjectName) {
		List<AcademicTerm> response = service.getAcademicTermList(paper, 	termId, subjectName);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	//Akash V1 04/08/2023
//	@GetMapping("/get_academic_subject_List_by_termId_and_subjectName")
//	public ResponseEntity<?> getAcademicTermListByTermIdAndSubject(@RequestParam String paper,
//			@RequestParam Long termId,
//			@RequestParam String subjectName) {
//		List<AcademicTerm> response = service.getAcademicTermListByTermIdAndSubject(paper,termId,subjectName);
//		if (response != null) {
//			return new ResponseEntity<>(
//					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
//					HttpStatus.OK);
//		}
//		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
//				HttpStatus.OK);
//	}

	@PutMapping("/update_academic_subject")
	public ResponseEntity<?> updateAcademicTerm(@RequestBody AcademicTerm academicTerm) {
		AcademicTerm response = service.updateAcademicTerm(academicTerm);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}
}
