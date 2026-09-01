package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EdossierPtSubject;
import com.example.demo.service.EdossierPtSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/edossier_pt_subject_controller")
public class EdossierPtSubjectController {

	@Autowired
	private EdossierPtSubjectService service;
	
	@GetMapping("/getAll_by_status")
	public ResponseEntity<?> getPtSubjectList(@RequestParam Integer status,@RequestParam String subjectType,@RequestParam Long termId) {
		java.util.List<EdossierPtSubject> response = service.getPtSubjectList(status, subjectType,termId);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
