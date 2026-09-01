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

import com.example.demo.model.EDossierLve;
import com.example.demo.myexception.MyException;
import com.example.demo.service.EDossierLveService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/eDossierLveController")
@CrossOrigin
public class EDossierLveController {
	@Autowired
	private EDossierLveService service;

	@PostMapping("/add_eDossierLve")
	public ResponseEntity<?> addEDossierLve(@RequestBody EDossierLve eDossierLve) {
		EDossierLve alreadyExist = service.findByServiceIdAndTermId(eDossierLve.getServiceId(),
				eDossierLve.getTermId());
		if (alreadyExist != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}
		EDossierLve response = service.addEDossierLve(eDossierLve);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get_eDossierLve_by_serviceId")
	public ResponseEntity<?> getById(@RequestParam String serviceId) {
		EDossierLve response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping("/get_eDossierLve_by_status")
	public ResponseEntity<?> getBystatus(@RequestParam Integer status) {
		List<EDossierLve> response = service.getBystatus(status);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-eDossierLve")
	public ResponseEntity<?> updateEDossierLve(@RequestBody EDossierLve eDossierLve) throws MyException {
		EDossierLve response = service.updateEDossierLve(eDossierLve);
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
