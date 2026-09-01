package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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

import com.example.demo.model.Schedule;
import com.example.demo.model.WeeklySchedule;
import com.example.demo.model.WeeklyScheduleDate;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.WeeklyFilter;
import com.example.demo.payload.WeeklyFilter2;
import com.example.demo.service.AdminWeeklyScheduleService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/trg-calendar")
public class AdminWeeklyScheduleController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminWeeklyScheduleService weeklyScheduleService;

	// APi Change
	@PostMapping(value = "/add-weekly-program")
	public ResponseEntity<?> addWeeklyScheduale(@RequestBody WeeklySchedule weeklySchedule, ServletRequest request)
			throws MyException {
		WeeklySchedule response = weeklyScheduleService.createSchedule(weeklySchedule);
		// added 25July
		if (response != null) {
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "weekly-program,"
					+ ConstantMessage.WEEKLY_SCHEDULE_ADDED + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.WEEKLY_SCHEDULE_ADDED, HttpStatus.OK, response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.INTERNAL_SERVER, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get-weekly-program-list")
	public ResponseEntity<?> getWeeklyScheduleList() {
		List<WeeklySchedule> list = weeklyScheduleService.getAllWeeklyScheduleList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-weekly-program")
	public ResponseEntity<?> getWeeklyScheduleByID(@RequestParam Integer id) {
		WeeklySchedule list = weeklyScheduleService.getScheduleById(id);
		// added 25July
		if (list != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR, list),
					HttpStatus.OK);
		}
	}

	@PatchMapping(value = "/update-weekly-program")
	public ResponseEntity<?> updateWeeklySchedule(@RequestBody WeeklySchedule weeklySchedule, ServletRequest request)
			throws MyException {
		WeeklySchedule response = weeklyScheduleService.updateWeeklySchedule(weeklySchedule);
		// FileWritting.createLog((HttpServletRequest) request, response.getId() +
		// ",updated," + "weekly-program,"
		// + ConstantMessage.WEEKLY_SCHEDULE_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.WEEKLY_SCHEDULE_UPDATED, HttpStatus.OK, response), HttpStatus.OK);

	}

	@GetMapping(value = "/get-dailySchedule-list")
	public ResponseEntity<?> getDailyScheduleList() {
		List<Schedule> list = weeklyScheduleService.getScheduleList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-weekly-program-week")
	public ResponseEntity<?> getWeeklyScheduleByWeek(@RequestParam Long id) {
		WeeklySchedule weekScd = weeklyScheduleService.getWeeklyScheduleByWeek(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, weekScd),
				HttpStatus.OK);
	}

	// @GetMapping(value = "/get-weekly-schedule-date")
	// public ResponseEntity<?> getWeeklyScheduleDateByDate(
	// @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dt) {
	// List<WeeklyScheduleDate> weekScd =
	// weeklyScheduleService.getWeeklyScheduleDateByDate(dt);
	// return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE,
	// HttpStatus.OK, weekScd),
	// HttpStatus.OK);
	// }

	@PostMapping(value = "/get-weekly-schedule-date")
	public ResponseEntity<?> getWeeklyScheduleDateByDate(@RequestBody WeeklyFilter filters) {
		List<WeeklySchedule> weekScd = weeklyScheduleService.getWeeklyScheduleDateByDate(filters);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, weekScd),
				HttpStatus.OK);
	}

	@PostMapping(value = "/get-current-week-schedule")
	public ResponseEntity<?> getCurrentWeekSchedule(@RequestBody WeeklyFilter filters) {
		List<WeeklySchedule> weekScd = weeklyScheduleService.getCurrentWeekSchedule(filters);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, weekScd),
				HttpStatus.OK);
	}

	@PostMapping(value = "/get-week-schedule-date")
	public ResponseEntity<?> getWeekScheduleDateByDate(@RequestBody WeeklyFilter filters) {
		WeeklyScheduleDate weekScd = weeklyScheduleService.getWeeklyScheduleDateByDate1(filters);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, weekScd),
				HttpStatus.OK);
	}

	@PostMapping(value = "/get-week-schedule-by-date")
	public ResponseEntity<?> getWeeksScheduleDateByDate(@RequestBody WeeklyFilter2 filter2) {
		WeeklyScheduleDate weekScd = weeklyScheduleService.getWeeklyScheduleDateByDate2(filter2);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, weekScd),
				HttpStatus.OK);
	}

}
