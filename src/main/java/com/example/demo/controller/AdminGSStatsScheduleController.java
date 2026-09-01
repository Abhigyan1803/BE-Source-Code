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

import com.example.demo.model.GSStatsSchedule;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminGSStatsScheduleService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/gs-assessment")
public class AdminGSStatsScheduleController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminGSStatsScheduleService gsScheduleService;

	@PostMapping(value = "/add-gs-schedule")
	public ResponseEntity<?> addGsSchedule(GSStatsSchedule schedule, @RequestParam("scheduleDoc") MultipartFile[] file,
			ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			schedule.setDoc(url + uploaded_doc);
		}
		GSStatsSchedule response = gsScheduleService.createGsSchedule(schedule);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "gs-assessment,"
				+ ConstantMessage.GS_STATS_SCHEDULE_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.GS_STATS_SCHEDULE_ADDED, HttpStatus.OK, response), HttpStatus.OK);

	}

	@GetMapping(value = "/get-gs-schedule-list")
	public ResponseEntity<?> getGsSchedule(@RequestParam Integer status) {
		List<GSStatsSchedule> list = gsScheduleService.getAllGsScheduleList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-gs-schedule")
	public ResponseEntity<?> getGsScheduleByID(@RequestParam Integer id) {
		GSStatsSchedule list = gsScheduleService.getGsScheduleById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-gs-schedule")
	public ResponseEntity<?> updateGsSchedule(GSStatsSchedule schedule,
			@RequestParam(value = "scheduleDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				schedule.setDoc(url + uploaded_doc);
			}
		}
		GSStatsSchedule response = gsScheduleService.updateGsSchedule(schedule);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "gs-assessment,"
				+ ConstantMessage.GS_STATS_SCHEDULE_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.GS_STATS_SCHEDULE_UPDATED, HttpStatus.OK, response), HttpStatus.OK);

	}

}
