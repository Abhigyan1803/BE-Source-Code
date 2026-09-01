package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Department;
import com.example.demo.service.DepartmentsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/deptController")
public class DepartmentController {

	@Autowired
	DepartmentsService departmentsService;
	
	
	@GetMapping(value = "/getAllDepartments")
	public ResponseEntity<?> getAllDepartments() {
		    List<Department> list=departmentsService.getAllDepartments();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

}
