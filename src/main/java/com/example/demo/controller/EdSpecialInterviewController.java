package com.example.demo.controller;

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

import com.example.demo.model.EdSpecialInterview;
import com.example.demo.myexception.MyException;
import com.example.demo.service.EdSpecialInterviewService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/edSpecialInterviewController")
@CrossOrigin
public class EdSpecialInterviewController {
	@Autowired
	private EdSpecialInterviewService service;

	@PostMapping("/add_edSpecialInterviewService")
	public ResponseEntity<?> addEdSpecialInterview(@RequestBody EdSpecialInterview edSpecialInterview) {
		EdSpecialInterview specialInterview = service.getByServiceId(edSpecialInterview.getServiceId());
		if (specialInterview == null) {
			EdSpecialInterview response = service.addEdSpecialInterview(edSpecialInterview);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_edSpecialInterviewService_by_serviceId")
	public ResponseEntity<?> getById(@RequestParam String serviceId) {
		EdSpecialInterview response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-edSpecialInterviewService")
	public ResponseEntity<?> updateEdSpecialInterview(@RequestBody EdSpecialInterview edSpecialInterview)
			throws MyException {
		EdSpecialInterview response = service.updateEdSpecialInterview(edSpecialInterview);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

}
