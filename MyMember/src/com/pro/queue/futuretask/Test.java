package com.pro.queue.futuretask;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			MyFutureTask ft = new MyFutureTask(new Callable<String>() {
				public String call() throws Exception {
					System.out.println("execute task");
					Random rand = new Random();
					TimeUnit.SECONDS.sleep(rand.nextInt(5));
					return Thread.currentThread().getName();
				}
			});
			service.submit(ft);
			try {
				System.out.println(ft.get()); // 在执行完任务之前，会阻塞！
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();
	}

}
