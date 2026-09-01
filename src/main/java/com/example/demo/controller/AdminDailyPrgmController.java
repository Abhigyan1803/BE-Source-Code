package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.AddDailyProgramPayLoad;
import com.example.demo.payload.UpdateDailyProgramPayLoad;
import com.example.demo.service.DailyProgrameService;

@RestController
@RequestMapping("/api/dailyPrgmController")
@CrossOrigin
public class AdminDailyPrgmController {

	@Autowired
	DailyProgrameService prgmService;

	// @ApiOperation(value= "Add daily programe")
	@PostMapping("/addPrograme")
	public Map<Object, Object> addDailyPrograme(@RequestBody AddDailyProgramPayLoad request,
			ServletRequest servletRequest) {
		return prgmService.addDailyPrograme(request, servletRequest);
	}

	// @ApiOperation(value="Get all daily programes")
	@PostMapping("/getAllPrograme")
	public Map<Object, Object> getAllPrograme() {
		return prgmService.getAllDailyPrograme();
	}

	// @ApiOperation(value="Get programs by date")
	@PostMapping("/getProgrameByDate")
	public Map<Object, Object> getProgrameByDate(@RequestParam(value = "date") Long date) {
		return prgmService.getProgrameByDate(date);
	}

	// @ApiOperation(value= "Edit daily programe")
	@PostMapping("/updatePrograme")
	public Map<Object, Object> updateProgram(@RequestBody UpdateDailyProgramPayLoad request,
			ServletRequest servletRequest) {

		return prgmService.updateDailyPrograme(request, servletRequest);
	}

	@GetMapping("/getTodaysPrograme")
	public Map<Object, Object> getTodaysPrograme() {
		return prgmService.getTodaysProgramme();
	}

	@PostMapping("/viewProgrameById")
	public Map<Object, Object> viewById(Long id) {
		return prgmService.viewProgrammeById(id);
	}

}
