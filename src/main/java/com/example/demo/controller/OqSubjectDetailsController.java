package com.example.demo.controller;

import java.util.Set;

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
import com.example.demo.model.CampDetails;
import com.example.demo.model.OqSubjectDetails;
import com.example.demo.myexception.MyException;
import com.example.demo.service.CampDetailsService;
import com.example.demo.service.OqSubjectDetailsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/oqSubjectDetailsController")
@CrossOrigin
public class OqSubjectDetailsController {

	@Autowired
	OqSubjectDetailsService oqSubjectDetailsService;

	@PostMapping(value = "/add-subject")
	public ResponseEntity<?> addSubject(@RequestBody OqSubjectDetails oqSubjectDetails) throws MyException {
		OqSubjectDetails response = oqSubjectDetailsService.createSubject(oqSubjectDetails);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CAMP_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getSubjectByTerm")
	public ResponseEntity<?> getSubjectByTerm(@RequestParam Long termId) {
		Set<OqSubjectDetails> list = oqSubjectDetailsService.getSubjectByTerm(termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-subject-by-id")
	public ResponseEntity<?> getSubjectById(@RequestParam Long id) {
		OqSubjectDetails list = oqSubjectDetailsService.getSubjectById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-subject")
	public ResponseEntity<?> updateSubject(@RequestBody OqSubjectDetails oqSubjectDetails) {
		OqSubjectDetails response = oqSubjectDetailsService.updateSubject(oqSubjectDetails);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CAMP_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	
}
