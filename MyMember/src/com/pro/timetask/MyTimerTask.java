package com.pro.timetask;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

	public void run() {
		System.out.println("## start");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("## end");
	}

}
