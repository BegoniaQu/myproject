package com.pro.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketHelper {

	private static final int BUFFER_SIZE = 64;

	public void close(SocketChannel sc) {
		try {
			if (sc != null) {
				sc.close();
			}
		} catch (IOException e) {

		}
	}

	/**
	 * read the data
	 * 
	 * @param sc
	 * @return
	 * @throws Exception
	 */
	public byte[] readSock(SocketChannel sc) throws Exception {
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		ByteBuffer speciBuffer = null;
		while (sc.read(buffer) > 0) {
			buffer.flip();
			byte[] b = new byte[buffer.limit()];
			buffer.get(b, 0, buffer.limit());
			if (speciBuffer == null) {
				speciBuffer = ByteBuffer.allocate(b.length);
				speciBuffer.put(b);
			} else {
				byte[] temp = speciBuffer.array();
				speciBuffer = ByteBuffer.allocate(temp.length + b.length);
				speciBuffer.put(temp);
				speciBuffer.put(b);
			}
			buffer.clear();
		}
		if (speciBuffer != null) {
			return speciBuffer.array();
		} else {
			return null;
		}

	}

	/**
	 * write the data
	 * 
	 * @param sc
	 * @param b
	 * @throws Exception
	 */
	public void writeSock(SocketChannel sc, byte[] b) throws Exception {
		ByteBuffer buffer = ByteBuffer.wrap(b);
		while (buffer.hasRemaining()) {
			sc.write(buffer);
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		ByteBuffer speciBuffer = null;
		int i = 1;
		while (i < 4) {
			String str = "abcd|";
			buffer.put(str.getBytes(), 0, str.length());
			buffer.flip();
			byte[] b = new byte[buffer.limit()];
			buffer.get(b, 0, buffer.limit());
			if (speciBuffer == null) {
				speciBuffer = ByteBuffer.allocate(b.length);
				speciBuffer.put(b);
			} else {
				byte[] temp = speciBuffer.array();
				speciBuffer = ByteBuffer.allocate(temp.length + b.length);
				speciBuffer.put(temp);
				speciBuffer.put(b);
			}
			buffer.clear();
			i++;
		}
		System.out.println(new String(speciBuffer.array()));
	}
}
