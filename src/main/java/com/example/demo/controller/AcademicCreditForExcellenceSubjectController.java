package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AcademicCreditForExcellenceSubject;
import com.example.demo.service.AcademicCreditForExcellenceSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
public class AcademicCreditForExcellenceSubjectController {
	@Autowired
	private AcademicCreditForExcellenceSubjectService service;

	@GetMapping("/get_academic_Credit_for_Excellence_by_status")
	public ResponseEntity<?> getBystatus(Integer status) {
		List<AcademicCreditForExcellenceSubject> response = service.getBystatus(status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
