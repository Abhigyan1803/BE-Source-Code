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

import com.example.demo.model.GSStatsMatrix;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminGSStatsMatrixService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/gs-assessment")
public class AdminGSStatsMatrixController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminGSStatsMatrixService gsMatrixService;

	@PostMapping(value = "/add-gs-matrix")
	public ResponseEntity<?> addGsMatrix(GSStatsMatrix matrix, @RequestParam("matrixDoc") MultipartFile[] file,
			ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			matrix.setDoc(url + uploaded_doc);
		}
		GSStatsMatrix response = gsMatrixService.createGsMatrix(matrix);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "gs-assessment,"
				+ ConstantMessage.GS_STATS_MATRIX_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.GS_STATS_SCHEDULE_ADDED, HttpStatus.OK, response), HttpStatus.OK);

	}

	@GetMapping(value = "/get-gs-matrix-list")
	public ResponseEntity<?> getGsMatrix(@RequestParam Integer status) {
		List<GSStatsMatrix> list = gsMatrixService.getAllGsMatricList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-gs-matrix")
	public ResponseEntity<?> getGsMatrixByID(@RequestParam Integer id) {
		GSStatsMatrix list = gsMatrixService.getGsgsMatrixById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-gs-matrix")
	public ResponseEntity<?> updateGsMatrix(GSStatsMatrix matrix,
			@RequestParam(value = "matrixDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				matrix.setDoc(url + uploaded_doc);
			}
		}
		GSStatsMatrix response = gsMatrixService.updateGsMatrix(matrix);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "gs-assessment,"
				+ ConstantMessage.GS_STATS_MATRIX_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.GS_STATS_MATRIX_UPDATED, HttpStatus.OK, response), HttpStatus.OK);

	}

}
