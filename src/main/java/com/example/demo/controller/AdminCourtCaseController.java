package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.CourtCase;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminCourtCaseService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/current-cases")
public class AdminCourtCaseController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminCourtCaseService caseService;

	@PostMapping(value = "/add-court-case")
	public ResponseEntity<?> addCourtCase(CourtCase courtCase, @RequestParam("courtCaseDoc") MultipartFile[] file,
			ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			courtCase.setDoc(url + uploaded_doc);
		}
		CourtCase response = caseService.createCourtCase(courtCase);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "court-case," + ConstantMessage.COURT_CASE_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.COURT_CASE_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-court-case-list")
	public ResponseEntity<?> getCourtCaseList(@RequestParam Integer status) {
		List<CourtCase> list = caseService.getAllCourtCaseList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-court-case")
	public ResponseEntity<?> getCourtCaseByID(@RequestParam Integer id) {
		CourtCase list = caseService.getCourtCaseById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-court-case")
	public ResponseEntity<?> updateCourtCase(CourtCase courtCase,
			@RequestParam(value = "courtCaseDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				courtCase.setDoc(url + uploaded_doc);
			}
		}
		CourtCase response = caseService.updateCourtCase(courtCase);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "court-case," + ConstantMessage.COURT_CASE_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.COURT_CASE_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
