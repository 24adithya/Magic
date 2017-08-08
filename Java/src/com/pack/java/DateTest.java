package com.pack.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat soapDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
	
	
	public static void main(String[] args)
	{
		/*DateTest test = new DateTest();
		Date date = Calendar.getInstance().getTime();
		test.convDate("2015-11-01");
		
		Set<String> set = new HashSet<>();
		set.add("a");
		test.processCollection(set);*/

		Calendar c = Calendar.getInstance();
		soapDateFormat.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
		c.setTimeInMillis(1488456000000l);
		System.out.println(soapDateFormat.format(c.getTime()));
	}
	
	private void processCollection(Collection<String> coll)
	{
		
	}
	
	private Date convDate(String date)
	{
		Date convDate = null;
		try
		{
			Calendar cal = Calendar.getInstance();
			   cal.set(2002,7,6);
			   System.out.println("Day is "+cal.get(cal.DAY_OF_WEEK));
			
			convDate = simpleDateFormat.parse(date);
			System.out.println(convDate);
			date = date.concat("T00:00:00.0");
			convDate = soapDateFormat.parse(date);
			System.out.println(convDate);
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return convDate;
	}

}
