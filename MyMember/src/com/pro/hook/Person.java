package com.pro.hook;

public class Person {

	private boolean shutup = false;

	public void shutdown() {
		shutup = true;
	}

	public void keepSaying() {
		while (!shutup) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("stop saying");
	}

	public static void main(String[] args) {
		String a = "13482241025";
		String b = "13482241025";
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
	}

}
