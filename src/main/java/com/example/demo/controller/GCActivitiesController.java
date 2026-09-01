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

import com.example.demo.model.GCActivities;
import com.example.demo.myexception.MyException;
import com.example.demo.service.GCActivitiesService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/gc-activities")
public class GCActivitiesController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	GCActivitiesService gcService;

	@PostMapping(value = "/add-gc-activities")
	public ResponseEntity<?> addGCActivities(GCActivities gCActivities, @RequestParam("file") MultipartFile[] file,
			ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			gCActivities.setImage(url + uploaded_doc);
		}
		GCActivities response = gcService.createPerformanceHighlights(gCActivities);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "gc-activities,"
				+ ConstantMessage.GCACTIVITIES_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.GCACTIVITIES_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-gc-activities-list")
	public ResponseEntity<?> getGCActivitiesList(@RequestParam(defaultValue = "2") Integer status,
			@RequestParam(defaultValue = "0") Integer battalianId) {
		List<GCActivities> list = gcService.getAllGCActivitiesList(status, battalianId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-gc-activity")
	public ResponseEntity<?> getGCActivitiesByID(@RequestParam Integer id) {
		Optional<GCActivities> list = gcService.getGCActivitiesById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-gc-activities")
	public ResponseEntity<?> updateActivities(GCActivities gCActivities,
			@RequestParam(value = "file", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				gCActivities.setImage(url + uploaded_doc);
			}
		}
		GCActivities response = gcService.updateActivities(gCActivities);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "gc-activities,"
				+ ConstantMessage.GCACTIVITIES_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.GCACTIVITIES_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
