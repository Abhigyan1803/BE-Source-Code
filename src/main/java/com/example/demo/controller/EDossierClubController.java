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

import com.example.demo.model.EDossierClub;
import com.example.demo.myexception.MyException;
import com.example.demo.service.EDossierClubService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/eDossierClubController")
@CrossOrigin
public class EDossierClubController {
	@Autowired
	private EDossierClubService service;

	@PostMapping("/add_EDossierClubService")
	public ResponseEntity<?> addAcademicTerm(@RequestBody EDossierClub eDossierClub) {
		EDossierClub alreadyExist = service.findByServiceIdAndTermId(eDossierClub.getServiceId(),
				eDossierClub.getTermId());
		if (alreadyExist != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}
		EDossierClub response = service.addEDossierClub(eDossierClub);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get_eDossierClubService_by_serviceId")
	public ResponseEntity<?> getById(@RequestParam String serviceId) {
		EDossierClub response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping("/get_eDossierClubService_by_status")
	public ResponseEntity<?> getBystatus(@RequestParam Integer status) {
		List<EDossierClub> response = service.getBystatus(status);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-eDossierClubService")
	public ResponseEntity<?> updateEDossierClubService(@RequestBody EDossierClub eDossierClub) throws MyException {
		EDossierClub response = service.updateEDossierClub(eDossierClub);
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
