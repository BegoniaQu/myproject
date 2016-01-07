package com.pro.hook;

public class HookApp {

	// public static void main(String[] args) {
	//
	// Person person=new Person();
	// new Service(person).start();
	// new CloseProcessor(person).start();
	// }

	public static void main(String[] args) {
		Person person = new Person();

		Runtime.getRuntime().addShutdownHook(new CloseProcessor(person)); // 创建一个钩

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("## main is over");
	}
}
