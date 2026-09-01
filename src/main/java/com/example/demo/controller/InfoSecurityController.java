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

import com.example.demo.model.CombatEntryPasses;
import com.example.demo.model.PoliciesInfoSecurity;
import com.example.demo.model.ReportsInfoSecurity;
import com.example.demo.service.InfoSecurityService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/info_security")
@CrossOrigin
public class InfoSecurityController {
	
	@Autowired
	InfoSecurityService infoSecurityService;
	
	@PostMapping("/add-policy")
	public ResponseEntity<?> addPoilcyDetails(PoliciesInfoSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		PoliciesInfoSecurity response = infoSecurityService.addPoliciesDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-policy-info-security,"
				+ ConstantMessage.POLICY_INFO_SECURITY_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.POLICY_INFO_SECURITY_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-policy")
	public ResponseEntity<?> updatePoilcyDetails(PoliciesInfoSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		PoliciesInfoSecurity response = infoSecurityService.updatePoliciesDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-policy-info-security,"
				+ ConstantMessage.POLICY_INFO_SECURITY_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.POLICY_INFO_SECURITY_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-policy-status")
	public ResponseEntity<?> changePoilcyStatus(Long id , int status,ServletRequest request)
	{
		
		PoliciesInfoSecurity response = infoSecurityService.changePolicyStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-policyInfoSecurity-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-policy")
	public ResponseEntity<?> viewPoilcyById(Long id)
	{
		PoliciesInfoSecurity response = infoSecurityService.viewPoliciesById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-policy-list")
	public ResponseEntity<?> getPoilcyList(int status)
	{
		List<PoliciesInfoSecurity> response = infoSecurityService.getPoliciesList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	
	@PostMapping("/add-report")
	public ResponseEntity<?> addReportDetails(ReportsInfoSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		ReportsInfoSecurity response = infoSecurityService.addReportDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-report-info-security,"
				+ ConstantMessage.REPORTS_INFO_SECURITY_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.REPORTS_INFO_SECURITY_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-report")
	public ResponseEntity<?> updateReportsDetails(ReportsInfoSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		ReportsInfoSecurity response = infoSecurityService.updateReportDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-report-info-security,"
				+ ConstantMessage.REPORTS_INFO_SECURITY_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.REPORTS_INFO_SECURITY_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-report-status")
	public ResponseEntity<?> changeReportStatus(Long id , int status,ServletRequest request)
	{
		
		ReportsInfoSecurity response = infoSecurityService.changeReportStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-reportInfoSecurity-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-report")
	public ResponseEntity<?> viewReportById(Long id)
	{
		ReportsInfoSecurity response = infoSecurityService.viewReportById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-report-list")
	public ResponseEntity<?> getReportList(int status)
	{
		List<ReportsInfoSecurity> response = infoSecurityService.getReportList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
