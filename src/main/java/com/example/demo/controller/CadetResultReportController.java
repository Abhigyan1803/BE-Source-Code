package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.CadetResultReportPayload1;

import com.example.demo.service.CadetResultReportService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;


@RestController
@CrossOrigin
@RequestMapping("api/cadet_result_report")
public class CadetResultReportController {

	@Autowired
	private CadetResultReportService cadetResultReportService;

	@GetMapping("/get_all_cadet_result_report")
	public ResponseEntity<?> getCadetResultReport() {
		CadetResultReportPayload1 response = cadetResultReportService.getCadetResultReport();
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
