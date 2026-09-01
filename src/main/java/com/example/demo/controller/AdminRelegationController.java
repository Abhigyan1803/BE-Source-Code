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

import com.example.demo.model.Relegation;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminRelegationService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/current-cases")
public class AdminRelegationController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminRelegationService relegationService;

	@PostMapping(value = "/add-relegation")
	public ResponseEntity<?> addRelegation(Relegation relegation, @RequestParam("relegationDoc") MultipartFile[] file,
			ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			relegation.setDoc(url + uploaded_doc);
		}
		Relegation response = relegationService.createRelegation(relegation);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "relegation," + ConstantMessage.RELEGATION_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RELEGATION_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-relegation-list")
	public ResponseEntity<?> getRelegationList(@RequestParam Integer status) {
		List<Relegation> list = relegationService.getAllRelegationList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-relegation")
	public ResponseEntity<?> getRelegationByID(@RequestParam Integer id) {
		Relegation list = relegationService.getRelegationById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-relegation")
	public ResponseEntity<?> updateRelegation(Relegation relegation,
			@RequestParam(value = "relegationDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				relegation.setDoc(url + uploaded_doc);
			}
		}
		Relegation response = relegationService.updateRelegation(relegation);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "relegation," + ConstantMessage.RELEGATION_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RELEGATION_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
