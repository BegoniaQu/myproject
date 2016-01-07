package com.pro.hook;

public class Service extends Thread {

	private Person person;

	Service(Person person) {
		this.person = person;
	}

	public void run() {
		this.person.keepSaying();
	}
}
