package com.pro.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServicePool {

	public static ExecutorService service;

	public static ExecutorService getCachedPool() {
		return Executors.newCachedThreadPool();
	}

	/**
	 * 你用这个
	 * 
	 * @param size
	 * @return
	 */
	public void getFixedPool(int size) {
		// int proNum=Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool(size);

	}

	public void closePool(ExecutorService poolService) {
		poolService.shutdown();
	}
}
