package com.pro.queue.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelay implements Delayed {

	private long delayTime;

	public MyDelay(long submitTime) {
		this.delayTime = TimeUnit.NANOSECONDS.convert(submitTime,
				TimeUnit.MILLISECONDS) + System.nanoTime();
	}

	public long getDelay(TimeUnit unit) {

		return unit
				.convert(delayTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	public int compareTo(Delayed o) {
		MyDelay next = (MyDelay) o;
		return this.delayTime > next.delayTime ? 1
				: this.delayTime < next.delayTime ? -1 : 0;
	}

}
