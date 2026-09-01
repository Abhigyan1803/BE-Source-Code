package com.example.demo.service;

import java.util.List;

import com.example.demo.model.SessionByYear;
import com.example.demo.model.SessionWeek;
import com.example.demo.model.TermSeason;

public interface AdminSessionService {

	SessionByYear addSessionYear(SessionByYear sessionYear);
	
	List<SessionWeek> getSessionWeeks(Long termSeason);

	SessionWeek getWeeksByYear(Long seasonYear, String year, String week);

	List<SessionByYear> sessionYearList(int status);

	//SessionByYear getSessionTermAndYear(TermSeason termSeason, String sessionYear);
}
