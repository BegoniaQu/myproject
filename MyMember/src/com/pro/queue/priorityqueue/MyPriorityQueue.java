package com.pro.queue.priorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyPriorityQueue {

	/**
	 * 不是并发队列 优先级队列是不同于先进先出队列的另一种队列。每次从队列中取出的是具有最高优先权的元素
	 * 如果不提供Comparator的话，优先队列中元素默认按自然顺序排列，也就是数字默认是小的在队列头，字符串则按字典序排列
	 * 如果想实现按照自己的意愿进行优先级排列的队列的话，需要实现Comparator接
	 */

	class Nation implements Comparable<Nation> {
		String country;
		int population;

		Nation(String country, int population) {
			this.country = country;
			this.population = population;
		}

		public String toString() {
			return country + " has population " + population;
		}

		public String getCountry() {
			return country;
		}

		public int getPopulation() {
			return population;
		}

		public int compareTo(Nation o) {
			if (this.population > o.population) {
				return 1;
			} else if (this.population < o.population) {
				return -1;
			} else {
				return 0;
			}

		}

	}

	public static void main(String[] args) {
		// Comparator<Nation> comparator = new Comparator<Nation>() {
		// @Override
		// public int compare(Nation o1, Nation o2) {
		// int one = o1.getPopulation();
		// int two = o2.getPopulation();
		// if (one > two) {
		// return 1;
		// } else if (one < two) {
		// return -1;
		// } else {
		// return 0;
		// }
		// }
		// };
		// Queue<Nation> priQueue = new PriorityQueue<Nation>(11,comparator);

		Queue<Nation> priQueue = new PriorityQueue<Nation>();
		Nation nation1 = new MyPriorityQueue().new Nation("China", 1000);
		Nation nation2 = new MyPriorityQueue().new Nation("America", 5000);
		Nation nation3 = new MyPriorityQueue().new Nation("Russia", 4000);
		priQueue.add(nation1);
		priQueue.add(nation2);
		priQueue.add(nation3);
		System.out.println(priQueue.poll().toString());
		System.out.println(priQueue.poll().toString());
		System.out.println(priQueue.poll().toString());
	}
}
