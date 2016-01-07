package com.pro.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Car> cars = new ArrayList<Car>();
		for (Car car : cars) {

			for (City c : car.getCitys()) {
				ServicePool.service.execute(new Task(c));
			}
		}
	}

}
