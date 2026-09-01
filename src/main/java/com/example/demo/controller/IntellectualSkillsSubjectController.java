package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.IntellectualSkillsSubject;
import com.example.demo.service.IntellectualSkillsSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
public class IntellectualSkillsSubjectController {
	@Autowired
	private IntellectualSkillsSubjectService service;

	@GetMapping("/get_intellectualsubject_by_status")
	public ResponseEntity<?> getByStatusAndTermId(Integer status, Long termId) {
		List<IntellectualSkillsSubject> response = service.getByStatusAndTermId(status, termId);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
