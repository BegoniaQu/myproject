package com.pro.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {
	private final static String module = Acceptor.class.getName();
	private final Selector sel;

	private final ServerSocketChannel ssc;

	public Acceptor(Selector selector, ServerSocketChannel ssc) {
		this.sel = selector;
		this.ssc = ssc;
	}

	@Override
	public void run() {
		System.out.println(module + "started");
		try {
			SocketChannel schannel = ssc.accept();
			if (schannel != null) {
				schannel.configureBlocking(false);
				SelectionKey sk = schannel.register(sel, SelectionKey.OP_READ);
				sk.attach(new TCPHandler(sk, schannel));
				sel.wakeup();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
