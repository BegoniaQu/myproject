package com.pro.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class MyIOSix {

	public static void main(String[] args) {

		test(1);

	}

	private static void test(int start) {
		BufferedWriter buffWriter = null;
		Writer writer = null;
		try {
			File file = new File("D:\\iotest.txt");
			int index = file.getName().lastIndexOf(".");
			String prefix = file.getName().substring(0, index);
			System.out.println(prefix);
			writer = new FileWriter(file, true); // true代表文件追加
			buffWriter = new BufferedWriter(writer);
			for (int i = start * 5; i < start * 5 + 5; i++) {
				buffWriter.write("你是第" + i + "个" + "\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffWriter != null) {
					buffWriter.flush();
					buffWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
