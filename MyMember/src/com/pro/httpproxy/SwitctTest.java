package com.pro.httpproxy;

public class SwitctTest {

	public static void test1() {
		int year = 2005;
		int month = 3;
		int day = 0;
		while (true) {
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				day = 31;
				// continue;
				break;
			case 2:
				day = 28;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				day = 30;
				break;
			}
			System.out.println(day);
			if (day > 0) {
				break;
			}
		}

	}

	public static void main(String[] args) {
		test1();
	}
}
