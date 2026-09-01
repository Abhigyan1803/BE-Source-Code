package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import com.example.demo.payload.AddDailyProgramPayLoad;
import com.example.demo.payload.UpdateDailyProgramPayLoad;

public interface DailyProgrameService {

	Map<Object, Object> addDailyPrograme(AddDailyProgramPayLoad request, ServletRequest servletRequest);

	Map<Object, Object> getAllDailyPrograme();

	Map<Object, Object> getProgrameByDate(Long date);

	Map<Object, Object> updateDailyPrograme(UpdateDailyProgramPayLoad request, ServletRequest servletRequest);

	Map<Object, Object> getTodaysProgramme();

	Map<Object, Object> viewProgrammeById(Long id);

}
