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

import com.example.demo.model.StandingTRGDirectives;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminStandingTRGDirectivesService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/standing-trg-directive")
public class AdminStandingTRGDirectivesController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminStandingTRGDirectivesService directiveService;

	@PostMapping(value = "/add-trg-directive")
	public ResponseEntity<?> addTRGDirectives(StandingTRGDirectives trgDirective,
			@RequestParam("trgDirectiveDoc") MultipartFile[] file, ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			trgDirective.setDoc(url + uploaded_doc);
		}
		StandingTRGDirectives response = directiveService.createTRGDirectives(trgDirective);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "standing-trg-directive,"
				+ ConstantMessage.TRG_DIRECTIVES_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.TRG_DIRECTIVES_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-trg-directive-list")
	public ResponseEntity<?> getTRGDirectivesList(@RequestParam Integer status) {
		List<StandingTRGDirectives> list = directiveService.getAllTRGDirectivesList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-trg-directive")
	public ResponseEntity<?> getTRGDirectivesByID(@RequestParam Integer id) {
		StandingTRGDirectives list = directiveService.getTRGDirectivesById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-trg-directive")
	public ResponseEntity<?> updateTRGDirective(StandingTRGDirectives trgDirective,
			@RequestParam(value = "trgDirectiveDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				trgDirective.setDoc(url + uploaded_doc);
			}
		}
		StandingTRGDirectives response = directiveService.updateTRGDirective(trgDirective);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "standing-trg-directive,"
				+ ConstantMessage.TRG_DIRECTIVES_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.TRG_DIRECTIVES_UPDATED, HttpStatus.OK, response), HttpStatus.OK);

	}

}
