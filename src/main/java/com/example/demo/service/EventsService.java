package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.UpcomingEventPayload;
import com.example.demo.payload.UpdateUpcomingEventsPayLoad;

public interface EventsService {

	Map<Object, Object> addUpcomingEvents(UpcomingEventPayload eventPayload, MultipartFile file,
			ServletRequest servletRequest);

	Map<Object, Object> getAllUpcomingEvents(Boolean isGcEvent);

	Map<Object, Object> ActiveDeactiveEvents(Long id, int status, ServletRequest servletRequest);

	Map<Object, Object> getEventsByStatus(int status);

	Map<Object, Object> updateUpcomingEvents(UpdateUpcomingEventsPayLoad request, MultipartFile image,
			ServletRequest servletRequest);

	//Map<Object, Object> UpcomingEvents();

	Map<Object, Object> viewEventsById(Long id);

	Map<Object, Object> getEventsByDate(Long startDate, Long endDate);
        Map<Object, Object> UpcomingEvents(Boolean isGcEvent);

}
