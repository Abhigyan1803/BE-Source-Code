package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Course;
import com.example.demo.myexception.MyException;
import com.example.demo.service.CoursesService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/course")
public class CourseController {
	
	@Autowired
	CoursesService courseService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() throws MyException
	{
		List<Course> response = courseService.getAll();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
   }

}
