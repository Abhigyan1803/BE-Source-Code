package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DRILLSubject;
import com.example.demo.myexception.MyException;
import com.example.demo.service.DRILLSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/drillSubjectController")
@CrossOrigin
public class DRILLSubjectController {

	@Autowired
	private DRILLSubjectService drillSubjectService;

	@PostMapping(value = "/add-subject")
	public ResponseEntity<?> addSubject(@RequestBody DRILLSubject drillSubject) throws MyException {
		DRILLSubject isAlreadyExist = drillSubjectService.isSubjectExist(drillSubject);
		if (isAlreadyExist == null) {
			DRILLSubject response = drillSubjectService.createSubject(drillSubject);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.DRILL_SUBJECT_ADDED, HttpStatus.OK, response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.DRILL_SUBJECT_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get-subject-by-termid")
	public ResponseEntity<?> getSubjectByTermId(@RequestParam Long id) {
		List<DRILLSubject> list = drillSubjectService.getAllSubjectByTermId(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-all-subject-by-status")
	public ResponseEntity<?> getAllSubjectByStatus(@RequestParam Integer status) {
		List<DRILLSubject> list = drillSubjectService.getAllSubjectByStatus(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-all-subject")
	public ResponseEntity<?> getAllSubject() {
		List<DRILLSubject> list = drillSubjectService.getAllSubject();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PutMapping(value = "/update-subject")
	public ResponseEntity<?> updateSubject(@RequestBody DRILLSubject drillSubject) throws MyException {
		DRILLSubject isAlreadyExist = drillSubjectService.validateSubjectExist(drillSubject);
		if (isAlreadyExist == null) {
			DRILLSubject response = drillSubjectService.updateSubject(drillSubject);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.DRILL_SUBJECT_UPDATED, HttpStatus.OK, response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.DRILL_SUBJECT_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get-subject-by-id")
	public ResponseEntity<?> getSubjectById(@RequestParam Long id) {
		DRILLSubject response = drillSubjectService.getSubjectById(id).get();
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

}
