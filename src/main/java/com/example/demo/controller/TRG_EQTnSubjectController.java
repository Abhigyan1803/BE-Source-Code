package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TRG_EQTNSubject;
import com.example.demo.service.TRG_EQTNSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
public class TRG_EQTnSubjectController {
	@Autowired
	private TRG_EQTNSubjectService service;

	@GetMapping("/get_trg_eqtn_by_status")
	public ResponseEntity<?> getByStatusAndTermId(Integer status, Long termId) {
		List<TRG_EQTNSubject> response = service.getByStatusAndTermId(status, termId);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
