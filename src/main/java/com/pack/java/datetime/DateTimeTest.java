package com.pack.java.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeTest {

	public static void main(String[] args) {
		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());
		System.out.println(LocalDateTime.now());

		// LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
		// LocalTime time = LocalTime.of(5, 15);
		// LocalDateTime dateTime = LocalDateTime.of(date, time)
		// .minusDays(1).minusHours(10).minusSeconds(30);
		// System.out.println(dateTime);

		LocalDate date = LocalDate.of(2015, 1, 20);
		LocalTime time = LocalTime.of(20, 15);
		LocalDateTime dateTime = LocalDateTime.of(date, time);

		System.out.println("Date b4 manipulation = " + date);
		System.out.println("DateTime b4 manipulation = " + dateTime);
		Period period = Period.ofMonths(1);
		System.out.println(date.plus(period)); // 2015-02-20
		System.out.println(dateTime.plus(period)); // 2015-02-20T06:15
		// System.out.println(time.plus(period)); //
		// UnsupportedTemporalTypeException

		System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
		System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		System.out.println(shortDateTime.format(dateTime)); // 1/20/20
		System.out.println(shortDateTime.format(date)); // 1/20/20
		// System.out.println(shortDateTime.format(time)); //
		// UnsupportedTemporalTypeException

		shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		System.out.println(dateTime.format(shortDateTime));
		System.out.println(date.format(shortDateTime));
//		System.out.println(time.format(shortDateTime));

		DateTimeFormatter shortF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		DateTimeFormatter mediumF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		DateTimeFormatter longF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		DateTimeFormatter fullF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		System.out.println(shortF.format(dateTime)); // 1/20/20 11:12 AM
		System.out.println(mediumF.format(dateTime)); // Jan 20, 2020 11:12:34 AM
//		System.out.println(dateTime.format(longF)); // 1/20/20 11:12 AM
//		System.out.println(dateTime.format(fullF)); // Jan 20, 2020 11:12:34 AM
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yy, h:mm a");
		System.out.println(dateTime.format(f)); // January 20, 2020, 11:12
		
//		DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM dd yyyy");
//		LocalDate date1 = LocalDate.parse("Jan 02 2015", f1);
//		LocalTime time1 = LocalTime.parse("11:22");
//		System.out.println(date1); // 2015-01-02
//		System.out.println(time1); // 11:22
		
		String test = "AAR";
		StringBuilder sb = new StringBuilder("AAR");
		System.out.println(sb.equals(test));
	}
}