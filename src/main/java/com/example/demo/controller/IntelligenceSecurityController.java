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
import com.example.demo.model.PoliciesIntelligenceSecurity;
import com.example.demo.model.ReportsIntelligenceSecurity;
import com.example.demo.service.IntelligenceSecurityService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/intelligence_security")
@CrossOrigin
public class IntelligenceSecurityController {
	
	@Autowired
	IntelligenceSecurityService intelligenceService;
	
	
	@PostMapping("/add-policy")
	public ResponseEntity<?> addPoliciesDetails(PoliciesIntelligenceSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		PoliciesIntelligenceSecurity response = intelligenceService.addPoliciesDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-intelligence-security,"
				+ ConstantMessage.POLICY_INTELLIGENCE_SECURITY_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.POLICY_INTELLIGENCE_SECURITY_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-policy")
	public ResponseEntity<?> updatePoilcyDetails(PoliciesIntelligenceSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		PoliciesIntelligenceSecurity response = intelligenceService.updatePoliciesDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-policyIntelligence-security,"
				+ ConstantMessage.POLICY_INTELLIGENCE_SECURITY_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.POLICY_INTELLIGENCE_SECURITY_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-policy-status")
	public ResponseEntity<?> changePoilcyStatus(Long id , int status,ServletRequest request)
	{
		
		PoliciesIntelligenceSecurity response = intelligenceService.changePolicyStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-policyIntelligence-security-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-policy")
	public ResponseEntity<?> viewPoilcyById(Long id)
	{
		PoliciesIntelligenceSecurity response = intelligenceService.viewPoliciesById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-policy-list")
	public ResponseEntity<?> getPoilcyList(int status)
	{
		List<PoliciesIntelligenceSecurity> response = intelligenceService.getPoliciesList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/add-report")
	public ResponseEntity<?> addReportDetails(ReportsIntelligenceSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		ReportsIntelligenceSecurity response = intelligenceService.addReportDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-report-intelligence-security,"
				+ ConstantMessage.REPORTS_INTELLIGENCE_SECURITY_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.REPORTS_INTELLIGENCE_SECURITY_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-report")
	public ResponseEntity<?> updateReportsDetails(ReportsIntelligenceSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		ReportsIntelligenceSecurity response = intelligenceService.updateReportDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-report-intelligence-security,"
				+ ConstantMessage.REPORTS_INTELLIGENCE_SECURITY_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.REPORTS_INTELLIGENCE_SECURITY_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-report-status")
	public ResponseEntity<?> changeReportStatus(Long id , int status,ServletRequest request)
	{
		
		ReportsIntelligenceSecurity response = intelligenceService.changeReportStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-reportIntelligenceSecurity-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-report")
	public ResponseEntity<?> viewReportById(Long id)
	{
		ReportsIntelligenceSecurity response = intelligenceService.viewReportById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-report-list")
	public ResponseEntity<?> getReportList(int status)
	{
		List<ReportsIntelligenceSecurity> response = intelligenceService.getReportList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}


}
