package com.pro.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class StudyNioFive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FileChannel fc = null;
		try {
			FileInputStream fis = new FileInputStream("d:/iotest.txt");
			fc = fis.getChannel();
			ByteBuffer bb = ByteBuffer.allocate(64);
			while (fc.read(bb) != -1) {
				bb.flip();
				out(bb);
				bb.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fc != null) {
				try {
					fc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void out(ByteBuffer bb) throws IOException {
		WritableByteChannel out = Channels.newChannel(System.out); // 既然用了通道，则坚持一种完全基于通道的方案会更有好处吧
		out.write(bb);
	}
}
