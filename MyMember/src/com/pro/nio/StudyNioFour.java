package com.pro.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class StudyNioFour {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ServerSocketChannel ssc = null;
		Selector sel = null;
		try {
			ssc = ServerSocketChannel.open();

			ServerSocket serverSock = ssc.socket();
			serverSock.bind(new InetSocketAddress(8090));
			ssc.configureBlocking(false);
			sel = Selector.open();
			ssc.register(sel, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				sel.select();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}

			Set<SelectionKey> set = sel.keys();

			Iterator<SelectionKey> iterator = set.iterator();
			while (iterator.hasNext()) {
				SelectionKey skey = iterator.next();
				iterator.remove();
				try {
					if (skey.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) skey
								.channel();

						SocketChannel client = server.accept();
						if (client != null) {
							ByteBuffer buffer = ByteBuffer.allocate(4);
							buffer.putInt(0);
							buffer.flip();
							client.register(sel, SelectionKey.OP_WRITE, buffer);
						}
					} else if (skey.isWritable()) {
						ByteBuffer buffer = (ByteBuffer) skey.attachment();
						if (!buffer.hasRemaining()) {
							buffer.rewind();
							int value = buffer.getInt();
							buffer.clear();
							buffer.putInt(value + 1);
							buffer.flip();
						}
						SocketChannel client = (SocketChannel) skey.channel();
						client.write(buffer);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
