package com.pro.thread;

public class MyThreadGroup {

	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("workgroup");
		Thread thread = new Thread(tg, new RunnableImpl()); // 将此线程加入线程组

		if (tg.activeCount() == 0) {
			// 线程组中所有线程均已终止
		}
	}
}
