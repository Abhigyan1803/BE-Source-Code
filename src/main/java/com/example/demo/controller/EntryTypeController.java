package com.example.demo.controller;

import java.util.List;

import javax.servlet.ServletRequest;

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

import com.example.demo.model.EntryType;
import com.example.demo.service.EntryTypeService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/entryTypeController")
public class EntryTypeController {

	@Autowired
	EntryTypeService entryTypeService;

	@GetMapping(value = "/getAllEntryTypeList")
	public ResponseEntity<?> entryTypeList() {
		List<EntryType> list = entryTypeService.getAllEntryTypeList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getAllEntryTypeListByStatus")
	public ResponseEntity<?> entryTypeListByStatus(@RequestParam Integer status) {
		List<EntryType> list = entryTypeService.getAllEntryTypeListByStatus(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PostMapping(value = "/add_entry_type")
	public ResponseEntity<?> addEntryType(@RequestBody EntryType entryType, ServletRequest request) {
		EntryType entryTypeExist = entryTypeService.isAlreadyExist(entryType);
		if (entryTypeExist == null) {
			EntryType response = entryTypeService.createEntryType(entryType);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ENTRY_TYPE_ADDED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.ENTRY_TYPE_ALREADY_EXIST, HttpStatus.OK, null), HttpStatus.OK);
		}

	}

	@PostMapping(value = "/update_entry_type")
	public ResponseEntity<?> updateEntryType(@RequestBody EntryType entryType, ServletRequest request) {
		EntryType entryTypeExist = entryTypeService.validateEntryTypeExist(entryType);
		if (entryTypeExist == null) {
			EntryType response = entryTypeService.updateEntryType(entryType);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.ENTRY_TYPE_UPDATED, HttpStatus.OK, response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.ENTRY_TYPE_ALREADY_EXIST, HttpStatus.OK, null), HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_entry_type_by_id")
	public ResponseEntity<?> getEntryTypeById(@RequestParam Long id) {
		EntryType response = entryTypeService.getEntryTypeById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
