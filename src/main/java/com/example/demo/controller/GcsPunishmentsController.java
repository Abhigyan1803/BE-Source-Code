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

import com.example.demo.model.GcsPunishments;
import com.example.demo.myexception.MyException;
import com.example.demo.service.GcsPunishmentsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/gcspunshmentsController")
public class GcsPunishmentsController {
	@Autowired
	GcsPunishmentsService service;

	@PostMapping("/add_gcspunishments")
	public ResponseEntity<?> addgcspunshiments(@RequestBody GcsPunishments gcsPunishments) {
		GcsPunishments response = service.addGcspunshiments(gcsPunishments);
		if (gcsPunishments != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, null), HttpStatus.OK);
		} else {
			// CampSubjectDetails response =
			// campSubjectDetailsService.createSubject(campSubjectDetails);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_ADD, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@PostMapping(value = "/update-gcspunishimnets")
	public ResponseEntity<?> updateGCSpunshiments(@RequestBody GcsPunishments gcsPunishments) throws MyException {
		GcsPunishments response = service.updateGcsPunishments(gcsPunishments);
		if (response != null) {
			// CampSubjectDetails response =
			// campSubjectDetailsService.updateSubject(campSubjectDetails);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_gcsPunishmentsList")
	public ResponseEntity<?> getGcsPunishmentsList(@RequestParam String serviceId,
			@RequestParam(required = false) Long termId, @RequestParam(required = false) Integer status) {
		List<GcsPunishments> response = service.getGcsPunishmentsList(serviceId, termId, status);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
