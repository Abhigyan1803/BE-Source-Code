package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcademicExamination;
import com.example.demo.service.AcademicExaminationService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin("*")
@RestController("acadmic_examination_Controller")
public class AcademicExaminationController {

	@Autowired
	private AcademicExaminationService service;

	@PostMapping("/add_academic_examination")
	public ResponseEntity<?> addAcademicExam(AcademicExamination academicExam, MultipartFile doc) {
		AcademicExamination response = service.addAcademicExam(academicExam, doc);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get_academic_examination_By_Id")
	public ResponseEntity<?> getById(Long id) {
		AcademicExamination response = service.getById(id);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_academic_examination_list_by_termId_type")
	public ResponseEntity<?> getAcademicExamList(@RequestParam String type, @RequestParam Long termId,
			@RequestParam Integer status) {
		java.util.List<AcademicExamination> response = service.getAcademicExamList(type, termId, status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping("/update_academic_examination")
	public ResponseEntity<?> updateAcademicExam(AcademicExamination academicExam, MultipartFile doc) {
		AcademicExamination response = service.updateAcademicExamination(academicExam, doc);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}
}
