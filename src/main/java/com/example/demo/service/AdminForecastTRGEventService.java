package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ForecastTRGEvent;

public interface AdminForecastTRGEventService {

	ForecastTRGEvent createForecast(ForecastTRGEvent forcast);

	List<ForecastTRGEvent> getAllForecastList(int status, Boolean isGcLec);

	ForecastTRGEvent getForecastById(Long id);

	ForecastTRGEvent updateForecast(ForecastTRGEvent forecast);

}
