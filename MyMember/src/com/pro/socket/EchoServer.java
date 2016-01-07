package com.pro.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(10);
		try {
			ServerSocket server = new ServerSocket(5008);
			while (true) {
				Socket incoming = server.accept();
				executor.execute(new ThreadEchoHandle(incoming));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
