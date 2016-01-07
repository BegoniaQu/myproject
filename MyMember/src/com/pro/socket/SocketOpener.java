package com.pro.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketOpener implements Runnable {

	public static Socket openSocket(String host, int port, int timeout) {
		SocketOpener so = new SocketOpener(host, port);
		Thread thread = new Thread(so);
		thread.start();
		try {
			thread.join(10000); // 十秒钟还没有构造成功一个socket的话，就不管它了，当做失败处理
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return so.getSocket();
	}

	private SocketOpener(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() {
		try {
			socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String host;
	private int port;
	private Socket socket;

	public Socket getSocket() {
		return socket;
	}
}
