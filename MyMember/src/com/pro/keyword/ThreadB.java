package com.pro.keyword;

public class ThreadB extends Thread {

	private Recource resource;

	ThreadB(Recource res) {
		resource = res;
	}

	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StudyVolatile.open = false;
		resource.close();
	}
}
