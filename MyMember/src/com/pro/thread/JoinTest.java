package com.pro.thread;

public class JoinTest {
	public static void main(String[] args) {
		Thread t = new Thread(new RunnableImpl());
		// new ThreadTest().start();
		new ThreadTest(t).start(); // 如果ThreadTest
									// 先获得t锁，那么主线程就只能待ThreadTest释放锁后才能结束等待
		t.start();
		try {
			System.out.println("joinstart");
			t.join(2000);// 不管t有没有执行完，我只等你2s，2s后我主线程就要继续跑了,主线程再次阻塞一段时间
			System.out.println("joinFinish");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
