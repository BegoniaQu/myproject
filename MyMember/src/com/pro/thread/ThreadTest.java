package com.pro.thread;

public class ThreadTest extends Thread {
	private Thread thread;

	public ThreadTest(Thread thread) {
		this.thread = thread;
	}

	public ThreadTest() {

	}

	@Override
	public void run() {
		// System.out.println("ThreadTest is on");
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// System.out.println("ThreadTest is over");
		holdThreadLock();
	}

	public void holdThreadLock() {
		synchronized (thread) {
			System.out.println("getObjectLock");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println("ReleaseObjectLock");
		}
	}
}
