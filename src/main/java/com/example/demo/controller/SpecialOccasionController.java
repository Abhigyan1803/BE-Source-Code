package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SpecialOccasion;
import com.example.demo.repository.SpecialOccasionRepo;
import com.example.demo.service.SpecialOccasionService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/special-occasion")
public class SpecialOccasionController {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	@Autowired
	SpecialOccasionService soService;
	
	@Autowired
	SpecialOccasionRepo specialOccasionRepo;


	@PostMapping(value = "/add-occasion")
	private ResponseEntity<?> addOccasion(@RequestBody SpecialOccasion occasion, ServletRequest request) {
		
		SpecialOccasion icNumber=specialOccasionRepo.findByIcNumber(occasion.getIcNumber());
		if(icNumber!=null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.IC_NUMBER_ALREADY_EXIST, HttpStatus.OK,icNumber.getIcNumber()),
					HttpStatus.OK);
					
			}
		SpecialOccasion response = soService.addOccasion(occasion);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "special-occasion," + ConstantMessage.OCCASION_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OCCASION_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
		}

	@GetMapping(value = "/get-all-occasion")
	private ResponseEntity<?> getOccasionList(@RequestParam Integer status) {
		List<SpecialOccasion> list = soService.getAllOccasiomList(status);

		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-occasion")
	private ResponseEntity<?> getOccasionById(@RequestParam Long id) {
		SpecialOccasion list = soService.getOccasionById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-occasion")
	private ResponseEntity<?> updateOccasion(@RequestBody SpecialOccasion occasion, ServletRequest request) {
		SpecialOccasion response = soService.updateOccasion(occasion);
		if(response==null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE_IC_NUMBER, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "special-occasion,"
				+ ConstantMessage.OCCASION_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OCCASION_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-week-occasions")
	private ResponseEntity<?> getWeekOccasion() {
		List<SpecialOccasion> list = soService.getWeekOccasion();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

}
