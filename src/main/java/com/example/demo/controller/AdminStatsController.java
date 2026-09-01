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

import com.example.demo.model.Intake;
import com.example.demo.model.POC;
import com.example.demo.service.AdminStatsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/statsController")
@CrossOrigin
public class AdminStatsController {
	
	@Autowired
	AdminStatsService statsService;
	
	
	@PostMapping("/add-POC")
	public ResponseEntity<?> addPOCDetails(POC details , MultipartFile docfile , ServletRequest request)
	{
		
		POC response = statsService.addPOCDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-POC,"
				+ ConstantMessage.POC_ADDED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.POC_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-POC")
	public ResponseEntity<?> updatePOCDetails(POC details , MultipartFile docfile , ServletRequest request)
	{
		
		POC response = statsService.updatePOCDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-POC,"
				+ ConstantMessage.POC_UDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.POC_UDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-POC-Status")
	public ResponseEntity<?> changePOCStatus(Long id , int status,ServletRequest request)
	{
		
		POC response = statsService.changePOCStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-POC-Status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-POC")
	public ResponseEntity<?> viewPOCById(Long id)
	{
		POC response = statsService.viewPOCById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-POC-list")
	public ResponseEntity<?> getPOCList(int status)
	{
		List<POC> response = statsService.getPOCList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	//==============================
	@PostMapping("/add-intake")
	public ResponseEntity<?> addIntakeDetails(Intake details , MultipartFile docfile , ServletRequest request)
	{
		
		Intake response = statsService.addIntakeDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-intake,"
				+ ConstantMessage.INTAKE_ADDED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.INTAKE_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-intake")
	public ResponseEntity<?> updateIntakeDetails(Intake details , MultipartFile docfile , ServletRequest request)
	{
		
		Intake response = statsService.updateIntakeDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-intake,"
				+ ConstantMessage.INTAKE_UDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.INTAKE_UDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-intake-Status")
	public ResponseEntity<?> changeIntakeStatus(Long id , int status,ServletRequest request)
	{
		
		Intake response = statsService.changeIntakeStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-intake-Status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-intake")
	public ResponseEntity<?> viewIntakeById(Long id)
	{
		Intake response = statsService.viewIntakeById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-intake-list")
	public ResponseEntity<?> getIntakeList(int status)
	{
		List<Intake> response = statsService.getIntakeList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
