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

import com.example.demo.model.Resignation;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminResignationService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/current-cases")
public class AdminResignationController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminResignationService resignationService;

	@PostMapping(value = "/add-resignation")
	public ResponseEntity<?> addResignation(Resignation resignation,
			@RequestParam("resignationDoc") MultipartFile[] file, ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			resignation.setDoc(url + uploaded_doc);
		}
		Resignation response = resignationService.createResignation(resignation);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "resignation," + ConstantMessage.RESIGNATION_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RESIGNATION_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-resignation-list")
	public ResponseEntity<?> getResignationList(@RequestParam Integer status) {
		List<Resignation> list = resignationService.getAllResignationList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-resignation")
	public ResponseEntity<?> getResignationByID(@RequestParam Integer id) {
		Resignation list = resignationService.getResignationById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-resignation")
	public ResponseEntity<?> updateResignation(Resignation resignation,
			@RequestParam(value = "resignationDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				resignation.setDoc(url + uploaded_doc);
			}
		}
		Resignation response = resignationService.updateResignation(resignation);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "resignation,"
				+ ConstantMessage.RESIGNATION_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RESIGNATION_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
