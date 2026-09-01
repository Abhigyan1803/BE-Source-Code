package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.myexception.MyException;
import com.example.demo.service.UserService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
public class UserController {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	UserService service;

	@PostMapping(value = "/user/add-user")
	public ResponseEntity<?> addUser(@RequestBody User usr,ServletRequest request) throws MyException {
		User response = service.createUser(usr);
		FileWritting.createLog((HttpServletRequest)request ,response.getId()+ ",add,"+"add-user,"+ ConstantMessage.USER_ADDED+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.USER_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@Transactional
	@GetMapping(value = "/user/get-all-user")
	public ResponseEntity<?> getAllUsers() {
		List<User> response = service.getAllUsers();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/user/get-user")
	public ResponseEntity<?> getUsersById(@RequestParam Integer id) {
		Optional<User> response = service.getUserById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/user/update-user")
	public ResponseEntity<?> updateUser(@RequestBody User usr,ServletRequest request) throws MyException {
		User response = service.updateUser(usr);
		FileWritting.createLog((HttpServletRequest)request ,response.getId()+ ",update,"+"update-user,"+ ConstantMessage.USER_UPDATED+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.USER_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
