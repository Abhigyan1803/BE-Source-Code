package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.example.demo.model.SyllabusTerm;
import com.example.demo.model.SyllabusType;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminSyllabusTermService;
import com.example.demo.service.AdminSyllabusTypeService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/syllabus/term")
public class AdminSyllabusTermController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminSyllabusTypeService syllabusTypeService;

	@Autowired
	AdminSyllabusTermService syllabusTermService;

	@GetMapping(value = "/syllabus-type-list")
	public ResponseEntity<?> syllabusTypeList() {
		List<SyllabusType> list = syllabusTypeService.getAllSyllabusTypeList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PostMapping(value = "/add-syllabus-term")
	public ResponseEntity<?> addSyllabus(SyllabusTerm syllbus,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("Syllabusdoc") MultipartFile[] file, ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			syllbus.setDoc(url + uploaded_doc);
		}
		SyllabusTerm response = syllabusTermService.createSyllabus(syllbus);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "syllabus-term," + ConstantMessage.SYLLABUS_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SYLLABUS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-syllabus-term-list")
	public ResponseEntity<?> getSyllabusList() {
		List<SyllabusTerm> list = syllabusTermService.getAllSyllabusList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-syllabus-term")
	public ResponseEntity<?> getSyllabusByID(@RequestParam Integer id) {
		SyllabusTerm list = syllabusTermService.getSyllabusById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-syllabus-term")
	public ResponseEntity<?> updateSyllabus(SyllabusTerm syllbus,
			@RequestParam(value = "Syllabusdoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				syllbus.setDoc(url + uploaded_doc);
			}
		}
		SyllabusTerm response = syllabusTermService.updateSyllabus(syllbus);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "syllabus-term,"
				+ ConstantMessage.SYLLABUS_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SYLLABUS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
