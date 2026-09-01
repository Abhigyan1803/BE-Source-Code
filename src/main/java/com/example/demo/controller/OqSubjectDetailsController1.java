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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OqSubjectDetails1;
import com.example.demo.myexception.MyException;
import com.example.demo.service.OqSubjectDetailsService1;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/oqSubjectDetailsController1")
@CrossOrigin
public class OqSubjectDetailsController1 {

	@Autowired
	private OqSubjectDetailsService1 oqSubjectDetailsService1;

	@PostMapping(value = "/add-subject")
	public ResponseEntity<?> addSubject(@RequestBody OqSubjectDetails1 oqSubjectDetails1) throws MyException {
		OqSubjectDetails1 OqSubjectdtls = oqSubjectDetailsService1.findBySubject(oqSubjectDetails1.getSubjectName());
		if (OqSubjectdtls == null) {
			OqSubjectDetails1 response = oqSubjectDetailsService1.createSubject(oqSubjectDetails1);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OQ_SUBJECT_ADDED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.OQ_SUBJECT_ALREADY_EXIT, HttpStatus.OK, null), HttpStatus.OK);
		}

	}

	@PutMapping(value = "/update-subject")
	public ResponseEntity<?> updateSubject(@RequestBody OqSubjectDetails1 oqSubjectDetails1) throws MyException {
		OqSubjectDetails1 OqSubjectdtls = oqSubjectDetailsService1.validateSubject(oqSubjectDetails1);
		if (OqSubjectdtls == null) {
			OqSubjectDetails1 response = oqSubjectDetailsService1.updateSubject(oqSubjectDetails1);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.OQ_SUBJECT_UPDATED, HttpStatus.OK, response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.OQ_SUBJECT_ALREADY_EXIT, HttpStatus.OK, null), HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get-subject-by-id")
	public ResponseEntity<?> getSubjectById(@RequestParam Long id) {
		OqSubjectDetails1 list = oqSubjectDetailsService1.getSubjectById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-all-subject-by-status")
	public ResponseEntity<?> getAllSubjectByStatus(@RequestParam Integer status) {
		List<OqSubjectDetails1> list = oqSubjectDetailsService1.getAllSubjectByStatus(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

}
