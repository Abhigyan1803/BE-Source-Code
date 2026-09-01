package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Week;
import com.example.demo.myexception.MyException;

public interface AdminWeekService {

	Week createWeek(Week week);

	List<Week> getAllScheduleList();

	Week getWeekById(Long id);

	Week updateSchedule(Week week);

}
