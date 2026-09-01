package com.example.demo.controller;

import java.util.Date;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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
import com.example.demo.myexception.MyException;
import com.example.demo.service.CampDetailsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/campDetailsController")
@CrossOrigin
public class CampDetailsController {

	@Autowired
	CampDetailsService campDetailsService;

	@PostMapping(value = "/add-camp")
	public ResponseEntity<?> addCamp(@RequestBody CampDetails campDetails, ServletRequest request) throws MyException {
		CampDetails response = campDetailsService.createCamp(campDetails);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "camp," + ConstantMessage.CAMP_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CAMP_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getCampByTerm")
	public ResponseEntity<?> getCampByTerm(@RequestParam Long termId) {
		Set<CampDetails> list = campDetailsService.getCampByTerm(termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-Capm-by-id")
	public ResponseEntity<?> getCampById(@RequestParam Long id) {
		CampDetails list = campDetailsService.getCampById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-camp")
	public ResponseEntity<?> updateCamp(@RequestBody CampDetails campDetails, ServletRequest request) {
		CampDetails response = campDetailsService.updateCamp(campDetails);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "camp," + ConstantMessage.CAMP_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CAMP_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
