package com.pro.queue.deque;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Deque与BlockingDeque --双端队列，用于工作密取模式，BlockingQueue用于生产者-消费者模式
 * 双端队列：不像生产者-消费者设置中，所有消费者都共享一个工作队列。
 * 双端队列在工作密取中，每个消费者都有各自的双端队列。如果一个消费者完成了自己双端队列中的全部工作，
 * 那么它可以从其他消费者双端队列末尾秘密地获取工作。在大多数时候，它们都只是访问自己的双端队列，从而极大的减少了竞争。
 * 
 * @author Administrator
 * 
 */
public class MyDeque {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Semaphore //信号量
		// LinkedBlockingDeque<E> 并发
		// ArrayDeque<E> //非并发
	}

}
