package com.pro.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 客户端的基于通道和缓冲区的io
 * 
 * @author Administrator
 * 
 */
public class StudyNioOne {

	public static void main(String[] args) throws Exception {
		SocketHelper sh = new SocketHelper();
		SocketAddress sa = new InetSocketAddress(InetAddress.getLocalHost(),
				9095);
		SocketChannel sc = null;
		try {
			sc = SocketChannel.open(sa);
			sc.configureBlocking(false); // 非阻塞方式
			Scanner scan = new Scanner(System.in);
			while (true) {
				String str = scan.next();
				sh.writeSock(sc, str.getBytes());
				// Thread.sleep(2000);
				byte[] b = sh.readSock(sc);
				if (b == null) {
					System.out.println("is null");
				} else {
					System.out.println(new String(b));
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sh.close(sc);
			System.out.println(sc.isConnected());
		}
	}

	/**
	 * 非阻塞模式
	 * 
	 * @throws Exception
	 */
	private void nonBlockingWay() throws Exception {
		SocketAddress sa = new InetSocketAddress(InetAddress.getLocalHost(), 19);
		SocketChannel sc = null;
		try {
			sc = SocketChannel.open(sa);
			sc.configureBlocking(false); // 非阻塞方式
			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel output = Channels.newChannel(System.out);
			while (true) {
				if (sc.read(buffer) > 0) { // 没有数据时返回0
					buffer.flip(); // 当前位置设置为EOF，指针指向0,数据的时候，读到EOF位置就结束了
					output.write(buffer);
					buffer.clear(); // 只是把指针移到位置0，并没有真正清空数据。
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
			}
			try {
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 阻塞模式
	 */
	private void blockingWay() {
		try {
			SocketAddress sa = new InetSocketAddress("rama.poly.edu", 19);
			SocketChannel sc = SocketChannel.open(sa);
			ByteBuffer buffer = ByteBuffer.allocate(74);

			WritableByteChannel out = Channels.newChannel(System.out); // 既然用了通道，则坚持一种完全基于通道的方案会更有好处吧
			while (sc.read(buffer) != -1) { // 阻塞和普通io一样，会在没有数据可读时，返回-1
				buffer.flip();
				out.write(buffer);
				buffer.clear(); // 为了可以重用buffer
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
