package com.example.demo.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Term;
import com.example.demo.myexception.MyException;
import com.example.demo.service.TermService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/term")
@CrossOrigin
public class AdminTermController {

	@Autowired
	TermService termService;

	@GetMapping("/getAllTerms")
	public Map<Object, Object> getAllTerms() {
		return termService.getAllTerms();
	}

	@GetMapping("/getAllTermsNew")
	public Map<Object, Object> getAllTermsNew() {
		return termService.getAllTermsNew();
	}

	@PostMapping(value = "/add-term")
	public ResponseEntity<?> addTerm(@RequestBody Term term, ServletRequest request) throws MyException {

		Term response = termService.createTerm(term);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "term," + ConstantMessage.TERM_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.TERM_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@PostMapping(value = "/update-term")
	public ResponseEntity<?> updateTerm(@RequestBody Term term, ServletRequest request) throws MyException {

		Term response = termService.updateTerm(term);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "term," + ConstantMessage.TERM_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.TERM_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping("/get_term_by_id")
	public ResponseEntity<?> getTermById(@RequestParam Long id) {
		Term response = termService.getTermById(id);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

}
