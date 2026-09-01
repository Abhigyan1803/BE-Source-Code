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

import com.example.demo.model.EdInterviewSheet;
import com.example.demo.myexception.MyException;
import com.example.demo.service.EdInterviewSheetService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/edInterviewSheetController")
@CrossOrigin
public class EdInterviewSheetController {
	@Autowired
	private EdInterviewSheetService service;

	@PostMapping("/add_edInterviewSheetService")
	public ResponseEntity<?> addEdInterviewSheet(@RequestBody EdInterviewSheet edInterviewSheet) {
		EdInterviewSheet interviewSheet = service.getByServiceId(edInterviewSheet.getServiceId());
		if (interviewSheet == null) {
			EdInterviewSheet response = service.addEdInterviewSheet(edInterviewSheet);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_edInterviewSheetService_by_serviceId")
	public ResponseEntity<?> getById(@RequestParam String serviceId) {
		EdInterviewSheet response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-edInterviewSheetService")
	public ResponseEntity<?> updateEdInterviewSheetService(@RequestBody EdInterviewSheet edInterviewSheet)
			throws MyException {
		EdInterviewSheet response = service.updateEdInterviewSheetService(edInterviewSheet);
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
