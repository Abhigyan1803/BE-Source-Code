package com.example.demo.controller;

import java.util.Date;
import java.util.List;

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

import com.example.demo.model.CentralLibrary;
import com.example.demo.myexception.MyException;
import com.example.demo.service.CentralLibraryService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/centralLibraryController")
public class CentralLibraryController {

	@Autowired
	CentralLibraryService centralLibraryService;

	@PostMapping(value = "/addCentralLibrary")
	public ResponseEntity<?> addCentralLibrary(@RequestBody CentralLibrary request, ServletRequest servRequest) throws MyException {
		CentralLibrary response = centralLibraryService.addCentralLibrary(request);
		FileWritting.createLog((HttpServletRequest) servRequest, response.getId() + ",added," + "Central Library,"
				+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping(value = "/getAllCentralLibraryRecord")
	public ResponseEntity<?> getAllCentralLibraryRecord() {
		List<CentralLibrary> list = centralLibraryService.getAllCentralLibraryRecord();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/activeDeActiveLibrary")
	public ResponseEntity<?> activeDeActiveLibrary(@RequestParam Long id, int status) {
		CentralLibrary response = centralLibraryService.activeDeactiveLibrary(id, status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/updateLibrary")
	public ResponseEntity<?> updateLibrary(@RequestBody CentralLibrary request , ServletRequest servRequest) throws MyException {
		CentralLibrary response = centralLibraryService.updateLibraryRecord(request);
		FileWritting.createLog((HttpServletRequest) servRequest, response.getId() + ",updated," + "Central Library,"
				+ ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getLibraryDetailsById")
	public ResponseEntity<?> getLibraryDetailsById(@RequestParam Long id) {
		CentralLibrary response = centralLibraryService.getLibraryRecordById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getAllCentralLibraryRecordHomePage")
	public ResponseEntity<?> getAllCentralLibraryRecordHomePage() {
		List<CentralLibrary> list = centralLibraryService.getAllCentralLibraryRecordHomePage();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

}
