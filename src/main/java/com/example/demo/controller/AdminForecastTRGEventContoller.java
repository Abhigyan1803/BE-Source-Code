package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ForecastTRGEvent;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminForecastTRGEventService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/forecast")
public class AdminForecastTRGEventContoller {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminForecastTRGEventService forecastService;

	@PostMapping(value = "/add-forecast")
	public ResponseEntity<?> addForecast(ForecastTRGEvent forcast, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam(value = "mapImage", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {

		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				forcast.setLocationImage(url + uploaded_doc);
			}
		}

		ForecastTRGEvent response = forecastService.createForecast(forcast);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "forecast," + ConstantMessage.FORECAST_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FORECAST_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-forecast-list")
	public ResponseEntity<?> getForecastList(int status, @RequestParam(required = false) Boolean isGcLec) {
		List<ForecastTRGEvent> list = forecastService.getAllForecastList(status, isGcLec);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-forecast")
	public ResponseEntity<?> getForecastByID(@RequestParam Long id) {
		ForecastTRGEvent list = forecastService.getForecastById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-forecast")
	public ResponseEntity<?> updateWeek(ForecastTRGEvent forecast, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam(value = "mapImage", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				forecast.setLocationImage(url + uploaded_doc);
			}
		}
		ForecastTRGEvent response = forecastService.updateForecast(forecast);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "forecast," + ConstantMessage.FORECAST_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FORECAST_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
	}
}
