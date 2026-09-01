package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ServiceBmt2Subject;
import com.example.demo.service.ServiceBmt2SubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("api/service_bmt_2_subject_controller")
public class ServiceBmt2SubjectController {

	@Autowired
	private ServiceBmt2SubjectService service;

	@GetMapping("/get_service_bmt_2_subject_by_status")
	public ResponseEntity<?> getBystatus(Integer status, Long termId) {
		List<ServiceBmt2Subject> response = service.getByStatusAndTermId(status, termId);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
