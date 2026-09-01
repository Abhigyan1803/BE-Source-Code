package com.example.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static String convertTimeStampToDate(long timeStamp) {
		if (timeStamp != 0L) {
			Date currentDate = new Date(timeStamp);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
			String newDate = df.format(currentDate);
			return newDate;
		} else {
			return "";
		}

	}
	
	public static Date convertStringToDate(String stringDate) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy hh:mm a").parse(stringDate);
		return date;
		

	}
	
	public static long todaysDate() throws ParseException
	{
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String todaysdate = dateFormat.format(date);
        // System.out.println("Today's date : " + todaysdate);
       
         //converting the date into long 
         date = dateFormat.parse(todaysdate);
         long milliseconds = date.getTime();
         System.out.println("date in millioseconds : "+milliseconds);
         return milliseconds;
	}
	
	public static long nextDayDate() throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
		String nextdate = dateFormat.format(new Date().getTime()+ MILLIS_IN_DAY);//here date of today is fixed

        //System.out.println("next day date : "+nextdate);
        Date date = dateFormat.parse(nextdate);
        long milliseconds = date.getTime();
        //System.out.println("date in millioseconds : "+milliseconds);
        return milliseconds;
        
	}
	//adding a day to existing day
	public static long getNextDayDate(Long tdate) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
        String nextdate = dateFormat.format(tdate + MILLIS_IN_DAY);//here the user request date will be passed
        

        //System.out.println("next day date : "+nextdate);
        Date date = dateFormat.parse(nextdate);
        long milliseconds = date.getTime();
        //System.out.println("date in millioseconds : "+milliseconds);
        return milliseconds;
        
	}
	
	
}
