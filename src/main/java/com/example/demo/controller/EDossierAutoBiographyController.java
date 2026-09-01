package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EDossierAutoBiography;
import com.example.demo.service.EDossierAutoBiographyService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController("/api/edossier_auto_biography")
public class EDossierAutoBiographyController {
	@Autowired
	private EDossierAutoBiographyService service;

	@PostMapping("/add_auto_biography")
	public ResponseEntity<?> addAutoBiography(@RequestBody EDossierAutoBiography autoBiography) {
		EDossierAutoBiography alredyExist = service.getAutoBiographyByServiceId(autoBiography.getServiceId());
		if (alredyExist == null) {
			EDossierAutoBiography response = service.createAutoBiography(autoBiography);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping("/get_auto_biography_by_Id")
	public ResponseEntity<?> getById(@RequestParam Long id) {
		EDossierAutoBiography response = service.getAutoBiographyById(id);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_auto_biography_by_serviceId")
	public ResponseEntity<?> getByServiceId(@RequestParam String serviceId) {
		EDossierAutoBiography response = service.getAutoBiographyByServiceId(serviceId);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@PatchMapping("/update_auto_biography")
	public ResponseEntity<?> updateAutoBiography(@RequestBody EDossierAutoBiography autoBiography) {
		EDossierAutoBiography response = service.updateAutoBiography(autoBiography);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

}
