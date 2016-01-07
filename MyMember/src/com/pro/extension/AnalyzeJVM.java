package com.pro.extension;

public class AnalyzeJVM {

	public static AnalyzeJVM saveObject = null;
	public int i = 0;

	public void say() {
		i++;
		System.out.println("I am alive");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		saveObject = this;
	}

	public static void main(String[] args) {

		AnalyzeJVM.saveObject = new AnalyzeJVM();
		saveObject.say();
		System.out.println(saveObject.i);
		saveObject = null;
		System.gc();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (AnalyzeJVM.saveObject != null) {
			AnalyzeJVM.saveObject.say();
			System.out.println(saveObject.i);
		} else {
			System.out.println("oh,I am dead");
		}

		saveObject = null;
		System.gc();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (AnalyzeJVM.saveObject != null) {
			AnalyzeJVM.saveObject.say();
		} else {
			System.out.println("oh,I am dead");
		}
	}

}
