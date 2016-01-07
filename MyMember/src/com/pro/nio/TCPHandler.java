package com.pro.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class TCPHandler implements Runnable {

	private static final String module = TCPHandler.class.getName();
	private final SocketChannel schannel;
	private final SelectionKey skey;
	private final SocketHelper shelper;
	private final int READING = 1;
	private final int SENDING = 2;
	private static int state = 1;

	public TCPHandler(SelectionKey sk, SocketChannel sc) {
		this.skey = sk;
		this.schannel = sc;
		shelper = new SocketHelper();
	}

	public void run() {
		System.out.println(module + " started");
		try {
			if (state == READING) {
				System.out.println("start receiving msg");
				read();// 读取数据
			} else if (state == SENDING) {
				System.out.println("start writing data");
				send(); // 写入数据
			}
		} catch (Exception e) {
			shelper.close(schannel);
			skey.cancel();
			e.printStackTrace();
		}
	}

	private void read() throws Exception {
		byte[] b = shelper.readSock(schannel);
		if (b != null && b.length == 0) {
			System.out.println("socket read data exception");
			return;
		} else if (b == null) {
			System.out.println("none data");
			return;
		}
		System.out.println("read data: " + new String(b));
		state = SENDING;
		skey.interestOps(SelectionKey.OP_WRITE);
	}

	private void send() throws Exception {
		String msg = "ok,i got your msg";
		shelper.writeSock(schannel, msg.getBytes());
		state = READING;
		skey.interestOps(SelectionKey.OP_READ); // 切换到只读模式，这时，selector只关注只读
	}
}
