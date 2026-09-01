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

import com.example.demo.model.EdEqtn;
import com.example.demo.myexception.MyException;
import com.example.demo.service.EdEqtnService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/ed_eqnt_controller")
public class EdEqtnController {

	@Autowired
	private EdEqtnService service;

	@PostMapping("/add_ed_eqtn")
	public ResponseEntity<?> addEdEqtn(@RequestBody EdEqtn edEqtn) {
		EdEqtn response = service.addEdEqtn(edEqtn);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping("/get_ed_eqtn_by_serviceid")
	public ResponseEntity<?> getByServiceId(@RequestParam String serviceId) {
		EdEqtn response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update-ed_eqtn")
	public ResponseEntity<?> updateEdEqtn(@RequestBody EdEqtn edEqtn) throws MyException {
		EdEqtn response = service.updateEdEqtn(edEqtn);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
