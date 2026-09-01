package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.ApptType;
import com.example.demo.service.ApptTypeService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/apptType")
public class ApptTypeController {

	@Autowired
	ApptTypeService apptTypeService;
	
	@GetMapping(value = "/getAllAppt")
	public ResponseEntity<?> apptList() {
		List<ApptType> list = apptTypeService.getAllApptList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}
	
	
}
