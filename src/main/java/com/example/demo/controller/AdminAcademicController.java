package com.example.demo.controller;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcademicFiles;
import com.example.demo.service.AdminAcademicService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/academic_files")
public class AdminAcademicController {

	@Autowired
	AdminAcademicService adminAcademicService;

	@PostMapping("/add-adcademicFiles")
	public ResponseEntity<?> addDetails(@RequestParam(value = "docfile", required = false) MultipartFile docfile,
			ServletRequest request) {

		AcademicFiles response = adminAcademicService.addAcademicFiles(docfile);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.ACADEMIC_UPLOAD_ADDED, HttpStatus.OK, response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPLOAD, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}
	
	@PostMapping("/add-adcademicFiles-new")
	public ResponseEntity<?> addDetailsNew(@RequestParam(value = "docfile", required = false) MultipartFile docfile,
			ServletRequest request) {

		AcademicFiles response = adminAcademicService.addAcademicFiles(docfile);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.ACADEMIC_UPLOAD_ADDED, HttpStatus.OK, response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPLOAD, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

}
