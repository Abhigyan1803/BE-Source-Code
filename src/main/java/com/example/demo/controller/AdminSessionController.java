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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.SessionByYear;
import com.example.demo.model.SessionWeek;
import com.example.demo.service.AdminSessionService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/sessionController")
@CrossOrigin
public class AdminSessionController {

	@Autowired
	AdminSessionService sessionService;
	
	@PostMapping("/addSessionYear")
	public ResponseEntity<?> addYearSession(@RequestBody SessionByYear sessionYear, ServletRequest request)
	{
//		SessionByYear response= sessionService.getSessionTermAndYear(sessionYear.getTermSeason(),sessionYear.getSessionYear());
//		if(response) {
//			
//		}
		SessionByYear response = sessionService.addSessionYear(sessionYear);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "addSessionYear," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/getWeeksBySeason")
	public ResponseEntity<?> getSessionWeeks(Long termSeason)
	{
		List<SessionWeek> response= sessionService.getSessionWeeks(termSeason);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/getByWeek")
	public ResponseEntity<?> getSessionByWeek(Long seasonYear , String year , String week)
	{
		SessionWeek response= sessionService.getWeeksByYear(seasonYear , year , week);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PostMapping("/get-sessionYear-list")
	public ResponseEntity<?> getSessionYearList(int status)
	{
		List<SessionByYear> response= sessionService.sessionYearList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
