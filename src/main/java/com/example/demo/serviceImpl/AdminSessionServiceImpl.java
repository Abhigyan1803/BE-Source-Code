package com.example.demo.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SessionByYear;
import com.example.demo.model.SessionWeek;
import com.example.demo.model.TermSeason;
import com.example.demo.repository.SessionByYearRepo;
import com.example.demo.repository.SessionWeekRepo;
import com.example.demo.service.AdminSessionService;

@Service
public class AdminSessionServiceImpl  implements AdminSessionService{
	
	@Autowired
	SessionByYearRepo sessionYearRepo;
	
	@Autowired
	SessionWeekRepo sessionWeekRepo;
	
	@Override
	public SessionByYear addSessionYear(SessionByYear sessionYear) {
		
		try {
		sessionYear.setCreatedAt(new Date());
		sessionYear.setUpdatedAt(new Date());
		
		SessionByYear savedYear = sessionYearRepo.save(sessionYear);
		
		
		
//		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-07");
//		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-08");
		
		Date startDate = sessionYear.getStartDate();
		Date endDate = sessionYear.getEndDate();

		long days = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
		int weeks = (int) (days / 7);
        long tempStartDate= 0l;
        
		for (int i = 1; i <= weeks; i++) {
			
			SessionWeek sessionWeek = new SessionWeek();
			sessionWeek.setCreatedAt(new Date());
			sessionWeek.setUpdatedAt(new Date());
			sessionWeek.setSessionYear(savedYear);
			
			long weektime=startDate.getTime()+6*24*60*60*1000;
			Date today8=new Date(weektime);
			tempStartDate = today8.getTime()+1*24*60*60*1000;
			sessionWeek.setWeek("Week "+i);
			sessionWeek.setWeekStartDate(startDate);
			sessionWeek.setWeekEndDate(today8);
			
			
			System.out.println("start day date : "+startDate);
			System.out.println("end day date : "+today8);
			startDate = new Date(tempStartDate);
			
			sessionWeekRepo.save(sessionWeek);
		 }
		return savedYear;
		}
		catch(Exception e)
		{
			e.toString();
			return null;
		}
	
	}

	@Override
	public List<SessionWeek> getSessionWeeks(Long termSeason) {
//		List<SessionWeek> weeks	= sessionWeekRepo.findAll();
		List<SessionWeek> weeks	= sessionWeekRepo.findBySessionYearId(termSeason);
		return weeks;
	}

	@Override
	public SessionWeek getWeeksByYear(Long seasonYear, String year, String week) {
		SessionByYear sessionYear = sessionYearRepo.findBySessionYearAndTermSeasonIdAndStatus(year,seasonYear,1);
		if(sessionYear != null)
		{
			SessionWeek list= sessionWeekRepo.findBySessionYearIdAndWeek(sessionYear.getId() , week);
			return list;
		}
		return null;
	}

	@Override
	public List<SessionByYear> sessionYearList(int status) {
		List<SessionByYear> list = sessionYearRepo.findAllByStatusOrderByIdDesc(status);
		return list;
	}
	
}
