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
import com.example.demo.service.AdvCellSopsService;

@RestController
@RequestMapping("/api/advCellSopsController")
@CrossOrigin
public class AdvCellSopsController {

	@Autowired
	AdvCellSopsService advCellSopsService;

	@PostMapping("/addSopsDetails")
	public Map<Object, Object> addadvCellSops(
			@RequestParam(required = false, value = "document") MultipartFile document,
			GeneralInstructionReqPayload reqPayload, ServletRequest request) {
		return advCellSopsService.addAdvCellSops(reqPayload, document, request);
	}

	@PostMapping("/updateSops")
	public Map<Object, Object> updateAdvCellSops(
			@RequestParam(required = false, value = "document") MultipartFile document,
			UpdateGeneralInstructionPayload updatePayload, ServletRequest request) {
		return advCellSopsService.updateadvCellSops(updatePayload, document, request);
	}

	@GetMapping("/getAllSopsDetails")
	public Map<Object, Object> getAllSops() {
		return advCellSopsService.getAllAdvCellSops();
	}

	@PostMapping("/getSopsById")
	public Map<Object, Object> viewInstructionById(@RequestParam(value = "id") Long id) {
		return advCellSopsService.viewDetailsById(id);
	}

	@PostMapping("/activeDeActiveSops")
	public Map<Object, Object> activeDeactiveInstruction(@RequestParam(value = "id") Long id,
			@RequestParam(value = "status") int status, ServletRequest request) {
		return advCellSopsService.activeDeactiveStatus(id, status, request);
	}

	@PostMapping("/getSopsByStatus")
	public Map<Object, Object> getSopsByStatus(@RequestParam(value = "status") int status) {
		return advCellSopsService.getSopsByStatus(status);
	}
}