package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.UpcomingEventPayload;
import com.example.demo.payload.UpdateUpcomingEventsPayLoad;
import com.example.demo.service.EventsService;

@RestController
@RequestMapping("/api/eventsController")
@CrossOrigin
public class AdminEventsController {

	@Autowired
	EventsService eventsService;

	// @ApiOperation(value="Add upcoming events")
	@PostMapping("/addUpcomingEvents")
	public Map<Object, Object> addUpcomingEvents(@RequestParam(required = false, value = "image") MultipartFile image,
			UpcomingEventPayload eventsPayload, ServletRequest servletRequest) {
		return eventsService.addUpcomingEvents(eventsPayload, image, servletRequest);
	}

	// @ApiOperation(value="Get all upcoming events")
	@GetMapping("/getAllUpcomingEvents")
	public Map<Object, Object> getUpcomingEvents(@RequestParam Boolean isGcEvent) {
		return eventsService.getAllUpcomingEvents(isGcEvent);
	}

	// @ApiOperation(value="Get upcoming events by status")
	@PostMapping("/getEventsByStatus")
	public Map<Object, Object> getEventsByStatus(@RequestParam(value = "status") int status) {
		return eventsService.getEventsByStatus(status);
	}

	// @ApiOperation(value="Active deactive status : 1 for active and 0 for
	// deactive")
	@PostMapping("/activeDeactiveEvents")
	public Map<Object, Object> activeDeactiveStatus(@RequestParam(value = "status") int status,
			@RequestParam(value = "id") Long id, ServletRequest servletRequest) {
		return eventsService.ActiveDeactiveEvents(id, status, servletRequest);
	}

	// @ApiOperation(value="update events")
	@PostMapping("/updateEvents")
	public Map<Object, Object> updateEvents(@RequestParam(required = false, value = "image") MultipartFile image,
			UpdateUpcomingEventsPayLoad request, ServletRequest servletRequest) {

		return eventsService.updateUpcomingEvents(request, image, servletRequest);
	}

	@GetMapping("/upcomingEvents")
	public Map<Object, Object> upcomingEvents(@RequestParam Boolean isGcEvent) {
		return eventsService.UpcomingEvents(isGcEvent);
	}

	@PostMapping("/viewEventsById")
	public Map<Object, Object> viewById(Long id) {
		return eventsService.viewEventsById(id);
	}

	@PostMapping("/getEventsByDates")
	public Map<Object, Object> getEventsByDate(Long startDate, Long endDate) {
		return eventsService.getEventsByDate(startDate, endDate);
	}
}
