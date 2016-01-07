package com.pro.socket.sslsocket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import com.pro.socket.SafeBufferedReader;

public class SocketTwo {

	public static void main(String[] args) {
		SocketFactory sf = SSLSocketFactory.getDefault();
		String host = "www.wsistudents.com";
		SSLSocket sck = null;
		try {
			sck = (SSLSocket) sf.createSocket(host, 443);
			String[] suites = sck.getSupportedCipherSuites(); // 启用所有密码组
			sck.setEnabledCipherSuites(suites);
			Writer out = new OutputStreamWriter(sck.getOutputStream());
			out.write("GET http://" + host + "/ HTTP/1.1\r\n"); // https在get请求中需要完整的URL
			out.write("Host: " + host + "\r\n");
			out.write("\r\n");
			out.flush();

			SafeBufferedReader in = new SafeBufferedReader(
					new InputStreamReader(sck.getInputStream()));

			String str;

			while (!(str = in.readLine()).startsWith("Content-Length")) {
				System.out.println(str);
			}
			System.out.println("xxxxxxxxxxxxx" + str);

			int length = Integer.parseInt(str.substring(str.indexOf(":") + 1)
					.trim());

			StringBuilder sb = new StringBuilder();
			int c;
			int i = 0;
			while ((c = in.read()) != -1 && i++ < length) {
				sb.append((char) c);
			}
			out.close();
			in.close();
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sck != null) {
					sck.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

}
