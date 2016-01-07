package com.pro.common;

import java.util.Calendar;
import java.util.Date;

public class SymbolTransfer {

	public static void main(String[] args) {
		int str = 2;

		switch (str) {
		case 1:
			System.out.println("ddddd");
			break;
		case 2:
		case 11:
			System.out.println("ddd");
			break;
		}

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DAY_OF_WEEK, 7);
		Date monDay = cal.getTime();
		System.out.println(monDay);
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
	}
}
