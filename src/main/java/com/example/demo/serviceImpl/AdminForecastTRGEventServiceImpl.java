package com.example.demo.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ForecastTRGEvent;
import com.example.demo.repository.AdminForecastTRGEventRepo;
import com.example.demo.service.AdminForecastTRGEventService;

@Service
public class AdminForecastTRGEventServiceImpl implements AdminForecastTRGEventService {

	@Autowired
	AdminForecastTRGEventRepo forecastRepo;

	@Override
	public ForecastTRGEvent createForecast(ForecastTRGEvent forcast) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(forcast.getDate());
		LocalDate currentDate = LocalDate.parse(strDate);
		int ordinal = currentDate.getDayOfWeek().getValue() + 1;
		String day = StringUtils.EMPTY;
		switch (ordinal) {
		case 1:
			day = "Sunday";
			break;
		case 2:
			day = "Monday";
			break;
		case 3:
			day = "Tuesday";
			break;
		case 4:
			day = "Wednesday";
			break;
		case 5:
			day = "Thursday";
			break;
		case 6:
			day = "Friday";
			break;
		case 7:
			day = "Saturday";
		}

		forcast.setDay(day);
		return forecastRepo.save(forcast);
	}

	@Override
	public List<ForecastTRGEvent> getAllForecastList(int status, Boolean isGcLec) {
		List<ForecastTRGEvent> list = new ArrayList<>();
		if (status == 0 || status == 1) {
			if (isGcLec == true) {
				list = forecastRepo.findByStatusAndIsGcLecOrderByIdDesc(status, isGcLec);
			} else {
				list = forecastRepo.findByStatusOrderByIdDesc(status);
			}

		} else {
			if (isGcLec == true) {
				list = forecastRepo.findAllByIsGcLecOrderByIdDesc(isGcLec);
			} else {
				list = forecastRepo.findAllByOrderByIdDesc();
			}

		}

		return list;
	}

	@Override
	public ForecastTRGEvent getForecastById(Long id) {
		Optional<ForecastTRGEvent> list = forecastRepo.findById(id);
		return list.get();
	}

	@Override
	public ForecastTRGEvent updateForecast(ForecastTRGEvent forecast) {
		ForecastTRGEvent fc = null;
		Optional<ForecastTRGEvent> f = forecastRepo.findById(forecast.getId());

		if (f.isPresent()) {

			fc = f.get();

			if (StringUtils.isNotBlank(forecast.getLocationImage())) {
				fc.setLocationImage(forecast.getLocationImage());
			}

			if (forecast.getSessionTerm() != null) {

				fc.setSessionTerm(forecast.getSessionTerm());
			}

			if (forecast.getYear() != null) {

				fc.setYear(forecast.getYear());
			}

			if (forecast.getWeek() != null) {

				fc.setWeek(forecast.getWeek());
			}
			if (forecast.getDescription() != null) {

				fc.setDescription(forecast.getDescription());
			}

			if (forecast.getStatus() != null) {

				fc.setStatus(forecast.getStatus());
			}
			if (forecast.getIsGcLec() != null) {
				fc.setIsGcLec(forecast.getIsGcLec());
			}

			fc.setUpdatedAt(new Date());

			if (forecast.getDate() != null) {

				fc.setDate(forecast.getDate());

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = dateFormat.format(forecast.getDate());
				LocalDate currentDate = LocalDate.parse(strDate);
				int ordinal = currentDate.getDayOfWeek().getValue() + 1;
				String day = StringUtils.EMPTY;
				switch (ordinal) {
				case 1:
					day = "Sunday";
					break;
				case 2:
					day = "Monday";
					break;
				case 3:
					day = "Tuesday";
					break;
				case 4:
					day = "Wednesday";
					break;
				case 5:
					day = "Thursday";
					break;
				case 6:
					day = "Friday";
					break;
				case 7:
					day = "Saturday";
				}

				fc.setDay(day);
			}
		}

		ForecastTRGEvent list = forecastRepo.save(fc);
		return list;
	}

}
