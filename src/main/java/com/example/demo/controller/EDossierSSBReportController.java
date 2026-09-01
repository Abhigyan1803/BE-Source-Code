package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EDossierSSBReport;
import com.example.demo.service.EDossierSSBReportService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/edossier_ssb_report_controller")
public class EDossierSSBReportController {
	@Autowired
	private EDossierSSBReportService service;

	@PostMapping("/add_edossier_ssb_report")
	public ResponseEntity<?> addEDossierSSBReport(@RequestBody EDossierSSBReport eDossierSSBReport) {
		EDossierSSBReport alreadyExist = service.getByServiceId(eDossierSSBReport.getServiceId());
		if (alreadyExist == null) {
			EDossierSSBReport response = service.addAcademicSyllabus(eDossierSSBReport);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@GetMapping("/get_edossier_ssb_report_By_Id")
	public ResponseEntity<?> getById(Long id) {
		EDossierSSBReport response = service.getById(id);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_edossier_ssb_report_By_serviceId")
	public ResponseEntity<?> getByServiceId(String serviceId) {
		EDossierSSBReport response = service.getByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping("/update_edossier_ssb_report")
	public ResponseEntity<?> updateEDossierSSBReport(@RequestBody EDossierSSBReport EDossierSSBReport) {
		EDossierSSBReport response = service.updateEDossierSSBReport(EDossierSSBReport);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}
}
