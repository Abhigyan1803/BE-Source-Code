package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.DateSheet;
import com.example.demo.model.GSO2ServiceSubject;
import com.example.demo.service.DateSheetService;
import com.example.demo.service.GSO2ServiceSubjectService;

@RestController
@CrossOrigin
@RequestMapping("/dateSheet")
public class AdminDateSheetController {

	@Autowired
	DateSheetService sheetService;
	@Autowired
	GSO2ServiceSubjectService serviceSubService;

	@PostMapping("/addSheet")
	public Map<Object, Object> addDateSheet(@RequestParam(required = false, value = "doc") MultipartFile doc,
			DateSheet dateSheet, ServletRequest request) {
		return sheetService.addDateSheet(doc, dateSheet, request);
	}

	@GetMapping("/getAll")
	public Map<Object, Object> getAllRecords(@RequestParam() Long termId) {
		return sheetService.getAllRecords(termId);
	}

	@PostMapping("/updateRecord")
	public Map<Object, Object> updateDateSheet(@RequestParam(required = false, value = "doc") MultipartFile doc,
			DateSheet update, ServletRequest request) {
		return sheetService.updateRecord(doc, update, request);
	}

	@PostMapping("/viewById")
	public Map<Object, Object> viewById(Long id) {
		return sheetService.viewDetailsById(id);
	}

	@PostMapping("/activeDeactiveStatus")
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request) {
		return sheetService.activeDeactiveStatus(id, status, request);
	}

	@PostMapping("/addServiceSubject")
	public Map<Object, Object> addServiceSubject(@RequestParam(required = false, value = "doc") MultipartFile doc,
			GSO2ServiceSubject serviceSubject, ServletRequest request) {
		return serviceSubService.addServiceSubject(doc, serviceSubject, request);
	}

	@GetMapping("/getAllServiceSubjectRecords")
	public Map<Object, Object> getAllServiceSubjectRecords() {
		return serviceSubService.getAllServiceSubjectRecords();
	}

	@PostMapping("/updateServiceSubject")
	public Map<Object, Object> updateServiceSubject(@RequestParam(required = false, value = "doc") MultipartFile doc,
			GSO2ServiceSubject update, ServletRequest request) {
		return serviceSubService.updateServiceSubjectRecord(doc, update, request);
	}

	@PostMapping("/viewSubjectServiceById")
	public Map<Object, Object> viewSubjectServiceById(Long id) {
		return serviceSubService.viewServiceSubjectDetailsById(id);
	}

	@PostMapping("/activeDeactiveStatusSubjectService")
	public Map<Object, Object> activeDeactiveStatusSubjectService(Long id, int status, ServletRequest request) {
		return serviceSubService.activeDeactiveStatusServiceSubject(id, status, request);
	}

	@GetMapping("/get_all_service_subject_by_type_subType_termId")
	public Map<Object, Object> getAllServiceSubject(@RequestParam String type, @RequestParam String subType,
			@RequestParam Long termId) {
		return serviceSubService.getAllServiceSubjectByTypeSubTypeAndTerm(type, subType, termId);
	}

}
