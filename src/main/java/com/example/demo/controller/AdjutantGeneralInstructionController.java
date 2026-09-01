package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.POPAdjutantBranch;
import com.example.demo.model.ReceptionAdjutantBranch;
import com.example.demo.model.SOPAdjutantBranch;
import com.example.demo.model.ScheduleDrillCompetition;
import com.example.demo.service.AdjutantGeneralInstructionService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/adjutant_general_instruction")
public class AdjutantGeneralInstructionController {
	
	@Autowired
	AdjutantGeneralInstructionService adjutantGeneralInstructionService;
	
	@PostMapping("/add-reception")
	public ResponseEntity<?> addReceptionDetails(ReceptionAdjutantBranch details , MultipartFile file ,ServletRequest request)
	{
		ReceptionAdjutantBranch response = adjutantGeneralInstructionService.addReception(details, file);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-reception-adjutant,"
				+ ConstantMessage.RECEPTION_GENERAL_INSTRUCTION_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECEPTION_GENERAL_INSTRUCTION_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-reception")
	public ResponseEntity<?> updateReceptionDetails(ReceptionAdjutantBranch details , MultipartFile file ,ServletRequest request)
	{
		ReceptionAdjutantBranch response = adjutantGeneralInstructionService.updateReception(details, file);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",update," + "update-reception-adjutant,"
				+ ConstantMessage.RECEPTION_GENERAL_INSTRUCTION_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECEPTION_GENERAL_INSTRUCTION_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-reception-status")
	public ResponseEntity<?> changeReceptionDetailsStatus(Long id , int status ,ServletRequest request)
	{
		ReceptionAdjutantBranch response = adjutantGeneralInstructionService.changeReceptionStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",change_status," + "change-reception-adjutant-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-reception")
	public ResponseEntity<?> viewReceptionById(Long id)
	{
		ReceptionAdjutantBranch response = adjutantGeneralInstructionService.viewReceptionById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/get-reception-list")
	public ResponseEntity<?> getReceptionList(int status)
	{
		List<ReceptionAdjutantBranch> response = adjutantGeneralInstructionService.getReceptionList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	
	//========================================POP=================================================
	
	@PostMapping("/add-pop")
	public ResponseEntity<?> addPOPDetails(POPAdjutantBranch details , MultipartFile file ,ServletRequest request)
	{
		POPAdjutantBranch response = adjutantGeneralInstructionService.addPOP(details, file);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-pop-adjutantDetails,"
				+ ConstantMessage.POP_GENERAL_INSTRUCTION_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.POP_GENERAL_INSTRUCTION_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-pop")
	public ResponseEntity<?> updatePOPDetails(POPAdjutantBranch details , MultipartFile file ,ServletRequest request)
	{
		POPAdjutantBranch response = adjutantGeneralInstructionService.updatePOP(details, file);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",update," + "update-pop-adjutantDetails,"
				+ ConstantMessage.POP_GENERAL_INSTRUCTION_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.POP_GENERAL_INSTRUCTION_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-pop-status")
	public ResponseEntity<?> changePOPDetailsStatus(Long id , int status ,ServletRequest request)
	{
		POPAdjutantBranch response = adjutantGeneralInstructionService.changePOPStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",change_status," + "change-pop-adjutant-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-pop")
	public ResponseEntity<?> viewPOPById(Long id)
	{
		POPAdjutantBranch response = adjutantGeneralInstructionService.viewPOPById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/get-pop-list")
	public ResponseEntity<?> getPOPList(int status)
	{
		List<POPAdjutantBranch> response = adjutantGeneralInstructionService.getPOPList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	//=================================SOP========================================================
	@PostMapping("/add-sop")
	public ResponseEntity<?> addSOPDetails(SOPAdjutantBranch details , MultipartFile file ,ServletRequest request)
	{
		SOPAdjutantBranch response = adjutantGeneralInstructionService.addSOP(details, file);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-sop-adjutantDetails,"
				+ ConstantMessage.SOP_GENERAL_INSTRUCTION_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.SOP_GENERAL_INSTRUCTION_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-sop")
	public ResponseEntity<?> updateSOPDetails(SOPAdjutantBranch details , MultipartFile file ,ServletRequest request)
	{
		SOPAdjutantBranch response = adjutantGeneralInstructionService.updateSOP(details, file);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",update," + "update-sop-adjutantDetails,"
				+ ConstantMessage.SOP_GENERAL_INSTRUCTION_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.SOP_GENERAL_INSTRUCTION_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-sop-status")
	public ResponseEntity<?> changeSOPDetailsStatus(Long id , int status ,ServletRequest request)
	{
		SOPAdjutantBranch response = adjutantGeneralInstructionService.changeSOPStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",change_status," + "change-sop-adjutant-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-sop")
	public ResponseEntity<?> viewSOPById(Long id)
	{
		POPAdjutantBranch response = adjutantGeneralInstructionService.viewPOPById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/get-sop-list")
	public ResponseEntity<?> getSOPList(int status)
	{
		List<SOPAdjutantBranch> response = adjutantGeneralInstructionService.getSOPList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	//========================Schedule Drill Competition General Instruction =========================
	@PostMapping("/add-schedule")
	public ResponseEntity<?> addScheduleDetails(ScheduleDrillCompetition details , MultipartFile file ,ServletRequest request)
	{
		ScheduleDrillCompetition response = adjutantGeneralInstructionService.addSchedule(details, file);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-schedule-drillCompetition,"
				+ ConstantMessage.SCHEDULE_DRILL_COMPETITION_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.SCHEDULE_DRILL_COMPETITION_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-schedule")
	public ResponseEntity<?> updateScheduleDetails(ScheduleDrillCompetition details , MultipartFile file ,ServletRequest request)
	{
		ScheduleDrillCompetition response = adjutantGeneralInstructionService.updateSchedule(details, file);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",update," + "update-schedule-drillCompetition,"
				+ ConstantMessage.SCHEDULE_DRILL_COMPETITION_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.SCHEDULE_DRILL_COMPETITION_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-schedule-status")
	public ResponseEntity<?> changeScheduleDetailsStatus(Long id , int status ,ServletRequest request)
	{
		ScheduleDrillCompetition response = adjutantGeneralInstructionService.changeScheduleStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",change_status," + "change-schedule-drillCompetition-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-schedule")
	public ResponseEntity<?> viewScheduleById(Long id)
	{
		ScheduleDrillCompetition response = adjutantGeneralInstructionService.viewScheduleById(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/get-schedule-list")
	public ResponseEntity<?> getScheduleList(int status)
	{
		List<ScheduleDrillCompetition> response = adjutantGeneralInstructionService.getScheduleList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	//////////////////////////New Added
	@GetMapping("/get-schedule-by-type-status")
	public ResponseEntity<?> getScheduleByTypeAndStatus(@RequestParam String type,@RequestParam int status)
	{
		List<ScheduleDrillCompetition> response = adjutantGeneralInstructionService.getScheduleByTypeAndStatus(type,status);
		if(response!=null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, null),
				HttpStatus.OK);
	}
}
