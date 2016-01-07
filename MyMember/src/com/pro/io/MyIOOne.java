package com.pro.io;

import java.io.FileInputStream;
import java.io.InputStream;

public class MyIOOne {

	public static void main(String[] args) {
		try {
			InputStream is = new FileInputStream("one.txt");
			int byteAvailable = is.available();
			byte[] b = new byte[byteAvailable];
			is.read(b, 0, byteAvailable);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// URL url = new URL();

	}
}
