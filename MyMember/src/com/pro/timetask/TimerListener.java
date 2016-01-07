package com.pro.timetask;

import java.util.Timer;

public class TimerListener extends Thread {

	private MyTimerTask task;
	private Timer timer;

	public TimerListener(MyTimerTask task) {
		this.task = task;
	}

	public TimerListener(Timer timer) {
		this.timer = timer;
	}

	public void run() {
		System.out.println("## start to listen");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// task.cancel(); //如果是任务取消，则此任务在结束后从queue中移除
		timer.cancel();// 如果是timer.cancel(),queue.clear().则在全部任务结束后退出。
		System.out.println("## listen over");

	}
}
