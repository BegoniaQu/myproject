package com.pro.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TCPReactor implements Runnable {

	private final static String module = TCPReactor.class.getName();
	private final Selector sel; // Selector 实例
	private final ServerSocketChannel sc; // SeletableCannel一个实现

	public TCPReactor(int port) throws Exception {
		sel = Selector.open();
		sc = ServerSocketChannel.open();
		InetSocketAddress endpoint = new InetSocketAddress(port);
		sc.socket().bind(endpoint);
		sc.configureBlocking(false);
		sc.register(sel, SelectionKey.OP_ACCEPT, new Acceptor(sel, sc));
	}

	@Override
	public void run() {
		System.out.println(module + " started");
		try {
			while (!Thread.interrupted()) {
				sel.select();
				Set<SelectionKey> set = sel.selectedKeys();
				Iterator<SelectionKey> ite = set.iterator();
				while (ite.hasNext()) {
					SelectionKey key = ite.next();
					if (key.isAcceptable() || key.isReadable()
							|| key.isWritable()) {
						dispatch(key);
					}

				}
				set.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dispatch(SelectionKey sk) {
		Runnable obj = (Runnable) sk.attachment();
		if (obj != null) {
			obj.run();
		}
	}

	public static void main(String[] args) {
		try {
			Thread t = new Thread(new TCPReactor(9095));
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
