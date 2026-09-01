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

import com.example.demo.model.EdMidInterview;
import com.example.demo.myexception.MyException;
import com.example.demo.service.EdMidInterviewService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/edMidInterviewController")
@CrossOrigin
public class EdMidInterviewController {
	@Autowired
	private EdMidInterviewService service;

	@PostMapping("/add_edMidInterviewService")
	public ResponseEntity<?> addEdMidInterview(@RequestBody EdMidInterview edMidInterview) {
		EdMidInterview midInterview = service.getByServiceIdAndSubmittedBy(edMidInterview.getServiceId(),
				edMidInterview.getSubmittedBy());
		if (midInterview == null) {
			EdMidInterview response = service.addEdMidInterview(edMidInterview);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_edMidInterviewService_by_serviceId")
	public ResponseEntity<?> getById(@RequestParam String serviceId) {
		List<EdMidInterview> response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-edMidInterviewService")
	public ResponseEntity<?> updateEdMidInterview(@RequestBody EdMidInterview edMidInterview) throws MyException {
		EdMidInterview response = service.updateEdMidInterview(edMidInterview);
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
