package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.GeneralInstructionReqPayload;
import com.example.demo.payload.UpdateGeneralInstructionPayload;
import com.example.demo.service.GeneralInstuctionService;

@RestController
@RequestMapping("/api/generalInstruction")
@CrossOrigin
public class AdminGeneralInstructionController {

	@Autowired
	GeneralInstuctionService generalService;

	@PostMapping("/addInstruction")
	public Map<Object, Object> addGeneralInstruction(
			@RequestParam(required = false, value = "document") MultipartFile document,
			GeneralInstructionReqPayload reqPayload, ServletRequest request) {
		return generalService.addGeneralInsturction(reqPayload, document, request);
	}

	@PostMapping("/updateInstruction")
	public Map<Object, Object> updateGeneralInstruction(
			@RequestParam(required = false, value = "document") MultipartFile document,
			UpdateGeneralInstructionPayload updatePayload, ServletRequest request) {
		return generalService.updateGeneralInstruction(updatePayload, document, request);
	}

	@GetMapping("/getAllInstructions")
	public Map<Object, Object> getAllInstructions() {
		return generalService.getAllInstruction();
	}

	@PostMapping("/viewInstructionById")
	public Map<Object, Object> viewInstructionById(@RequestParam(value = "id") Long id) {
		return generalService.viewDetailsById(id);
	}

	@PostMapping("/activeDeactiveInstruction")
	public Map<Object, Object> activeDeactiveInstruction(@RequestParam(value = "id") Long id,
			@RequestParam(value = "status") int status, ServletRequest request) {
		return generalService.activeDeactiveStatus(id, status, request);
	}

	@PostMapping("/getInstructionByStatus")
	public Map<Object, Object> getInstructionByStatus(@RequestParam(value = "status") int status) {
		return generalService.getInstructionsByStatus(status);
	}
}
