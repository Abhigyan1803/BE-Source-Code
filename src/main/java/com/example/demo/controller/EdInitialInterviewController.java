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

import com.example.demo.model.EdInitialInterview;
import com.example.demo.myexception.MyException;
import com.example.demo.service.EdInitialInterviewService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/edInitialInterviewController")
@CrossOrigin
public class EdInitialInterviewController {
	@Autowired
	private EdInitialInterviewService service;

	@PostMapping("/add_edInitialInterviewService")
	public ResponseEntity<?> addEdInitialInterview(@RequestBody EdInitialInterview edInitialInterview) {
		EdInitialInterview initialInterview = service.getByServiceIdAndSubmittedBy(edInitialInterview.getServiceId(),
				edInitialInterview.getSubmittedBy());
		if (initialInterview == null) {
			EdInitialInterview response = service.addEdInitialInterview(edInitialInterview);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_edInitialInterviewService_by_serviceId")
	public ResponseEntity<?> getById(@RequestParam String serviceId) {
		List<EdInitialInterview> response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-edInitialInterviewService")
	public ResponseEntity<?> updateEdInitialInterview(@RequestBody EdInitialInterview edInitialInterview)
			throws MyException {
		EdInitialInterview response = service.updateEdInitialInterview(edInitialInterview);
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
