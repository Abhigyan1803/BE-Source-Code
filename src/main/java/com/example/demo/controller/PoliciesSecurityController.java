package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.PoliciesSecurity;
import com.example.demo.service.PoliciesSecurityService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/policies-security")
@CrossOrigin
public class PoliciesSecurityController {
	
	@Autowired
	PoliciesSecurityService policyService;
	
	@PostMapping("/add-policy-security")
	public ResponseEntity<?> addDetails(PoliciesSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		PoliciesSecurity response = policyService.addDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-policy-security,"
				+ ConstantMessage.PoliciesSecurity_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.PoliciesSecurity_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-policy-security")
	public ResponseEntity<?> updateDetails(PoliciesSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		PoliciesSecurity response = policyService.updateDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-policy-security,"
				+ ConstantMessage.PoliciesSecurity_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.PoliciesSecurity_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-policy-status")
	public ResponseEntity<?> changeStatus(Long id , int status,ServletRequest request)
	{
		
		PoliciesSecurity response = policyService.changeStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-policy-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-policy-by-Id")
	public ResponseEntity<?> viewById(Long id)
	{
		PoliciesSecurity response = policyService.viewById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-policy-list")
	public ResponseEntity<?> getList(int status)
	{
		List<PoliciesSecurity> response = policyService.getList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
