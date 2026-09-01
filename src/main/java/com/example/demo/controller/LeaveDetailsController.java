package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.HomeAboutEntries;
import com.example.demo.model.LeaveDetails;
import com.example.demo.myexception.MyException;
import com.example.demo.service.LeaveDetailsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/leave_details_controller")
public class LeaveDetailsController {
	
	@Autowired
	LeaveDetailsService leaveService;
	
	@PostMapping(value = "/add_leave_details")
	public ResponseEntity<?> addLeaveDetails(@RequestBody LeaveDetails leaveDetails) throws MyException {
		
		LeaveDetails response = leaveService.addLeaveDetails(leaveDetails);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping("/get_leave_details_list")
	public ResponseEntity<?> getLeaveDetailsList() {
		List<LeaveDetails> response = leaveService.getLeaveDetailsList();
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@GetMapping("/get_leave_details_by_id")
	public ResponseEntity<?> getLeaveDetailsById(Long id) {
		LeaveDetails response = leaveService.getLeaveDetailsById(id);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PutMapping("/update_leave_details")
	public ResponseEntity<?> updateAboutEntry(@RequestBody LeaveDetails leaveDetails) {
		LeaveDetails response = leaveService.updateLeaveDetails(leaveDetails);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/change_status")
	public ResponseEntity<?> leaveDetailsChangeStatus(Long id , Integer status) throws MyException {
		
		LeaveDetails response = leaveService.leaveDetailsChangeStatus(id,status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}
}
