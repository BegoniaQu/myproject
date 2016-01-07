package com.pro.keyword;

public class Recource {

	private String str = "111";

	public void service() {
		System.out.println("i'm running" + Integer.parseInt(str));
	}

	public void close() {
		str = null;
	}

}
