package com.pro.keyword;

public class StudyVolatile {
	static boolean open = true;

	public static void main(String[] args) {
		Recource resource = new Recource();
		ThreadA a = new ThreadA(resource);
		a.start();
		ThreadB b = new ThreadB(resource);
		b.start();
	}
}
