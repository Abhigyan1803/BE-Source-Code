package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AssaigmentOfDuties;
import com.example.demo.service.AssaigmentOfDutiesService;

@RestController
@CrossOrigin
@RequestMapping("/assaigment_of_duties")
public class AssaigmentOfDutiesController {

	@Autowired
	AssaigmentOfDutiesService dutiesService;

	@PostMapping("/addAssaigmentDuties")
	public Map<Object, Object> addAssaigmentDuties(AssaigmentOfDuties details, MultipartFile doc,
			ServletRequest request) {
		return dutiesService.addDuties(details, doc, request);
	}

	@PostMapping("/updateAssaigmentDuties")
	public Map<Object, Object> updateAssaigmentDuties(AssaigmentOfDuties details, MultipartFile doc,
			ServletRequest request) {
		return dutiesService.updateDuties(details, doc, request);
	}

	@GetMapping("/getAll")
	public Map<Object, Object> getAllAssaigment(int battalionId , int status) {
		return dutiesService.getAllDuties(battalionId , status);
	}

	@PostMapping("/activeDeactiveStatus")
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request) {
		return dutiesService.activeDeactiveStatus(id, status, request);
	}

	@PostMapping("/viewDetailsById")
	public Map<Object, Object> viewDetailsById(Long id) {
		return dutiesService.viewDetailsById(id);
	}

}
