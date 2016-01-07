package com.pro.socket;

import com.huawei.eidc.slee.security.Base64;

public class MyBase64 {

	/*
	 * Base64要求把每三个8Bit的字节转换为四个6Bit的字节（3*8 = 4*6 =
	 * 24），然后把6Bit再添两位高位0，组成四个8Bit的字节， 也就是说，转换后的字符串理论上将要比原来的长1/3。
	 */

	public static void main(String[] args) {
		String encoding = Base64.encode("15".getBytes()); // 00001111 ->00000011
															// 11
		System.out.println(encoding);

		byte[] b = "15".getBytes(); // 001100 010011 0101 ->00001100 00010011
									// 010100= ->12 19 ->MT
		System.out.println(b.length);

		byte[] str = "我是".getBytes();
		System.out.println(str.length);

		for (int i = 0; i < 2; i++) {
			System.out.println(b[i]);
		}
	}

}
