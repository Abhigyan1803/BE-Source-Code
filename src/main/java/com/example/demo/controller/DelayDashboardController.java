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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DelayDashboard;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.DelayDashboardPayLoad;
import com.example.demo.service.DelayDashboardService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/delay-dashboard-controller")
@CrossOrigin
public class DelayDashboardController {

	@Autowired
	private DelayDashboardService delayDashboardService;

	@PostMapping("/add_delay_dashboard")
	public ResponseEntity<?> addDelayDashboard(@RequestBody DelayDashboard delayDashboard) {
		DelayDashboard response = delayDashboardService.addDelayDashboard(delayDashboard);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@PutMapping(value = "/update-delay_dashboard")
	public ResponseEntity<?> updateDelayDashboard(@RequestBody DelayDashboard delayDashboard) throws MyException {
		DelayDashboard response = delayDashboardService.updateDelayDashboard(delayDashboard);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_delay_dashboard_staff")
	public ResponseEntity<?> getDelayDashboardStaff(@RequestParam Long moduleId, @RequestParam Long termId)
			throws MyException {
		List<DelayDashboardPayLoad> response = delayDashboardService.getDelayDashboardStaff(moduleId, termId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}
}
