package com.pro.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import com.huawei.eidc.slee.security.Base64;

public class Myurl {

	public void one() {
		// 如果想了解资料的更多信息，那么应该用URLConnection;
		URL url = null;
		try {
			url = new URL("http://www.baidu.com");
			URLConnection uc = url.openConnection();
			uc.connect();

			int n = 1;
			String key;
			while ((key = uc.getHeaderField(n)) != null) {
				System.out.println(key);
				n++;
			}
			System.out.println("ContentType:" + uc.getContentType());
			System.out.println("ContentLength:" + uc.getContentLength());
			System.out.println("ContentEncoding:" + uc.getContentEncoding());
			System.out.println("getDate:" + uc.getDate());
			System.out.println("getExpiration:" + uc.getExpiration());
			System.out.println("getLastModified:" + uc.getLastModified());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 对于有口令的ftp访问
	public void two_1() {
		URL url = null;
		try {
			url = new URL("ftp://nop1:nop1234@192.168.1.102");
			// 如果只想读取资源内容
			InputStream is = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 访问一个有口令保护的网页
	public void two_2(String username, String password) {
		String input = username + ":" + password;

		String encoding = Base64.encode(input.getBytes());
		URL url = null;
		try {
			url = new URL("http://www.baidu.com");
			// 如果只想读取资源内容
			URLConnection con = url.openConnection();
			con.setRequestProperty("Authorization", "Basic " + encoding);
			con.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 带参数的请求
	public void three(String url, String name, String value) throws Exception {
		PrintWriter pw = null;
		URLConnection con = null;
		try {
			URL urlO = new URL(url);
			con = urlO.openConnection();
			con.setDoInput(true); // 需要输出
			pw = new PrintWriter(con.getOutputStream());
			pw.print(name + "=" + URLEncoder.encode(value, "UTF-8") + "\n"); // 没有指定字符编码的已经不过推荐使用了

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		BufferedReader br = null;
		try {
			if (con != null) {
				br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
			}

		} catch (IOException e) { // 对于返回出错时，获取错误的网页信息，需(HttpURLConnection)con
									// 进行强制转换
			InputStream err = ((HttpURLConnection) con).getErrorStream();
			if (err == null) {
				throw e;
			}
			br = new BufferedReader(new InputStreamReader(err));
		}
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		br.close();
	}

	public static void main(String[] args) {

		try {
			InetAddress inward = InetAddress.getByName("127.0.0.1");
			System.out.println(inward.getHostName());
			System.out.println(inward.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
