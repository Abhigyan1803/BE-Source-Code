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

import com.example.demo.model.WtMotivationBadge;
import com.example.demo.service.WtMotivationBadgeService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/wt_motivation_badge_controller")
public class WtMotivationBadgeController {

	@Autowired
	private WtMotivationBadgeService service;

	@PostMapping("/add_wt_motivation_badge")
	public ResponseEntity<?> addMotivationBadge(@RequestBody WtMotivationBadge wtMotivationBadge) {
		WtMotivationBadge response = service.addMotivationBadge(wtMotivationBadge);

		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get_wt_motivation_badge_by_serviceId_and_termId")
	public ResponseEntity<?> getByServiceIdAndTermId(@RequestParam String serviceId, @RequestParam Long termId) {
		WtMotivationBadge response = service.getByServiceIdAndTermId(serviceId, termId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
				HttpStatus.OK);
	}

	@GetMapping("/get_wt_motivation_badge_by_serviceId")
	public ResponseEntity<?> getByServiceId(@RequestParam String serviceId) {
		WtMotivationBadge response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
				HttpStatus.OK);
	}

	@PutMapping("/update_wt_motivation_badge")
	public ResponseEntity<?> updateMotionBadge(@RequestBody WtMotivationBadge wtMotivationBadge) {
		WtMotivationBadge response = service.updateMotivationBadge(wtMotivationBadge);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
