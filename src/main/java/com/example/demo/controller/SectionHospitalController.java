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
import com.example.demo.model.SectionHospital;
import com.example.demo.service.SectionHospitalService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/section-hospital")
public class SectionHospitalController {
	
	@Autowired
	SectionHospitalService sectionHospitalService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addDetails(SectionHospital details , MultipartFile docfile , ServletRequest request)
	{
		
		SectionHospital response = sectionHospitalService.addDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-section-hospital,"
				+ ConstantMessage.SECTION_HOSPITAL_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.SECTION_HOSPITAL_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateDetails(SectionHospital details , MultipartFile docfile , ServletRequest request)
	{
		
		SectionHospital response = sectionHospitalService.updateDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-section-hospital,"
				+ ConstantMessage.SECTION_HOSPITAL_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.SECTION_HOSPITAL_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-status")
	public ResponseEntity<?> changeStatus(Long id , int status,ServletRequest request)
	{
		
		SectionHospital response = sectionHospitalService.chnageStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-sectionHospital-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-by-Id")
	public ResponseEntity<?> viewById(Long id)
	{
		SectionHospital response = sectionHospitalService.viewById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/get-list")
	public ResponseEntity<?> getList(int status)
	{
		List<SectionHospital> response = sectionHospitalService.getList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	


}
