package com.pro.thread;

/**
 * Thread.setDaemon的用法，经过学习以后了解： 1. setDaemon需要在start方法调用之前使用 2.
 * 线程划分为用户线程和后台(daemon)进程，setDaemon将线程设置为后台进程 3.
 * 如果jvm中都是后台进程，当前jvm将exit。（随之而来的，所有的一切烟消云散，包括后台线程啦） 4. 主线程结束后， 1） 用户线程将会继续运行 2）
 * 如果没有用户线程，都是后台进程的话，那么jvm结束 另外：
 * setDaemon方法把java的线程设置为守护线程，此方法的调用必须在线程启动之前执行。只有在当前jvm中所有的线程都为守护线程时，jvm才会退出。
 * 如果创建的线程没有显示调用此方法，这默认为用户线程。 定义：守护线程--也称“服务线程”，在没有用户线程可服务时会自动离开。
 * 优先级：守护线程的优先级比较低，用于为系统中的其它对象和线程提供服务。
 * 设置：通过setDaemon(true)来设置线程为“守护线程”；将一个用户线程设置为 守护线程的方式是在 线程对象创建 之前
 * 用线程对象的setDaemon方法。 example: 垃圾回收线程就是一个经典的守护线程，当我们的程序中不再有任何运行的
 * Thread,程序就不会再产生垃圾，垃圾回收器也就无事可做，所以当垃圾回收线程是
 * JVM上仅剩的线程时，垃圾回收线程会自动离开。它始终在低级别的状态中运行，用于 实时监控和管理系统中的可回收资源。
 * 生命周期：守护进程（Daemon）是运行在后台的一种特殊进程。它独立于控制终端并且 周期性地执行某种任务或等待处理某些发生的事件。也就是
 * 说守护线程不依赖于终端，但是依赖于系统，与系统“同生共死”。那Java的守护线程是
 * 什么样子的呢。当JVM中所有的线程都是守护线程的时候，JVM就可以退出了；如果还有一个 或以上的非守护线程则JVM不会退出。
 * 
 * @author Andrew
 * 
 */

public class MyDaemonThread extends Thread {
	private boolean condition = true;
	private String threadName;
	private int threadNumber;

	MyDaemonThread(String threadName, int threadNumber) {
		this.threadName = threadName;
		this.threadNumber = threadNumber;
	}

	public void run() {
		while (condition) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("## " + threadName + " is alive");
		}
		if (threadNumber == 0) {
			System.out.println("daemonThread killed itself");
		} else {
			System.out.println("userThread is dying");
		}
	}

	public static void main(String[] args) {
		MyDaemonThread one = new MyDaemonThread("daemonThread", 0);
		one.setDaemon(true);
		one.start();
		MyDaemonThread two = new MyDaemonThread("userThread", 1);
		two.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		two.condition = false;

	}
}
