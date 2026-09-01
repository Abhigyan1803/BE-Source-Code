package com.example.demo.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.example.demo.serviceImpl.JwtUserDetailsService;
import com.example.demo.util.ConstantVar;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component // 2 may added /user/login
@WebFilter(urlPatterns = { "/hello", "/user/login", "/admin/login", "/admin/addAdmin", "/user/add-user",
		"/api/dailyPrgmController/getTodaysPrograme", "/api/eventsController/upcomingEvents",
		"/api/greyBookController/getGreyBookRecords", "/api/messageCommandantController/getMessagesByStatus",
		"/api/imaActivityController/getActivityByStatus", "/adjutant/getByAdjutantBranch",
		"/api/syllabus/term/get-syllabus-term-list", "/api/syllabus/get-syllabus-list",
		"/api/forecast/get-forecast-list", "/api/battalion/get-battalion-list",
		"/api/performance-highlights/get-performance-highlights-list", "/api/gc-activities/get-gc-activities-list",
		"/api/assaigment_of_duties/getAll", "/api/week/get-week-list", "/api/term/getAllTerms",
		"/termSeason/getSeasonTerm", "/api/special-occasion/get-week-occasions",
		"/api/commandant-recommended-reading-list/get-recommended-book-list",
		"/api/trg-calendar/get-weekly-program-week", "/api/trg-calendar/get-weekly-schedule-date",
		"/api/trg-calendar/get-current-week-schedule", "/api/OrganizationChartController/active-positions",
		"/api/gsController/get-gsOrg-list", "/api/battalion/get-organization-list",
		"/api/trg-calendar/get-current-week-schedule", "/api/hall-of-fame/get-officers-fame-list",
		"/api/hall-of-fame/get-param-veer-fame-list", "/api/blog/get-blog-list-by-category", "/api/blog/get-blog-list",
		"/api/announcement/get-announcement-list", "/api/section-hospital/get-list", "/api/gc_board/get-list",
		"/api/special-occasion/get-all-occasion", "/api/blog/get-blog",
		"/api/exercisesController/getAllExerciseTypeList", "/api/entryTypeController/getAllEntryTypeList",
		"/AllUser/login"})
public class JwtRequestFilter implements Filter {

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		String header = ((HttpServletRequest) request).getHeader(ConstantVar.HEADER_STRING);
		String username = null;
		String authToken = null;
		if (header != null && header.startsWith(ConstantVar.TOKEN_PREFIX)) {
			authToken = header.replace(ConstantVar.TOKEN_PREFIX, "");
			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("An error occurred while fetching Username from Token", e);
			} catch (ExpiredJwtException e) {
				logger.warn("The token has expired", e);
			} catch (SignatureException e) {
				logger.error("Authentication Failed. Username or Password not valid.");
			}
		} else {
			// logger.warn("Couldn't find bearer string, header will be ignored");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (userDetails != null && jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthenticationToken(authToken,
						SecurityContextHolder.getContext().getAuthentication(), userDetails);
				authentication
						.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);

	}

}
