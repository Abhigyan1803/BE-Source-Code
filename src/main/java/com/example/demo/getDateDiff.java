package com.example.demo;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class getDateDiff {
	public static void main(String[] args) {
		try {
			Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-07");
			Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-08");

			long days = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
			System.out.println(days / 7);

		} catch (Exception ex) {
			ex.toString();
		}
	}
}
