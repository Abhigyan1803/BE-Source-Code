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

import com.example.demo.model.CommunicationSecCharter;
import com.example.demo.model.ITSecCharter;
import com.example.demo.service.CharterITAndCommunicationService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/charter-IT&Communication")
@CrossOrigin
public class CharterITAndCommunicationController {
	
	@Autowired
	CharterITAndCommunicationService charterService;
	
	@PostMapping("/add-ITCharter")
	public ResponseEntity<?> addITDetails(ITSecCharter  record,MultipartFile docFile , ServletRequest request)
	{
		ITSecCharter response = charterService.addITDetails(record, docFile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-IT-charter,"
				+ ConstantMessage.IT_SEC_CHARTER_ADDED+ "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.IT_SEC_CHARTER_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-ITCharter")
	public ResponseEntity<?> updateITDetails(ITSecCharter  record,MultipartFile docFile , ServletRequest request)
	{
		ITSecCharter response = charterService.updateITDetails(record, docFile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-IT-charter,"
				+ ConstantMessage.IT_SEC_CHARTER_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.IT_SEC_CHARTER_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-ITCharter-status")
	public ResponseEntity<?> changeITStatus(Long id , int status,ServletRequest request)
	{
		
		ITSecCharter response = charterService.changeITStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-ITCharter-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-IT-Charter")
	public ResponseEntity<?> viewITById(Long id)
	{
		ITSecCharter response = charterService.viewITById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-ITCharter-list")
	public ResponseEntity<?> getReportList(int status)
	{
		List<ITSecCharter> response = charterService.getITList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	
	@PostMapping("/add-communication")
	public ResponseEntity<?> addCommunicationDetails(CommunicationSecCharter  record,MultipartFile docFile , ServletRequest request)
	{
		CommunicationSecCharter response = charterService.addCommunicationDetails(record, docFile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-communication-charter,"
				+ ConstantMessage.COMMUNICATION_SEC_CHARTER_ADDED+ "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.COMMUNICATION_SEC_CHARTER_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-communication")
	public ResponseEntity<?> updateCommunicationDetails(CommunicationSecCharter  record,MultipartFile docFile , ServletRequest request)
	{
		CommunicationSecCharter response = charterService.updateCommunicationDetails(record, docFile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-communication-charter,"
				+ ConstantMessage.COMMUNICATION_SEC_CHARTER_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.COMMUNICATION_SEC_CHARTER_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-communication-status")
	public ResponseEntity<?> changeCommunicationStatus(Long id , int status,ServletRequest request)
	{
		
		CommunicationSecCharter response = charterService.changeCommunicationStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-communication-chater-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-communication-Charter")
	public ResponseEntity<?> viewCommunicationById(Long id)
	{
		CommunicationSecCharter response = charterService.viewCommunicationById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-communication-list")
	public ResponseEntity<?> getCommunicationList(int status)
	{
		List<CommunicationSecCharter> response = charterService.getCommunicationList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
