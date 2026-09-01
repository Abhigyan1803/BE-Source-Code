package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CampSubjectDetails;
import com.example.demo.myexception.MyException;
import com.example.demo.service.CampSubjectDetailsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/campSubjectDetailsController")
@CrossOrigin
public class CampSubjectDetailsController {

	@Autowired
	private CampSubjectDetailsService campSubjectDetailsService;

	@PostMapping(value = "/add-subject")
	public ResponseEntity<?> addSubject(@RequestBody CampSubjectDetails campSubjectDetails) throws MyException {
		CampSubjectDetails campSubjectDtls = campSubjectDetailsService
				.findbySubject(campSubjectDetails.getSubjectName());
		if (campSubjectDtls != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.CAMP_SUBJECT_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		} else {
			CampSubjectDetails response = campSubjectDetailsService.createSubject(campSubjectDetails);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.CAMP_SUBJECT_ADDED, HttpStatus.OK, response), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/update-subject")
	public ResponseEntity<?> updateSubject(@RequestBody CampSubjectDetails campSubjectDetails) throws MyException {
		CampSubjectDetails campSubjectDtls = campSubjectDetailsService.validateSubjectExist(campSubjectDetails);
		if (campSubjectDtls == null) {
			CampSubjectDetails response = campSubjectDetailsService.updateSubject(campSubjectDetails);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.CAMP_SUBJECT_UPDATED, HttpStatus.OK, response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.CAMP_SUBJECT_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get-subject-by-id")
	public ResponseEntity<?> getSubjectById(@RequestParam Long id) {
		CampSubjectDetails list = campSubjectDetailsService.getSubjectById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-all-subject-by-status")
	public ResponseEntity<?> getAllSubjectByStatus(@RequestParam Integer status) {
		List<CampSubjectDetails> list = campSubjectDetailsService.getAllSubjectByStatus(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

}
