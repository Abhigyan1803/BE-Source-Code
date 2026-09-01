package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EdCampMarks;
import com.example.demo.service.EdCampMarksService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/ed_camp_marks")
public class EdCampMarksController {
	@Autowired
	private EdCampMarksService service;

	@PostMapping("/add_ed_camp_marks")
	public ResponseEntity<?> addEdCampMarks(@RequestBody EdCampMarks edCampMarks) {
		EdCampMarks alreadyExist = service.getByServiceIdAndTermId(edCampMarks.getServiceId(), edCampMarks.getTermId());
		if (alreadyExist == null) {
			EdCampMarks response = service.addEdCampMarks(edCampMarks);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping("/get_all_by_service_id")
	public ResponseEntity<?> getByServiceId(@RequestParam String serviceId) {
		List<EdCampMarks> response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping("/update_ed_camp_marks")
	public ResponseEntity<?> updateEdCampMarks(@RequestBody EdCampMarks edCampMarks) {
		EdCampMarks response = service.updateEdCampMarks(edCampMarks);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

}
