package com.pro.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

public class MyIOTwo {

	public static void main(String[] args) {
		String s = "whenyouzaiyigedifang,wowangjilefangxiang";
		byte buf[] = s.getBytes();
		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		PushbackInputStream f = new PushbackInputStream(in, 16);
		byte[] b = new byte[8];
		int i = 0, index = 0;
		String sentence = "";
		try {
			while ((i = f.read(b)) != -1) {
				String piece = new String(b, 0, i);
				sentence = sentence + piece;
				if ((index = sentence.indexOf("wang")) > 0) {
					f.unread(sentence.getBytes());
					if (index > 8) {
						b = new byte[index];
					}
					f.read(b);
					System.out.println("OK: " + new String(b, 0, index));
					break;
				} else {
					sentence = piece;
					System.out.println(sentence);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
