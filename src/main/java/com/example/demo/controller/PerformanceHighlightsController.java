package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.example.demo.model.PerformanceHighlights;
import com.example.demo.myexception.MyException;
import com.example.demo.service.PerformanceHighlightsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/performance-highlights")
public class PerformanceHighlightsController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	PerformanceHighlightsService phService;

	@PostMapping(value = "/add-performance-highlights")
	public ResponseEntity<?> addPerformance(PerformanceHighlights performanceHighlights, ServletRequest request) throws MyException {
	//	@RequestParam("file") MultipartFile[] file
//		String uploaded_doc = StringUtils.EMPTY;
//		for (MultipartFile multipartFile : file) {
//			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
//			performanceHighlights.setImage(url + uploaded_doc);
//		}
		PerformanceHighlights response = phService.createPerformanceHighlights(performanceHighlights);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "performance-highlights,"
				+ ConstantMessage.PERFORMANCE_HIGHLIGHT_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.PERFORMANCE_HIGHLIGHT_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-performance-highlights-list")
	public ResponseEntity<?> getaPerformanceList(@RequestParam(defaultValue = "2") Integer status,
			@RequestParam(defaultValue = "0") Integer battalianId) {
		List<PerformanceHighlights> list = phService.getAllPerformanceHighlightsList(status, battalianId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-performance-highlights")
	public ResponseEntity<?> getPerformanceByID(@RequestParam Integer id) {
		Optional<PerformanceHighlights> list = phService.getPerformanceById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-performance-highlights")
	public ResponseEntity<?> updatePerformance(PerformanceHighlights performanceHighlights,
			 ServletRequest request)
			throws MyException {
//	@RequestParam(value = "file", required = false) MultipartFile[] file
//		String uploaded_doc = StringUtils.EMPTY;
//		if (file != null) {
//			long length = file.length;
//			System.err.println("file length " + length);
//			for (MultipartFile multipartFile : file) {
//				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
//				performanceHighlights.setImage(url + uploaded_doc);
//			}
//		}
		PerformanceHighlights response = phService.updatePerformance(performanceHighlights);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "performance-Highlights,"
				+ ConstantMessage.PERFORMANCE_HIGHLIGHT_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.PERFORMANCE_HIGHLIGHT_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
