package com.example.demo.controller;

import java.util.List;

import javax.servlet.ServletRequest;

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

import com.example.demo.model.Officer;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.OfficerPayLoad;
import com.example.demo.service.AdminRecordOfService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/recordOfService")
public class AdminRecordOfServiceController {

	@Autowired
	AdminRecordOfService recordOfService;

	@PostMapping(value = "/add_officer")
	public ResponseEntity<?> addOfficer(@RequestBody Officer officer, ServletRequest request) throws MyException {

		Officer allReadyExist = recordOfService.getOfficerByPersonalNumber(officer.getPersonalNumber());
		if (allReadyExist == null || allReadyExist.getStatus() == 2) {
			Officer response = recordOfService.createOfficer(officer);
			if (response != null) {
				return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OFFICER_ADDED, HttpStatus.OK, response),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_ADD, HttpStatus.OK, response),
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_officer_by_id")
	public ResponseEntity<?> getOfficer(@RequestParam Long id, ServletRequest request) throws MyException {

		Officer response = recordOfService.getOfficerById(id);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_All_officer_by_status")
	public ResponseEntity<?> getOfficerByStatus(@RequestParam Integer status, ServletRequest request)
			throws MyException {

		List<Officer> response = recordOfService.getAllOfficerByStatus(status);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@PostMapping(value = "/change_officer_status")
	public ResponseEntity<?> ChangeOfficerStatus(@RequestBody Officer officer, ServletRequest request)
			throws MyException {

		Officer response = recordOfService.ChangeOfficerStatus(officer);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@PostMapping(value = "/update_officer")
	public ResponseEntity<?> UpdateOfficerDetails(@RequestBody Officer officer, ServletRequest request)
			throws MyException {
		Officer allReadyExist = recordOfService.getOfficerByPersonalNumber(officer.getPersonalNumber());
		if (allReadyExist == null || allReadyExist.getId() == officer.getId()) {
			Officer response = recordOfService.UpdateOfficerDetails(officer);
			if (response != null) {
				return new ResponseEntity<>(
						new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response), HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	// @PostMapping(value = "/update_officer1")
	// public ResponseEntity<?> UpdateOfficerDetails1(@RequestBody Officer officer,
	// ServletRequest request)
	// throws MyException {
	//
	// Officer response = recordOfService.UpdateOfficerDetails1(officer);
	// if (response != null) {
	// return new ResponseEntity<>(new
	// ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
	// HttpStatus.OK);
	// } else {
	// return new ResponseEntity<>(new
	// ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
	// HttpStatus.OK);
	// }
	//
	// }

	@GetMapping(value = "/get_active_officers_list")
	public ResponseEntity<?> getActiveOfficers(ServletRequest request) {

		List<OfficerPayLoad> response = recordOfService.getActiveOfficers();
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

}
