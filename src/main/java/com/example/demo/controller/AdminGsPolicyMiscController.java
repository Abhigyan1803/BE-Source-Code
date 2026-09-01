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

import com.example.demo.model.GsPolicyMisc;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminGsPolicyMiscService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/gs-policy-misc")
public class AdminGsPolicyMiscController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminGsPolicyMiscService miscService;

	@PostMapping(value = "/add-misc")
	public ResponseEntity<?> addMisc(GsPolicyMisc misc, @RequestParam("miscDoc") MultipartFile[] file,
			ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			misc.setDoc(url + uploaded_doc);
		}
		GsPolicyMisc response = miscService.createMisc(misc);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "gs-policy-misc," + ConstantMessage.MISC_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MISC_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-misc-list")
	public ResponseEntity<?> getMiscList(@RequestParam Integer status) {
		List<GsPolicyMisc> list = miscService.getAllMiscList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-parade-state")
	public ResponseEntity<?> getMiscByID(@RequestParam Integer id) {
		GsPolicyMisc list = miscService.getMiscById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-parade-state")
	public ResponseEntity<?> updateMisc(GsPolicyMisc misc,
			@RequestParam(value = "miscDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				misc.setDoc(url + uploaded_doc);
			}
		}
		GsPolicyMisc response = miscService.updateMisc(misc);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "gs-policy-misc," + ConstantMessage.MISC_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MISC_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
