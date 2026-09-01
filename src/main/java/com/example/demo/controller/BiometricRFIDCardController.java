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
import com.example.demo.model.BiometricRFIDCard;
import com.example.demo.service.BiometricRFIDCardService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/biometric-card")
@CrossOrigin
public class BiometricRFIDCardController {

	@Autowired
	BiometricRFIDCardService biometricService;
	
	@PostMapping("/add-biometric-card")
	public ResponseEntity<?> addDetails(BiometricRFIDCard details , MultipartFile docfile , ServletRequest request)
	{
		
		BiometricRFIDCard response = biometricService.addDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-biometric-card,"
				+ ConstantMessage.BIOMETRIC_CARD_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.BIOMETRIC_CARD_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-biometric-card")
	public ResponseEntity<?> updateDetails(BiometricRFIDCard details , MultipartFile docfile , ServletRequest request)
	{
		
		BiometricRFIDCard response = biometricService.updateDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-biometric-card,"
				+ ConstantMessage.BIOMETRIC_CARD_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.BIOMETRIC_CARD_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-status")
	public ResponseEntity<?> changeStatus(Long id , int status,ServletRequest request)
	{
		
		BiometricRFIDCard response = biometricService.changeStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-biometric-card-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-by-Id")
	public ResponseEntity<?> viewCVRById(Long id)
	{
		BiometricRFIDCard response = biometricService.viewById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-list")
	public ResponseEntity<?> getCVRList(int status)
	{
		List<BiometricRFIDCard> response = biometricService.getList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
}
