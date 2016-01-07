package com.pro.extension;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * 代理服务器的功能就是代理网络用户去取得网络信息。我们使用网络浏览器直接连接其他Internet站点取得网络信息时，通常需要发送Request请求来等到响应
 * 。 代理服务器是介于浏览器和Web服务器之间的一台服务器，有了它之后，浏览器不是直接到Web服务器去取得网页数据而是向代理服务器发出请求，
 * Request请求会先送到代理服务器，由代理服务器来取回浏览器所需要的信息并送回给网络浏览器。而且，大部分代理服务器都具有缓冲的功能，
 * 就好像一个大的Cache，它有很大的存储空间，它不断将新取得的数据储存到它本机的存储器上，如果浏览器所请求的数据在它本机的存储器上已经存在而且是最新的，
 * 那么它就不重新从Web服务器取数据，而直接将存储器上的数据传送给用户的浏览器，这样就能显著提高浏览速度和效率 归纳起来代理服务器主要提供如下两个功能：
 * 突破自身IP限制，对外隐藏自身IP地址。突破IP限制包括访问国外受限站点，访问国内特定单位、团体的内部资源。
 * 提高访问速度，代理服务器提供的缓冲功能可以避免每个用户都直接访问远程主机，从而提高客户端访问速度。
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class AnalyseProxy {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		ByteBuffer bb = Charset.forName("utf-8").encode(
				"GET http://www.baidu.com/ HTTP/1.1\r\n\r\n");
		// Proxy.Type.DIRECT：表示直接连接或缺少代理。
		// Proxy.Type.HTTP：表示高级协议的代理，如 HTTP 或 FTP。
		// Proxy.Type.SOCKS：表示 SOCKS（V4 或 V5）代理。

		String proxyAddress = "127.0.0.1"; // 以公司的公网IP对于服务器作为代理服务器
		int proxyPort = 1942;
		URL url = new URL("http://www.baidu.com");
		Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(
				proxyAddress, proxyPort));
		URLConnection con = url.openConnection(proxy);
		System.out.println("sssssssssssssss");
		con.setConnectTimeout(5000);
		Scanner scan = new Scanner(con.getInputStream(), "utf-8");// 初始化输出流

		while (scan.hasNextLine()) {
			String line = scan.nextLine();// 在控制台输出网页资源内容
			System.out.println(line);// 将网页资源内容输出到指定输出流
		}
		scan.close();
	}
}
