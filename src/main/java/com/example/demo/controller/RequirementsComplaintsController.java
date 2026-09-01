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

import com.example.demo.model.RequirementComplaint;
import com.example.demo.service.RequirementComplaintService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/requirements-complaints")
@CrossOrigin
public class RequirementsComplaintsController {
	
	@Autowired
	RequirementComplaintService complaintsService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addDetails(RequirementComplaint  record,MultipartFile docFile , ServletRequest request)
	{
		RequirementComplaint response = complaintsService.addDetails(record , docFile , request);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-complaints-requirements,"
				+ ConstantMessage.COMPLAINTS_REQUIREMENTS_ADDED+ "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.COMPLAINTS_REQUIREMENTS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateDetails(RequirementComplaint  record,MultipartFile docFile , ServletRequest request)
	{
		RequirementComplaint response = complaintsService.updateDetails(record, docFile ,  request);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-complaints-requirements,"
				+ ConstantMessage.COMPLAINTS_REQUIREMENTS_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.COMPLAINTS_REQUIREMENTS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-status")
	public ResponseEntity<?> changeStatus(Long id , int status,ServletRequest request)
	{
		
		RequirementComplaint response = complaintsService.changeStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-complaints-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view")
	public ResponseEntity<?> viewITById(Long id)
	{
		RequirementComplaint response = complaintsService.viewById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-list")
	public ResponseEntity<?> getReportList(int status)
	{
		List<RequirementComplaint> response = complaintsService.getList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-by-requestNature")
	public ResponseEntity<?> getByRequestNature(String requestNature , int status)
	{
		List<RequirementComplaint> response = complaintsService.getByRequestNature(requestNature , status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	
	

}
