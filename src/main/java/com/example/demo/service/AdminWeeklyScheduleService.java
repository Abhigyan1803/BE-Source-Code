package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Schedule;
import com.example.demo.model.WeeklySchedule;
import com.example.demo.model.WeeklyScheduleDate;
import com.example.demo.payload.WeeklyFilter;
import com.example.demo.payload.WeeklyFilter2;

public interface AdminWeeklyScheduleService {

	WeeklySchedule createSchedule(WeeklySchedule weeklySchedule);

	List<WeeklySchedule> getAllWeeklyScheduleList();

	WeeklySchedule getScheduleById(Integer id);

	WeeklySchedule updateWeeklySchedule(WeeklySchedule weeklySchedule);

	List<Schedule> getScheduleList();

	WeeklySchedule getWeeklyScheduleByWeek(Long id);

	List<WeeklySchedule> getWeeklyScheduleDateByDate(WeeklyFilter filters);

	List<WeeklySchedule> getCurrentWeekSchedule(WeeklyFilter filters);

	WeeklyScheduleDate getWeeklyScheduleDateByDate1(WeeklyFilter filters);

	WeeklyScheduleDate getWeeklyScheduleDateByDate2(WeeklyFilter2 filter2);

}
