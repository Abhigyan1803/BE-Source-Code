package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.example.demo.model.CadetOtherExams;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminCadetOtherExamService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/other-exam")
public class AdminCadetOtherExamController {

	@Autowired
	AdminCadetOtherExamService examService;

	@PostMapping(value = "/add-exam")
	public ResponseEntity<?> addExam(@RequestBody CadetOtherExams exam, ServletRequest request) throws MyException {
		CadetOtherExams response = examService.createExam(exam);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "other-exam," + ConstantMessage.EXAM_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.EXAM_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-exam-list")
	public ResponseEntity<?> getExamList() {
		List<CadetOtherExams> list = examService.getAllExam();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-exam-by-id")
	public ResponseEntity<?> getExamById(@RequestParam Long id) {
		Optional<CadetOtherExams> list = examService.getExamById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-exam")
	public ResponseEntity<?> updateExam(@RequestBody CadetOtherExams exam, ServletRequest request) {
		CadetOtherExams response = examService.updateExam(exam);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "other-exam," + ConstantMessage.EXAM_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.EXAM_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
