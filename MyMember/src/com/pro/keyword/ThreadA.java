package com.pro.keyword;

public class ThreadA extends Thread {

	private Recource resource;

	ThreadA(Recource res) {
		resource = res;
	}

	public void run() {
		while (StudyVolatile.open) {
			resource.service();
		}
		System.out.println("over");
	}
}
