package com.pro.operators;

public class LearnOperator {

	public static void main(String[] args) {
		int a = 7; // 0111
		int b = 6; // 0110
		System.out.println(a % 2);
		System.out.println(a & 1);

		System.out.println(2 ^ 5);

		// 0110^0111 = 0001
		System.out.println(a ^ b);
	}
}
