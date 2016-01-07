package com.pro.socket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class SocketOne {

	/**
	 * java.net.SocketException: （Connection reset或者Connect reset by peer:Socket
	 * write error）。 该异常在客户端和服务器端均有可能发生，引起该异常的原因有两个:
	 * 第一个就是如果一端的Socket被关闭（或主动关闭或者因为异常退出而引起的关闭），另一端仍发送数据，发送的第一个数据包引发该异常(Connect
	 * reset by peer); 另一个是一端退出，但退出时并未关闭该连接，另一端如果在从连接中读数据则抛出该异常（Connection
	 * reset）。简单的说就是在连接断开后的读和写操作引起的。
	 * 
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException,
			IOException {

		// StringTokenizer st = new
		// StringTokenizer("GET http 1.1 mysql server");
		// System.out.println(st.nextToken());
		// System.out.println(st.nextToken());
		// System.out.println(st.nextToken());
		// System.out.println(st.nextToken());
		// System.out.println(st.nextToken());
		SocketOne one = new SocketOne();
		one.test();
	}

	public void test() throws UnknownHostException, IOException {
		Socket sock = new Socket("www.yahoo.com", 80);
		SocketAddress sa = sock.getRemoteSocketAddress();
		sock.close();
		// /////////////////////
		sock = new Socket();
		sock.connect(sa); // sa可以复用
		// sock.connect(endpoint, timeout); 超时设置
		InputStream is = sock.getInputStream();

		SafeBufferedReader in = new SafeBufferedReader(
				new InputStreamReader(is));
		String str;
		StringBuilder sb = new StringBuilder();
		while ((str = in.readLine()) != null) {
			sb.append(str);
		}
		in.close();
		sock.close();
		System.out.println(sb.toString());
	}
}
