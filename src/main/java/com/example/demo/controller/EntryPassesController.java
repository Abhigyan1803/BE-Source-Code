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
import com.example.demo.model.CasualStaffEntryPasses;
import com.example.demo.model.CombatEntryPasses;
import com.example.demo.model.DefEntryPasses;
import com.example.demo.service.EntryPassesService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RequestMapping("/api/entryPasses")
@RestController
@CrossOrigin
public class EntryPassesController {
	
	
	@Autowired
	EntryPassesService entryPassesService;
	
	
	@PostMapping("/add-combat")
	public ResponseEntity<?> addCombatDetails(CombatEntryPasses details , MultipartFile docfile , ServletRequest request)
	{
		
		CombatEntryPasses response = entryPassesService.addCombatDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-combat,"
				+ ConstantMessage.COMBAT_ENTRY_PASS_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.COMBAT_ENTRY_PASS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-combat")
	public ResponseEntity<?> updateCombatDetails(CombatEntryPasses details , MultipartFile docfile , ServletRequest request)
	{
		
		CombatEntryPasses response = entryPassesService.updateCombatDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-combat,"
				+ ConstantMessage.COMBAT_ENTRY_PASS_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.COMBAT_ENTRY_PASS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-combat-status")
	public ResponseEntity<?> changeCombatStatus(Long id , int status,ServletRequest request)
	{
		
		CombatEntryPasses response = entryPassesService.changeCombatStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-combat-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-combat")
	public ResponseEntity<?> viewCombatById(Long id)
	{
		CombatEntryPasses response = entryPassesService.viewCombatById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-combat-list")
	public ResponseEntity<?> getCombatList(int status)
	{
		List<CombatEntryPasses> response = entryPassesService.getCombatList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/add-def")
	public ResponseEntity<?> adddefDetails(DefEntryPasses details , MultipartFile docfile , ServletRequest request)
	{
		
		DefEntryPasses response = entryPassesService.addDefDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-def,"
				+ ConstantMessage.DEF_ENTRY_PASS_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.DEF_ENTRY_PASS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-def")
	public ResponseEntity<?> updateDefDetails(DefEntryPasses details , MultipartFile docfile , ServletRequest request)
	{
		
		DefEntryPasses response = entryPassesService.updateDefDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-def,"
				+ ConstantMessage.DEF_ENTRY_PASS_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.DEF_ENTRY_PASS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-def-status")
	public ResponseEntity<?> changeDefStatus(Long id , int status,ServletRequest request)
	{
		
		DefEntryPasses response = entryPassesService.changeDefStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-def-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-def")
	public ResponseEntity<?> viewDefById(Long id)
	{
		DefEntryPasses response = entryPassesService.viewDefById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-def-list")
	public ResponseEntity<?> getDefList(int status)
	{
		List<DefEntryPasses> response = entryPassesService.getDefList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/add-casual")
	public ResponseEntity<?> addCasualDetails(CasualStaffEntryPasses details , MultipartFile docfile , ServletRequest request)
	{
		
		CasualStaffEntryPasses response = entryPassesService.addCasualStaffDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-casual,"
				+ ConstantMessage.CASUAL_ENTRY_PASS_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.CASUAL_ENTRY_PASS_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-casual")
	public ResponseEntity<?> updateCasualDetails(CasualStaffEntryPasses details , MultipartFile docfile , ServletRequest request)
	{
		
		CasualStaffEntryPasses response = entryPassesService.updateCasualStaffDetails(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-casual,"
				+ ConstantMessage.CASUAL_ENTRY_PASS_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.CASUAL_ENTRY_PASS_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-casual-status")
	public ResponseEntity<?> changeCasualStatus(Long id , int status,ServletRequest request)
	{
		
		CasualStaffEntryPasses response = entryPassesService.changeCasualStaffStatus(status, id);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-casual-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-casual")
	public ResponseEntity<?> viewCasualById(Long id)
	{
		CasualStaffEntryPasses response = entryPassesService.viewCasualStaffById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-casual-list")
	public ResponseEntity<?> getCasualStaffList(int status)
	{
		List<CasualStaffEntryPasses> response = entryPassesService.getCasualStaffList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	

}
