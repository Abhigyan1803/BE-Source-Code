package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Week;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminWeekService;
import com.example.demo.serviceImpl.AdminWeekServiceImpl;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/week")
public class AdminWeekController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminWeekService weekService;
	
	private static Logger logger=LoggerFactory.getLogger(AdminWeekController.class);


	@PostMapping(value = "/add-week")
	public ResponseEntity<?> addScheduale(@RequestBody Week week, ServletRequest request) throws MyException {
		Week response = weekService.createWeek(week);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "week," + ConstantMessage.WEEK_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.WEEK_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-week-list")
	public ResponseEntity<?> getWeekList() throws MyException{
		logger.info("something went wrong in controller");
		List<Week> list = weekService.getAllScheduleList();
		logger.info("list get successfully");
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-week")
	public ResponseEntity<?> getWeekByID(@RequestParam Long id) {
		Week list = weekService.getWeekById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-week")
	public ResponseEntity<?> updateWeek(@RequestBody Week week, ServletRequest request) throws MyException {
		Week response = weekService.updateSchedule(week);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "week," + ConstantMessage.WEEK_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.WEEK_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
