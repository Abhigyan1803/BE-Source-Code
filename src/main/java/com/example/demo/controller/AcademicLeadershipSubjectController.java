package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AcademicLeadershipSubject;
import com.example.demo.service.AcademicLeadershipSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
public class AcademicLeadershipSubjectController {
	@Autowired
	private AcademicLeadershipSubjectService service;

	@GetMapping("/get_academic_Leadership_matrix_by_status")
	public ResponseEntity<?> getBystatus(Integer status) {
		List<AcademicLeadershipSubject> response = service.getBystatus(status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
