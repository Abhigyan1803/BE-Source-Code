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

import com.example.demo.model.GSOrganizationChart;
import com.example.demo.model.GSPosition;
import com.example.demo.myexception.MyException;
import com.example.demo.service.GSOrganizationChartService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/gsController")
public class GSOrganizationController {
	
	@Autowired
	GSOrganizationChartService gsOrganizationService;
	
	@PostMapping("/add-GsOrg")
	public ResponseEntity<?> addOrganizationDetails(GSOrganizationChart details , MultipartFile docfile , ServletRequest request) throws MyException
	{
		
		GSOrganizationChart response = gsOrganizationService.addOrganization(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-GsOrg,"
				+ ConstantMessage.GS_ORGANIZATION_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.GS_ORGANIZATION_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-GsOrg")
	public ResponseEntity<?> updateOrganization(GSOrganizationChart details , MultipartFile docfile , ServletRequest request)
	{
		
		GSOrganizationChart response = gsOrganizationService.updateOrganization(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-GsOrg,"
				+ ConstantMessage.GS_ORGANIZATION_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.GS_ORGANIZATION_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-gsOrg-status")
	public ResponseEntity<?> changeOrganizationStatus(Long id , int status,ServletRequest request)
	{
		
		GSOrganizationChart response = gsOrganizationService.changeStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-gsOrg-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-gsOrg")
	public ResponseEntity<?> viewOrganizationById(Long id)
	{
		GSOrganizationChart response = gsOrganizationService.viewById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-gsOrg-list")
	public ResponseEntity<?> getOrganizationList(int status)
	{
		List<GSOrganizationChart> response = gsOrganizationService.getOrganizationList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping("/get-gsPositions")
	public ResponseEntity<?> getGsPositionsList(int status)
	{
		List<GSPosition> response = gsOrganizationService.getGsPositions();
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
