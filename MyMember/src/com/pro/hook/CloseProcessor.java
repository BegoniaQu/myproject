package com.pro.hook;

public class CloseProcessor extends Thread {

	private Person person;

	CloseProcessor(Person person) {
		this.person = person;
	}

	public void run() {
		System.out.println("daemon starts");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("closed");
		this.person.shutdown();
	}

}
