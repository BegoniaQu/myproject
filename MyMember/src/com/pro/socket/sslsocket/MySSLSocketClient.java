package com.pro.socket.sslsocket;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class MySSLSocketClient {

	private static String path = "d:\\keytools\\sslclient.keystore";
	private static char[] password = "quyang196667".toCharArray();

	public static void main(String[] args) {
		SSLContext context = null;
		try {
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(path), password);
			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance("SunX509");
			tmf.init(ks);
			TrustManager[] tm = tmf.getTrustManagers();
			context = SSLContext.getInstance("SSL");
			context.init(null, tm, null); // 认证服务器
			// 创建SSLSocket
			SSLSocketFactory sslsf = (SSLSocketFactory) context
					.getSocketFactory();
			SSLSocket sck = (SSLSocket) sslsf.createSocket("localhost", 8000);
			System.out.println("客户端就绪。");
			ObjectInputStream br = new ObjectInputStream(sck.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(
					sck.getOutputStream());
			os.writeObject("tomorrow is Friday,oh yeah!");
			System.out.println(br.readObject());
			br.close();
			sck.close();
			System.out.println("客户端测试ok");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
