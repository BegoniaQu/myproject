package com.pro.extension;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * 如果想让系统打开连接时总是具有默认的代理服务器，则可以使用java.net.ProxySelector，它可以它根据不同的连接使用不同的代理服务器。
 * 系统默认的ProxySelector会检测各种系统属性和URL协议，然后决定怎样连接不同的主机
 * 程序可以通过System类来设置系统的代理服务器属性，关于代理服务器常用的属性名有如下三个：
 * http.proxyHost：设置HTTP访问所使用的代理服务器地址
 * 。该属性名的前缀可以改为https、ftp等，分别用于设置HTTP访问、安全HTTP访问和FTP访问所用的代理服务器地址。
 * http.proxyPort：设置HTTP访问所使用的代理服务器端口
 * 。该属性名的前缀可以改为https、ftp等，分别用于设置HTTP访问、安全HTTP访问和FTP访问所用的代理服务器端口。
 * http.nonProxyHosts：设置HTTP访问中不需要使用代理服务器的远程主机，可以使用*通配符，如果有多个地址，多个地址用竖线（|）分隔。
 * 
 * @author Administrator
 * 
 */
public class AnalyseProxySelector {

	/**
	 * 系统提供了默认的ProxySelector子类作为代理选择器，开发者可以实现自己的代理选择器，
	 * 程序可以通过继承ProxySelector来实现自己的代理选择器。 继承ProxySelector需要重写两个方法： List<Proxy>
	 * select(URI
	 * uri)：实现该方法让代理选择器根据不同的URI来使用不同的代理服务器，该方法就是代理选择器管理网络连接使用代理服务器的关键。
	 * connectFailed(URI uri, SocketAddress sa, IOException
	 * ioe)：当系统通过默认的代理服务器建立连接失败后，代理选择器将会自动调用该方法。通过重写该方法可以对连接代理服务器失败的情形进行处理。
	 * 
	 * 系统默认的代理服务器选择器也重写了connectFailed方法，它重写该方法的处理策略是：当系统设置的代理服务器失败时，
	 * 默认代理选择器将会采用直连的方式连接远程资源， 所以当运行上面程序等待了足够长时间时，程序依然可以打印出该远程资源的所有内容。
	 * ProxySelector.getDefault();
	 * 
	 * @author Administrator
	 * 
	 */

	/**
	 * 定义自己的ProxySelector ProxySelector.setDefault(new mySelector())
	 * 
	 */
	public class mySelector extends ProxySelector {

		@Override
		public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {

		}

		@Override
		public List<Proxy> select(URI uri) {
			List<Proxy> result = new ArrayList<Proxy>();

			if (uri.getScheme().equalsIgnoreCase("http")) { // 不满足某种情况
				result.add(Proxy.NO_PROXY);
			} else {
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
						"代理服务器IP", 8000));
				result.add(proxy);
			}
			return result;
		}

	}

	public void setSystemProp() {

		Properties prop = System.getProperties();
		// 设置HTTP访问要使用的代理服务器的地址
		prop.setProperty("http.proxyHost", "10.10.0.96");
		// 设置HTTP访问要使用的代理服务器的端口
		prop.setProperty("http.proxyPort", "8080");
		// 设置HTTP访问不需要通过代理服务器访问的主机，
		// 可以使用*通配符，多个地址用|分隔
		prop.setProperty("http.nonProxyHosts", "localhost|10.20.*");
		// 设置安全HTTP访问使用的代理服务器地址与端口
		// 它没有https.nonProxyHosts属性，它按照http.nonProxyHosts 中设置的规则访问
		prop.setProperty("https.proxyHost", "192.168.0.96");
		prop.setProperty("https.proxyPort", "443");
		// 设置FTP访问的代理服务器的主机、端口以及不需要使用代理服务器的主机
		prop.setProperty("ftp.proxyHost", "10.10.0.96");
		prop.setProperty("ftp.proxyPort", "2121");
		prop.setProperty("ftp.nonProxyHosts", "localhost|10.10.*");
		// 设置socks代理服务器的地址与端口
		prop.setProperty("socks.ProxyHost", "10.10.0.96");
		prop.setProperty("socks.ProxyPort", "1080");
	}

	public void removeSystemProp() {
		Properties prop = System.getProperties();
		// 清除HTTP访问的代理服务器设置
		prop.remove("http.proxyHost");
		prop.remove("http.proxyPort");
		prop.remove("http.nonProxyHosts");
		// 清除HTTPS访问的代理服务器设置
		prop.remove("https.proxyHost");
		prop.remove("https.proxyPort");
		// 清除FTP访问的代理服务器设置
		prop.remove("ftp.proxyHost");
		prop.remove("ftp.proxyPort");
		prop.remove("ftp.nonProxyHosts");
		// 清除SOCKS的代理服务器设置
		prop.remove("socksProxyHost");
		prop.remove("socksProxyPort");
	}

	public void showProxy() throws Exception {
		URL url = new URL("http://www.baidu.com");
		// 直接打开连接，但系统会调用刚设置的HTTP代理服务器
		URLConnection con = url.openConnection();
		Scanner scanner = new Scanner(con.getInputStream(), "utf-8");
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
	}

	public static void main(String[] args) throws Exception {
		AnalyseProxySelector test = new AnalyseProxySelector();
		test.setSystemProp();
		test.showProxy();
		test.removeSystemProp();

	}
}
