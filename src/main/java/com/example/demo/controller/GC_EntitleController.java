package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.GC_Entitle;
import com.example.demo.service.GC_EntitleService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/entitle")
public class GC_EntitleController {

	@Autowired
	GC_EntitleService entitleService;

	// @RequestMapping("/get-all-entitle-by-type/{type}")
	@GetMapping("/get-all-entitle-by-type_cadetId")
	public ResponseEntity<?> getEntitle(@RequestParam String type, @RequestParam(required = false) Long cadetId) {

		List<GC_Entitle> response = entitleService.getGC_Entitle(type, cadetId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PostMapping("/add-entitle")
	public ResponseEntity<?> saveGC_Entitle(@RequestBody GC_Entitle entitle) {
		GC_Entitle response = entitleService.saveGC_Entitle(entitle);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}
}
