package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CadetCampBnCdrResult;
import com.example.demo.model.CadetCampCoyCdrResult;
import com.example.demo.model.CadetCampPlCdrResult;
import com.example.demo.myexception.MyException;
import com.example.demo.service.CadetCampBnMarksService;
import com.example.demo.service.CadetCampCoyService;
import com.example.demo.service.CadetPlCdrCampResultService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/campResult")
public class CampMarkResultController {

	@Autowired
	CadetPlCdrCampResultService cadetPlCdrCampResultService;

	@Autowired
	CadetCampCoyService cadetCampCoyService;

	@Autowired
	CadetCampBnMarksService cadetCampBnMarksService;

	@PostMapping(value = "/savePlMarks")
	public ResponseEntity<?> addPlResult(@RequestBody List<CadetCampPlCdrResult> result, ServletRequest request)
			throws MyException {
		List<CadetCampPlCdrResult> response = cadetPlCdrCampResultService.addCampPlResult(result);
		FileWritting.createLog((HttpServletRequest) request, response.get(0).getServiceId() + ",add,"
				+ "Platoon Commander Camp Result," + ConstantMessage.MARKS_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getCadetCampPlResult")
	public ResponseEntity<?> getResultCadetPlCamp(@RequestParam String serviceId, @RequestParam Long termId) {
		List<CadetCampPlCdrResult> response = cadetPlCdrCampResultService.getCadetResult(serviceId, termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "updatePlCampResult")
	public ResponseEntity<?> updatePlCampResult(@RequestBody List<CadetCampPlCdrResult> result, ServletRequest request)
			throws MyException {
		List<CadetCampPlCdrResult> response = cadetPlCdrCampResultService.updateResult(result);
		FileWritting.createLog((HttpServletRequest) request, response.get(0).getServiceId() + ",updated,"
				+ "Platoon Commander Camp Result," + ConstantMessage.MARKS_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/saveCoyMarks")
	public ResponseEntity<?> addCoyCampResult(@RequestBody List<CadetCampCoyCdrResult> result, ServletRequest request)
			throws MyException {

		List<CadetCampCoyCdrResult> response = cadetCampCoyService.addCampCoyResult(result);
		FileWritting.createLog((HttpServletRequest) request, response.get(0).getServiceId() + ",add,"
				+ "Company Camp Result," + ConstantMessage.MARKS_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getCadetCampCoyResult")
	public ResponseEntity<?> getResultCadetCoyCamp(@RequestParam String serviceId, @RequestParam Long termId) {
		List<CadetCampCoyCdrResult> response = cadetCampCoyService.getCadetCampCoyResult(serviceId, termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "updateCoyCampResult")
	public ResponseEntity<?> updateCoyCampResult(@RequestBody List<CadetCampCoyCdrResult> result,
			ServletRequest request) throws MyException {
		List<CadetCampCoyCdrResult> response = cadetCampCoyService.updateResult(result);
		FileWritting.createLog((HttpServletRequest) request, response.get(0).getServiceId() + ",update,"
				+ "Company Camp Result," + ConstantMessage.MARKS_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping(value = "/saveBnMarks")
	public ResponseEntity<?> addBnCampResult(@RequestBody List<CadetCampBnCdrResult> result, ServletRequest request)
			throws MyException {
		List<CadetCampBnCdrResult> response = cadetCampBnMarksService.addCampBnResult(result);
		FileWritting.createLog((HttpServletRequest) request, response.get(0).getServiceId() + ",add,"
				+ "Battalion Camp Result," + ConstantMessage.MARKS_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getCadetCampBnResult")
	public ResponseEntity<?> getResultCadetBnCampResult(@RequestParam String serviceId, @RequestParam Long termId) {
		List<CadetCampBnCdrResult> response = cadetCampBnMarksService.getCadetCampBnResult(serviceId, termId);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "updateBnCampResult")
	public ResponseEntity<?> updateBnCampResult(@RequestBody List<CadetCampBnCdrResult> result, ServletRequest request)
			throws MyException {
		List<CadetCampBnCdrResult> response = cadetCampBnMarksService.updateResultBn(result);
		FileWritting.createLog((HttpServletRequest) request, response.get(0).getServiceId() + ",update,"
				+ "Battalion Camp Result," + ConstantMessage.MARKS_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.MARKS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
