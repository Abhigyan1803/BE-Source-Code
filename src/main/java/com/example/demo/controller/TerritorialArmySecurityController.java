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

import com.example.demo.model.TerritorialArmySecurity;
import com.example.demo.service.TerritorialArmySecurityService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/territorial-army-security")
@CrossOrigin
public class TerritorialArmySecurityController {
	
	@Autowired
	TerritorialArmySecurityService  territorialArmyService;
	
	@PostMapping("/add-territorialArmy-security")
	public ResponseEntity<?> addDetails(TerritorialArmySecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		TerritorialArmySecurity response = territorialArmyService.addDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-territorialArmy-security,"
				+ ConstantMessage.TERRITORIAL_ARMY_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.TERRITORIAL_ARMY_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-territorialArmy-security")
	public ResponseEntity<?> updateDetails(TerritorialArmySecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		TerritorialArmySecurity response = territorialArmyService.updateDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-territorialArmy-security,"
				+ ConstantMessage.TERRITORIAL_ARMY_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.TERRITORIAL_ARMY_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-status")
	public ResponseEntity<?> changeStatus(Long id , int status,ServletRequest request)
	{
		
		TerritorialArmySecurity response = territorialArmyService.changeStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-territorialArmy-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-by-Id")
	public ResponseEntity<?> viewById(Long id)
	{
		TerritorialArmySecurity response = territorialArmyService.viewById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-list")
	public ResponseEntity<?> getList(int status)
	{
		List<TerritorialArmySecurity> response = territorialArmyService.getList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	

}
