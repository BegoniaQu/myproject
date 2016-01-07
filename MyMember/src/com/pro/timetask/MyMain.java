package com.pro.timetask;

public class MyMain {

	boolean isOK = true;

	public void change() {
		if (isOK) {
			isOK = false;
		}
	}

	public boolean isOK() {
		return isOK;
	}

	public static void main(String[] args) {
		int flag = 0x01;
		int readyFlag = 0;
		readyFlag |= flag;
		System.out.println(readyFlag);
		MyMain main = new MyMain();
		main.change();
		System.out.println(main.isOK());

		System.out.println(main.inc());
	}

	public int inc() {
		int x;
		try {
			x = 1;
			return x;
		} catch (Exception e) {
			x = 2;
			return x;
		} finally {
			x = 3;
		}

	}
}
