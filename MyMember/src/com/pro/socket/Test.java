package com.pro.socket;

import java.io.IOException;

public class Test {

	public static void main(String[] args) {

		try {
			// System.out.println(System.getProperty("user.dir"));
			Runtime.getRuntime()
					.exec("C:\\Program Files\\Tencent\\QQ\\QQProtect\\Bin\\QQProtect.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
