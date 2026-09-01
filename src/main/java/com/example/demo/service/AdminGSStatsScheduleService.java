package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GSStatsSchedule;

public interface AdminGSStatsScheduleService {

	GSStatsSchedule createGsSchedule(GSStatsSchedule schedule);

	List<GSStatsSchedule> getAllGsScheduleList(Integer status);

	GSStatsSchedule getGsScheduleById(Integer id);

	GSStatsSchedule updateGsSchedule(GSStatsSchedule schedule);

}
