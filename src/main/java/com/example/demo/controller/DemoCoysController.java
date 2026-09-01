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
import com.example.demo.model.DemoCoys;
import com.example.demo.service.DemoCoysService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/demoCoys")
@CrossOrigin
public class DemoCoysController {
	
	@Autowired
	DemoCoysService coysService;
	
	@PostMapping("/add-coysService")
	public ResponseEntity<?> addDetails(DemoCoys details , MultipartFile docfile , ServletRequest request)
	{
		
		DemoCoys response = coysService.addDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-coysService,"
				+ ConstantMessage.DEMO_COYS_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.DEMO_COYS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-coysService")
	public ResponseEntity<?> updateDetails(DemoCoys details , MultipartFile docfile , ServletRequest request)
	{
		
		DemoCoys response = coysService.updateDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-coysService,"
				+ ConstantMessage.DEMO_COYS_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.DEMO_COYS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-status")
	public ResponseEntity<?> changeStatus(Long id , int status,ServletRequest request)
	{
		
		DemoCoys response = coysService.changeStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-coysService-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-by-Id")
	public ResponseEntity<?> viewById(Long id)
	{
		DemoCoys response = coysService.viewById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-list")
	public ResponseEntity<?> getList(int status)
	{
		List<DemoCoys> response = coysService.getList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	

}
