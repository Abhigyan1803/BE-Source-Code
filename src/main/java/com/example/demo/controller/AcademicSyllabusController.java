package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcademicSyllabus;
import com.example.demo.service.AcademicSyllabusService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/acadmic_syllabus_controller")
@CrossOrigin
public class AcademicSyllabusController {
	@Autowired
	private AcademicSyllabusService service;

	@PostMapping("/add_academic_syllabus")
	public ResponseEntity<?> addAcademicSyllabus(AcademicSyllabus academicSyllabus, MultipartFile file) {
		AcademicSyllabus response = service.addAcademicSyllabus(academicSyllabus, file);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get_academic_syllabus_By_Id")
	public ResponseEntity<?> getById(Long id) {
		AcademicSyllabus response = service.getById(id);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/getall_academic_syllabus_by_status")
	public ResponseEntity<?> getByStatus(Integer status) {
		java.util.List<AcademicSyllabus> response = service.getByStatus(status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_academic_syllabus_List_by_termid")
	public ResponseEntity<?> getAcademicSyllabusList(@RequestParam Long termId, @RequestParam String paper, @RequestParam String subject,
			@RequestParam Integer status) {
		java.util.List<AcademicSyllabus> response = service.getAcademicSyllabusList(termId, paper,subject, status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping("/update_academic_syllabus")
	public ResponseEntity<?> updateAcademicSyllabus(AcademicSyllabus academicSyllabus, MultipartFile file) {
		AcademicSyllabus response = service.updateAcademicSyllabus(academicSyllabus, file);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

}
