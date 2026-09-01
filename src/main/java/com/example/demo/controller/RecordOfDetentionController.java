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

import com.example.demo.model.RecordOfDetention;
import com.example.demo.myexception.MyException;
import com.example.demo.service.RecordOfDetentionService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/recordOfDetentionController")
@CrossOrigin
public class RecordOfDetentionController {
	@Autowired
	private RecordOfDetentionService service;

	@PostMapping("/add_RecordOfDetention")
	public ResponseEntity<?> addRecordOfDetention(@RequestBody RecordOfDetention recordOfDetention) {
		RecordOfDetention alreadyExist = service.findByServiceIdAndTermId(recordOfDetention.getServiceId(),
				recordOfDetention.getTermId());
		if (alreadyExist != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}
		RecordOfDetention response = service.addRecordOfDetention(recordOfDetention);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get_recordOfDetention_by_serviceId")
	public ResponseEntity<?> getById(@RequestParam String serviceId) {
		RecordOfDetention response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping("/get_RecordOfDetention_by_status")
	public ResponseEntity<?> getBystatus(@RequestParam Integer status) {
		List<RecordOfDetention> response = service.getBystatus(status);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-RecordOfDetention")
	public ResponseEntity<?> updateRecordOfDetention(@RequestBody RecordOfDetention recordOfDetention)
			throws MyException {
		RecordOfDetention response = service.updateRecordOfDetention(recordOfDetention);
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
