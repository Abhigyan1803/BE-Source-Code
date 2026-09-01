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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.CyberPolicyType;
import com.example.demo.service.CyberPolicyTypeService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/cyberPolicyTypeController")
public class CyberPolicyTypeController {

	
	@Autowired
	CyberPolicyTypeService cyberPolicyTypeService;
	
	
	@GetMapping(value = "/getAllPolicyType")
	public ResponseEntity<?> getAllPolicyType() {
		List<CyberPolicyType> list = cyberPolicyTypeService.getAllCyberPolicy();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/addCyberPolicy")
	public ResponseEntity<?> addCyberPolicy(@RequestParam MultipartFile file,CyberPolicyType request,ServletRequest servletReq) {
		CyberPolicyType response = cyberPolicyTypeService.addCyberPolicy(file,request);
		FileWritting.createLog((HttpServletRequest)servletReq ,response.getId()+ ",add,"+"addCyberPolicy,"+ ConstantMessage.OK_MESSAGE+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK,response),
				HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getCyberPolicyById")
	public ResponseEntity<?> getCyberPolicyById(Long id) {
		CyberPolicyType response=cyberPolicyTypeService.getCyberPolicyById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK,response),
				HttpStatus.OK);
	}
	
	@PatchMapping(value = "/updateCyberPolicyById")
	public ResponseEntity<?> updateCyberPolicy(@RequestParam( required = false , value ="file") MultipartFile file,CyberPolicyType request,ServletRequest servletReq) {
		CyberPolicyType response=cyberPolicyTypeService.updateCyberPolicy(file,request);
		FileWritting.createLog((HttpServletRequest)servletReq ,response.getId()+ ",update,"+"updateCyberPolicyById,"+ ConstantMessage.OK_MESSAGE+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK,response),
				HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/activeDeacticePolicy")
	public ResponseEntity<?> activeDeActive(Long id,int status,ServletRequest servletReq) {
		CyberPolicyType response = cyberPolicyTypeService.activeDeActiveCyberPolicy(id, status);
		FileWritting.createLog((HttpServletRequest)servletReq ,response.getId()+ ",status,"+"activeDeacticePolicy,"+ ConstantMessage.OK_MESSAGE+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK,response),
				HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllPolicyTypeHomePage")
	public ResponseEntity<?> getAllPolicyTypeHomePage() {
		List<CyberPolicyType> list = cyberPolicyTypeService.getAllCyberPolicyHomepage();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}
	
	
}
