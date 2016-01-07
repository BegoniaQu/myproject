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

public class StudyNioTwo {

	private static int DEFAULT_PORT = 1990;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] rotation = new byte[95 * 2];
		for (byte i = ' '; i <= '~'; i++) { // ' '--32,'~'--126
			rotation[i - ' '] = i;
			rotation[i + 95 - ' '] = i;
		}
		ServerSocketChannel serverChannel;
		Selector selector = null;
		try {
			serverChannel = ServerSocketChannel.open();
			ServerSocket ss = serverChannel.socket();
			ss.bind(new InetSocketAddress(DEFAULT_PORT));
			serverChannel.configureBlocking(false);
			selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		while (true) {
			try {
				if (selector != null) {
					selector.select();// 阻塞的
					// selector.select(1000);//阻塞1秒时间仍旧没有符合（correspond）使用的通道则返回0
					// selector.selectNow();//非阻塞的，没有符合使用的通道即刻返回0
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		Set<SelectionKey> readyKeys = selector.selectedKeys(); // 获取就绪通道
		Iterator<SelectionKey> iterator = readyKeys.iterator();
		while (iterator.hasNext()) {
			SelectionKey key = iterator.next();
			iterator.remove(); // 拿到key后，移除这个key
			try {
				if (key.isAcceptable()) {
					ServerSocketChannel ssc = (ServerSocketChannel) key
							.channel();
					SocketChannel sc = ssc.accept();
					sc.configureBlocking(false);
					SelectionKey key2 = sc.register(selector,
							SelectionKey.OP_WRITE);
					ByteBuffer buffer = ByteBuffer.allocate(74);
					buffer.put(rotation, 0, 72);
					buffer.put((byte) '\r');
					buffer.put((byte) '\n');
					buffer.flip();
					key2.attach(buffer); // 附加对象

				} else if (key.isWritable()) {
					SocketChannel schannel = (SocketChannel) key.channel();
					ByteBuffer buffer = (ByteBuffer) key.attachment();
					if (!buffer.hasRemaining()) {
						buffer.rewind();
						int first = buffer.get();
						buffer.rewind();
						int position = first - ' ' + 1;
						buffer.put(rotation, position, 72);
						buffer.put((byte) '\r');
						buffer.put((byte) '\n');
						buffer.flip();
					}
					schannel.write(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
				key.cancel();
				try {
					key.channel().close(); // 通道关闭
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
