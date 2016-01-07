package com.pro.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NioClient {

	public static void main(String[] args) throws Exception {
		Selector sel = Selector.open();
		SocketHelper sh = new SocketHelper();
		SocketAddress sa = new InetSocketAddress("192.168.1.100", 9095);
		SocketChannel sc = null;
		try {
			sc = SocketChannel.open(sa);
			sc.configureBlocking(false);
			sc.register(sel, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

			while (!Thread.interrupted()) {
				sel.select();
				Set<SelectionKey> set = sel.selectedKeys();
				for (SelectionKey sk : set) {
					if (sk.isReadable()) {
						System.out.println("start reading msg");
						byte[] b = sh.readSock(sc);
						System.out.println(new String(b));
						Thread.sleep(1000);
						sk.interestOps(SelectionKey.OP_WRITE);
					}
					if (sk.isWritable()) {
						sh.writeSock(sc, "dddddddddd".getBytes());
						sk.interestOps(SelectionKey.OP_READ);
					}
				}
				set.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
