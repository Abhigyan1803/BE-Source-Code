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

import com.example.demo.model.ACSFP;
import com.example.demo.model.CommunicationInfra;
import com.example.demo.model.OtherSecurityInfra;
import com.example.demo.model.SRESecurity;
import com.example.demo.service.SecurityApparatusService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/security_apparatus")
@CrossOrigin
public class SecurityApparatusController {
	
	@Autowired
	SecurityApparatusService securityService ;
	
//==================SRESecurity ================================
	@PostMapping("/add-SRE-security")
	public ResponseEntity<?> addSREDetails(SRESecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		SRESecurity response = securityService.addSREDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-SRE-security,"
				+ ConstantMessage.SRE_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.SRE_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-SRE-security")
	public ResponseEntity<?> updateSREDetails(SRESecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		SRESecurity response = securityService.updateSREDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-SRE-security,"
				+ ConstantMessage.SRE_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.SRE_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-SRE-status")
	public ResponseEntity<?> changeSREStatus(Long id , int status,ServletRequest request)
	{
		
		SRESecurity response = securityService.changeSREStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-SRE-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-SRE-by-Id")
	public ResponseEntity<?> viewSREById(Long id)
	{
		SRESecurity response = securityService.viewSREById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-SRE-list")
	public ResponseEntity<?> getSREList(int status)
	{
		List<SRESecurity> response = securityService.getSREList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
//===================ACSFP============================================
	@PostMapping("/add-ACSFP-security")
	public ResponseEntity<?> addACSFPDetails(ACSFP details , MultipartFile docfile , ServletRequest request)
	{
		
		ACSFP response = securityService.addACSFPDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-ACSFP-security,"
				+ ConstantMessage.ACSFP_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.ACSFP_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-ACSFP-security")
	public ResponseEntity<?> updateACSFPDetails(ACSFP details , MultipartFile docfile , ServletRequest request)
	{
		
		ACSFP response = securityService.updateACSFPDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-ACSFP-security,"
				+ ConstantMessage.ACSFP_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.ACSFP_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-ACSFP-status")
	public ResponseEntity<?> changeACSFPStatus(Long id , int status,ServletRequest request)
	{
		
		ACSFP response = securityService.changeACSFPStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-ACSFP-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-ACSFP-by-Id")
	public ResponseEntity<?> viewACSFPById(Long id)
	{
		ACSFP response = securityService.viewACSFPById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-ACSFP-list")
	public ResponseEntity<?> getACSFPList(int status)
	{
		List<ACSFP> response = securityService.getACSFPList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
//================OtherSecurityInfra======================================
	@PostMapping("/add-OtherInfra-security")
	public ResponseEntity<?> addOtherInfraDetails(OtherSecurityInfra details , MultipartFile docfile , ServletRequest request)
	{
		
		OtherSecurityInfra response = securityService.addOtherInfraDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-OtherInfra-security,"
				+ ConstantMessage.OtherInfra_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.OtherInfra_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-OtherInfra-security")
	public ResponseEntity<?> updateOtherInfraDetails(OtherSecurityInfra details , MultipartFile docfile , ServletRequest request)
	{
		
		OtherSecurityInfra response = securityService.updateOtherInfraDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-ACSFP-security,"
				+ ConstantMessage.OtherInfra_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.OtherInfra_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-OtherInfra-status")
	public ResponseEntity<?> changeOtherInfraStatus(Long id , int status,ServletRequest request)
	{
		
		OtherSecurityInfra response = securityService.changeOtherInfraStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-OtherInfra-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-OtherInfra-by-Id")
	public ResponseEntity<?> viewOtherInfraById(Long id)
	{
		OtherSecurityInfra response = securityService.viewOtherInfraById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-OtherInfra-list")
	public ResponseEntity<?> getOtherInfraList(int status)
	{
		List<OtherSecurityInfra> response = securityService.getOtherInfraList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
//========================CommunicationInfra============================
	@PostMapping("/add-CommunicationInfra-security")
	public ResponseEntity<?> addCommunicationInfraDetails(CommunicationInfra details , MultipartFile docfile , ServletRequest request)
	{
		
		CommunicationInfra response = securityService.addCommunicationInfraDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-CommunicationInfra-security,"
				+ ConstantMessage.CommunicationInfra_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.CommunicationInfra_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-CommunicationInfra-security")
	public ResponseEntity<?> updateCommunicationInfraDetails(CommunicationInfra details , MultipartFile docfile , ServletRequest request)
	{
		
		CommunicationInfra response = securityService.updateCommunicationInfraDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-CommunicationInfra-security,"
				+ ConstantMessage.CommunicationInfra_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.CommunicationInfra_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-CommunicationInfra-status")
	public ResponseEntity<?> changeCommunicationInfraStatus(Long id , int status,ServletRequest request)
	{
		
		CommunicationInfra response = securityService.changeCommunicationInfraStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-CommunicationInfra-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-CommunicationInfra-by-Id")
	public ResponseEntity<?> viewCommunicationInfraById(Long id)
	{
		CommunicationInfra response = securityService.viewCommunicationInfraById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-CommunicationInfra-list")
	public ResponseEntity<?> getCommunicationInfraList(int status)
	{
		List<CommunicationInfra> response = securityService.getCommunicationInfraList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
