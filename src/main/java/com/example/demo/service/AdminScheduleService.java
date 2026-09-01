package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Schedule;

public interface AdminScheduleService {

	Schedule createSchedule(Schedule schedule);

	Schedule getScheduleById(Integer id);

	Schedule updateSchedule(Schedule schedule);

	//Page<Schedule> getAllScheduleList(Pageable paginationData);
	
	List<Schedule> getAllScheduleList();

}
