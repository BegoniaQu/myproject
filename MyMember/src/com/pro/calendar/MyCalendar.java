package com.pro.calendar;

import java.util.Calendar;
import java.util.Date;

public class MyCalendar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 2);
		Date monDay = cal.getTime();
		System.out.println(monDay); // 周一
		cal.set(Calendar.DAY_OF_WEEK, 7);
		System.out.println(cal.getTime()); // 周六
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		Date sunDay = cal.getTime();
		System.out.println(sunDay); // 周日
	}

}
