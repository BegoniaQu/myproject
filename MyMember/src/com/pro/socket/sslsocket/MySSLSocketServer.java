package com.pro.socket.sslsocket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * 生成服务器数字证书和密钥库步骤： 1.keytool.exe -genkeypair -v -alias sslsocket -keyalg RSA
 * -keystore d:\keytools\sslsocket.keystore 2.查看密钥库：keytool.exe -list -v -alias
 * sslsocket -keystore d:\keytools\sslsocket.keystore
 * 3.制作证书:是从密钥库输出特定别名的证书，保存到证书文件sslsocket.cer keytool.exe -exportcert -v -alias
 * sslsocket -file d:\keytools\sslsocket.cer -keystore
 * d:\keytools\sslsocket.keystore 4.查看证书文件信息：keytool.exe -printcert -v -file
 * d:\keytools\sslsocket.cer 5.保存服务器端证书到客户端密钥库，以备客户接收到服务器端证书进行验证： keytool.exe
 * -importcert -v -alias sslsocketcer -file d:\keytools\sslsocket.cer -keystore
 * d:\keytools\sslclient.keystore 6.查看证书：keytool.exe -list -v -keystore
 * d:\keytools\sslclient.keystore 7.若想删除：keytool.exe -delete -alias sslsocketcer
 * -keystore d:\keytools\sslclient.keystore
 * 
 * 说明：客户端认证服务器，即判断当前客户端连接的服务器是否可信
 * 当客户使用SSL向站点服务器发送请求时，服务器向客户端发送一个证书，客户使用已安装的证书，验证服务器身份
 * ，然后检查IP地址(主机名)与客户端连接的主机是否匹配
 * 。客户生成可以用来对话的私钥(称为会话密钥)，然后用服务者的公钥对它进行加密并将它发送到服务者。服务者用自己的私钥解密
 * ，然后用该信息和客户端一样的私有会话密钥。通常在这个阶段使用RSA算法。
 * 随后，客户端和服务器端使用私有会话密钥和私钥算法(通常是RC4)进行通信。使用另一个密钥的消息认证码来确保消息的完整性。
 * 使用JSSE实现客户与服务器端安全通信的代码如下：MySSLSocketServer 和 MySSLSocketClient
 * 
 * 如果服务端验证客户端，方法也是一样的
 * 
 * private static String kpath = "d:\\keytools\\sslsocket.keystore"; private
 * static String tpath = "d:\\keytools\\sslsocketserver.keystore"; KeyStore ks =
 * KeyStore.getInstance("JKS"); ks.load(new FileInputStream(kpath), password);
 * KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
 * kmf.init(ks, password); KeyManager[] km = kmf.getKeyManagers(); KeyStore ts =
 * KeyStore.getInstance("JKS"); ts.load(new FileInputStream(tpath), password);
 * TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
 * tmf.init(ts); TrustManager[] tm = tmf.getTrustManagers(); context =
 * SSLContext.getInstance("SSL"); context.init(km, tm, null);
 * 
 * @author Administrator
 * 
 */
public class MySSLSocketServer {

	private static String path = "d:\\keytools\\sslsocket.keystore";
	private static char[] password = "quyang196667".toCharArray();
	private static boolean flag = true;

	public static void main(String[] args) {
		SSLContext context = null;
		SSLServerSocket ss = null;
		try {
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(path), password);
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, password);
			KeyManager[] km = kmf.getKeyManagers();
			context = SSLContext.getInstance("SSL");
			context.init(km, null, null);
			// 创建sslserversocket
			SSLServerSocketFactory sslskf = (SSLServerSocketFactory) context
					.getServerSocketFactory();
			ss = (SSLServerSocket) sslskf.createServerSocket(8000);

			if (ss == null) {
				System.out.println("创建SSLServerSocket 失败");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (flag) {
			System.out.println("等待客户点连接。。。");
			Socket s = null;
			try {
				s = ss.accept();
				System.out.println("接收到客户端连接");
				ObjectOutputStream os = new ObjectOutputStream(
						s.getOutputStream());
				ObjectInputStream br = new ObjectInputStream(s.getInputStream());
				Object obj = br.readObject();
				os.writeObject(obj);
				os.flush();
				os.close();
				System.out.println();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (s != null) {
					try {
						s.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}
}
