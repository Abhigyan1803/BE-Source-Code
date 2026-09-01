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

import com.example.demo.model.AcademyParadeState;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminAcademyParadeStateService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/academy-parade-state")
public class AdminAcademyParadeStateController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminAcademyParadeStateService paradeStateService;

	@PostMapping(value = "/add-parade-state")
	public ResponseEntity<?> addParadeState(AcademyParadeState paradeState,
			@RequestParam("paradeDoc") MultipartFile[] file, ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			paradeState.setDoc(url + uploaded_doc);
		}
		AcademyParadeState response = paradeStateService.createParadeState(paradeState);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "academy-parade-state,"
				+ ConstantMessage.ACADEMY_PARADE_STATE_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.ACADEMY_PARADE_STATE_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-parade-state-list")
	public ResponseEntity<?> getParadeStateList(@RequestParam Integer status) {
		List<AcademyParadeState> list = paradeStateService.getAllParadeStateList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-parade-state")
	public ResponseEntity<?> getParadeStateByID(@RequestParam Integer id) {
		AcademyParadeState list = paradeStateService.getParadeStateById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-parade-state")
	public ResponseEntity<?> updateParadeState(AcademyParadeState paradeState,
			@RequestParam(value = "paradeDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				paradeState.setDoc(url + uploaded_doc);
			}
		}
		AcademyParadeState response = paradeStateService.updateParadeState(paradeState);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "academy-parade-state,"
				+ ConstantMessage.ACADEMY_PARADE_STATE_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.ACADEMY_PARADE_STATE_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
