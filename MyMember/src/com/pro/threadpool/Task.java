package com.pro.threadpool;

public class Task implements Runnable {

	private City c;

	public Task(City c) {
		this.c = c;
	}

	public Task() {
	}

	public void run() {
		// 调用queryEasyIllegal方法，将c相关属性传递给这个方法
	}

}
