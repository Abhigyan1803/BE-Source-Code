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

import com.example.demo.model.Letter;
import com.example.demo.service.LetterService;

@RestController
@CrossOrigin
@RequestMapping("/letter")
public class AdminLetterController {

	@Autowired
	LetterService letterService;

	@PostMapping("/addLetter")
	public Map<Object, Object> addLetter(@RequestParam(required = false, value = "doc") MultipartFile doc,
			Letter letter, ServletRequest request) {
		return letterService.addLetter(doc, letter, request);
	}

	@GetMapping("/getAllLetters")
	public Map<Object, Object> getAllLetters() {
		return letterService.getAllRecords();
	}

	@PostMapping("/updateLetter")
	public Map<Object, Object> update(@RequestParam(required = false, value = "doc") MultipartFile doc, Letter letter,
			ServletRequest request) {
		return letterService.updateRecord(doc, letter, request);
	}

	@PostMapping("/viewById")
	public Map<Object, Object> viewRecord(Long id) {
		return letterService.viewDetailsById(id);
	}

	@PostMapping("/activeDeactiveStatus")
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request) {
		return letterService.activeDeactiveStatus(id, status, request);
	}
}
