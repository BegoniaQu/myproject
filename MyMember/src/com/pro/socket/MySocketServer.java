package com.pro.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocketServer {

	private static boolean isEnd = false;

	public void start() {
		System.out.println("ServerSocket starts...");
		try {
			ServerSocket ss = new ServerSocket(5008);
			while (!isEnd) {
				Socket socket = ss.accept();
				byte[] bytes = new byte[512];
				InputStream is = socket.getInputStream();
				is.read(bytes);
				System.out.println("receive：" + new String(bytes).trim());

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MySocketServer().start();
	}
}
