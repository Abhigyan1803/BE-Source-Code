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
import com.example.demo.model.PoliciesProphylacticSecurity;
import com.example.demo.model.ReportsProphylacticSecurity;
import com.example.demo.service.ProphylacticSecurityService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/prophylactic_security")
@CrossOrigin
public class ProphylacticSecurityController {
	
	@Autowired
	ProphylacticSecurityService prophylacticService;
	
	
	@PostMapping("/add-policy")
	public ResponseEntity<?> addPoliciesDetails(PoliciesProphylacticSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		PoliciesProphylacticSecurity response = prophylacticService.addPoliciesDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-policyProphylactic-security,"
				+ ConstantMessage.POLICY_PROPHYLACTIC_SECURITY_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.POLICY_PROPHYLACTIC_SECURITY_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-policy")
	public ResponseEntity<?> updatePoilcyDetails(PoliciesProphylacticSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		PoliciesProphylacticSecurity response = prophylacticService.updatePoliciesDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-policyProphylactic-security,"
				+ ConstantMessage.POLICY_PROPHYLACTIC_SECURITY_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.POLICY_PROPHYLACTIC_SECURITY_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-policy-status")
	public ResponseEntity<?> changePoilcyStatus(Long id , int status,ServletRequest request)
	{
		
		PoliciesProphylacticSecurity response = prophylacticService.changePolicyStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-policyProphylactic-security-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-policy")
	public ResponseEntity<?> viewPoilcyById(Long id)
	{
		PoliciesProphylacticSecurity response = prophylacticService.viewPoliciesById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-policy-list")
	public ResponseEntity<?> getPoilcyList(int status)
	{
		List<PoliciesProphylacticSecurity> response = prophylacticService.getPoliciesList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/add-report")
	public ResponseEntity<?> addReportDetails(ReportsProphylacticSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		ReportsProphylacticSecurity response = prophylacticService.addReportDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-report-prophylactic-security,"
				+ ConstantMessage.REPORTS_PROPHYLACTIC_SECURITY_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.REPORTS_PROPHYLACTIC_SECURITY_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-report")
	public ResponseEntity<?> updateReportsDetails(ReportsProphylacticSecurity details , MultipartFile docfile , ServletRequest request)
	{
		
		ReportsProphylacticSecurity response = prophylacticService.updateReportDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-report-prophylactic-security,"
				+ ConstantMessage.REPORTS_PROPHYLACTIC_SECURITY_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.REPORTS_PROPHYLACTIC_SECURITY_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-report-status")
	public ResponseEntity<?> changeReportStatus(Long id , int status,ServletRequest request)
	{
		
		ReportsProphylacticSecurity response = prophylacticService.changeReportStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-reportProphylacticSecurity-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-report")
	public ResponseEntity<?> viewReportById(Long id)
	{
		ReportsProphylacticSecurity response = prophylacticService.viewReportById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-report-list")
	public ResponseEntity<?> getReportList(int status)
	{
		List<ReportsProphylacticSecurity> response = prophylacticService.getReportList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	

}
