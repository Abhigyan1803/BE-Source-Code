package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Weeks;

import com.example.demo.model.Week;

public class Demo {
	public static void main(String args[]) throws ParseException
	{
		
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-08");
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-08");

		long days = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
		int weeks = (int) (days / 7);
		Date tempDate = null;
		Calendar cal = Calendar.getInstance();    

		System.out.println(days / 7);
        long tempStartDate= 0l;
        
		for (int i = 1; i <= weeks; i++) {
			long weektime=startDate.getTime()+7*24*60*60*1000;
			Date today8=new Date(weektime);
			tempStartDate = today8.getTime()+1*24*60*60*1000;
//			sessionWeek.setWeek("Week "+i);
//			sessionWeek.setWeekStartDate(startDate);
//			sessionWeek.setWeekEndDate(today8);
			
			
			System.out.println("start day date : "+startDate);
			System.out.println("end day date : "+today8);
			startDate = new Date(tempStartDate);
		 }
			}
		
//		DateTime dateTime1 = new DateTime(2021-7-07); 
//		DateTime dateTime2 = new DateTime(2021-8-12); 
//		int weeks = Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
//		System.out.println(" total weeks  : "+weeks);
//		
//		DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String startDate = "2021-07-07";
//		String endDate = "2021-08-19";
//		LocalDate lds = LocalDate.parse(startDate, dTF);
//		LocalDate lds1 = LocalDate.parse(endDate, dTF);
//		
	
//		Date date1 = new Date(2021-7-7);
//		Date date2  = new Date(2021-8-12);
//		Calendar c1 = Calendar.getInstance();
//	    Calendar c2 = Calendar.getInstance();
//	    c1.setTime(f);
//	    c2.setTime(l);
//	    DateTime start = new DateTime(c1.YEAR, c1.MONTH, c1.DAY_OF_MONTH, 0, 0, 0, 0);
//	    DateTime end   = new DateTime(c2.YEAR, c2.MONTH, c2.DAY_OF_MONTH, 0, 0, 0, 0);
//	    Interval interval = new Interval(start, end);
//	    org.joda.time.Period p = interval.toPeriod();
//		 System.out.println("periods : "+p.getWeeks());
//		 
//		long weewks = Math.abs(ChronoUnit.WEEKS.between(f, l));
//		 System.out.println("number of weeks "+ weeks);

		
//		
//		LocalDate actualStartDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		LocalDate actualEndDate = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
//		LocalDate startDate = actualStartDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//		LocalDate endDate = actualEndDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
//		long daysBetweenTwoDates = ChronoUnit.DAYS.between(startDate, endDate);
//		int numberOfWeeks = (int) Math.ceil(daysBetweenTwoDates / 7.0);
//		System.out.println("NO : "+numberOfWeeks);
		
//		try {
//			Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-07");
//			Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-08");
//
//			long days = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
//			int weeks = (int) (days / 7);
//			System.out.println(days / 7);
//
//			for (int i = 1; i <= weeks; i++) {
//				
//			}
//		} catch (Exception ex) {
//			ex.toString();
//		}
		
  
	
//	try {
//			
//		
//		String start="2021-07-06";
//		String end= "2021-07-06";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDateTime dateTime = LocalDateTime.parse(start, formatter);
//		LocalDateTime dateTimeend = LocalDateTime.parse(end, formatter);
//		    
//		 //calDateTime endDate1 =
//
//		   System.out.println( ChronoUnit.WEEKS.between(dateTime, dateTimeend));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	
	
	
	
	
	
	
		
////		Duration duration = Duration.between(now, sixMinutesBehind);
////	    long diff = Math.abs(duration.toMinutes());
//		Date startdate = new Date(2021-7-06);
//		Date endDate =  new Date(2021-8-12);
//		
//		option3(startdate ,  endDate);
//		
//	}
//	public static int option1(Date start, Date end) {
//        Calendar cal = new GregorianCalendar();
//        cal.setTime(start);
//
//        int weeks = 0;
//        while (start.before(end)) {
//            cal.add(Calendar.WEEK_OF_YEAR, 1);
//            weeks++;
//            System.out.println("inside loop ");
//        }
//        System.out.println(" total weeks : "+weeks);
//        return weeks;
//    }
//	
//	public static int option3(Date start, Date end) {
//        Date firstOfWeek = Date.from(start.toInstant().atZone(ZoneId.of("Asia/Kolkata")).toLocalDate().with(
//                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
//        ).atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//        Date lastOfWeek = Date.from(end.toInstant().atZone(ZoneId.of("Asia/Kolkata")).toLocalDate().with(
//                TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)
//        ).atStartOfDay(ZoneId.systemDefault()).toInstant());
//        
//        System.out.println(" first week : "+firstOfWeek);
//        System.out.println("last week : "+lastOfWeek);
//        return option1(firstOfWeek, lastOfWeek);
//    }

}
