package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AdminScheduleService;

@RestController
@CrossOrigin
@RequestMapping("/api/trg-calendar")
public class AdminScheduleController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminScheduleService scheduleService;

//	@PostMapping(value = "/add-daily-program")
//	public ResponseEntity<?> addScheduale(@RequestBody Schedule schedule, ServletRequest request) throws MyException {
//		System.out.println(schedule.getWeek());
//		Schedule response = scheduleService.createSchedule(schedule);
//		FileWritting.createLog((HttpServletRequest) request,
//				response.getId() + ",added," + "daily-program," + ConstantMessage.SCHEDULE_ADDED + "," + new Date());
//		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SCHEDULE_ADDED, HttpStatus.OK, response),
//				HttpStatus.OK);
//
//	}
//
//	@GetMapping(value = "/get-daily-program-list")
//	public ResponseEntity<?> getScheduleList() {
////		List<Schedule> list = scheduleService.getAllScheduleList();
////		Page<Schedule> list = scheduleService.getAllScheduleList(FileUploader.paginationData(pageNo, pageSize));
//		List<Schedule> list = scheduleService.getAllScheduleList();
//		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
//				HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/get-daily-program")
//	public ResponseEntity<?> getScheduleByID(@RequestParam Integer id) {
//		Schedule list = scheduleService.getScheduleById(id);
//		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
//				HttpStatus.OK);
//	}
//
//	@PatchMapping(value = "/update-daily-program")
//	public ResponseEntity<?> updateSchedule(@RequestBody Schedule schedule, ServletRequest request) throws MyException {
//		Schedule response = scheduleService.updateSchedule(schedule);
//		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "daily-program,"
//				+ ConstantMessage.SCHEDULE_UPDATED + "," + new Date());
//		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SCHEDULE_UPDATED, HttpStatus.OK, response),
//				HttpStatus.OK);
//
//	}

}
