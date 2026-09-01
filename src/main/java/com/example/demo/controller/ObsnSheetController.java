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

import com.example.demo.model.Counselling;
import com.example.demo.model.ObsnSheet;
import com.example.demo.service.CounsellingService;
import com.example.demo.service.ObsnSheetService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/ObsnSheet")
public class ObsnSheetController {
	@Autowired
	ObsnSheetService service;

	@GetMapping("/getAll_ObsnSheet_by_status_and_serviceid")
	public ResponseEntity<?> getObsnSheetList(@RequestParam Integer status, @RequestParam String serviceId) {
		List<ObsnSheet> response = service.getObsnSheetList(status, serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/getAll_ObsnSheet_by_id")
	public ResponseEntity<?> getObsnSheetList(@RequestParam Long id) {
	List<ObsnSheet> response = service.getObsnSheetList(id);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping("/save_ObsnSheet")
	public ResponseEntity<?> addObsnSheet(@RequestBody List<ObsnSheet> obsnSheetList) {
		List<ObsnSheet> response = service.addObsnSheet(obsnSheetList);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
