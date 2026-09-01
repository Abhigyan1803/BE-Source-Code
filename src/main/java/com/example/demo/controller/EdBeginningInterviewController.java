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

import com.example.demo.model.EdBeginningInterview;
import com.example.demo.myexception.MyException;
import com.example.demo.service.EdBeginningInterviewService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/edBeginningInterviewController")
@CrossOrigin
public class EdBeginningInterviewController {
	@Autowired
	private EdBeginningInterviewService service;

	@PostMapping("/add_edBeginningInterviewService")
	public ResponseEntity<?> addEdBeginningInterview(@RequestBody EdBeginningInterview edBeginningInterview) {
		EdBeginningInterview beginningInterview = service.getByServiceIdAndSubmittedBy(
				edBeginningInterview.getServiceId(), edBeginningInterview.getSubmittedBy());
		if (beginningInterview == null) {
			EdBeginningInterview response = service.addEdBeginningInterview(edBeginningInterview);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_edBeginningInterviewService_by_serviceId")
	public ResponseEntity<?> getById(@RequestParam String serviceId) {
		List<EdBeginningInterview> response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-edBeginningInterviewService")
	public ResponseEntity<?> updateEdBeginningInterview(@RequestBody EdBeginningInterview edBeginningInterview)
			throws MyException {
		EdBeginningInterview response = service.updateEdBeginningInterview(edBeginningInterview);
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
