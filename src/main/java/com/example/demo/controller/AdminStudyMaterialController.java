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

import com.example.demo.model.StudyMaterial;
import com.example.demo.model.StudyMaterialType;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminStudyMaterialService;
import com.example.demo.service.AdminStudyMaterialTypeService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/studymaterial")
public class AdminStudyMaterialController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	@Autowired
	AdminStudyMaterialTypeService studyMaterialTypeService;
	@Autowired
	AdminStudyMaterialService studyMaterialService;

	@GetMapping(value = "/studymaterial-type-list")
	public ResponseEntity<?> studyMaterialTypeList() {
		List<StudyMaterialType> list = studyMaterialTypeService.getAllStudyMaterialTypeList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PostMapping(value = "/add-studymaterial")
	public ResponseEntity<?> addStudyMaterial(StudyMaterial studyMaterial,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("Syllabusdoc") MultipartFile[] file, ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			studyMaterial.setDoc(url + uploaded_doc);
		}
		StudyMaterial response = studyMaterialService.createStudyMaterial(studyMaterial);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "syllabus," + ConstantMessage.STUDYMATERIAL_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.STUDYMATERIAL_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-studymaterial-list")
	public ResponseEntity<?> getStudyMaterialList(@RequestParam String type,
			@RequestParam(required = false) Long termId) {
		List<StudyMaterial> list = studyMaterialService.getAllStudyMaterialList(type, termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-studymaterial")
	public ResponseEntity<?> getStudyMaterialById(@RequestParam Long id) {
		StudyMaterial list = studyMaterialService.getStudyMaterialById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-studymaterial")
	public ResponseEntity<?> updateStudyMaterial(StudyMaterial studyMaterial,
			@RequestParam(value = "StudyMaterialdoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				studyMaterial.setDoc(url + uploaded_doc);
			}
		}
		StudyMaterial response = studyMaterialService.updateStudyMaterial(studyMaterial);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "studymaterial,"
				+ ConstantMessage.STUDYMATERIAL_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.STUDYMATERIAL_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}
}
