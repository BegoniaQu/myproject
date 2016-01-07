package com.pro.httpproxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpProxy {

	/*
	 * /上级代理服务器地址和端口 private static String parent = null; private static int
	 * parentPort = -1; /** 描述：设置上级代理地址和端口，可选方法
	 * 
	 * @param name 描述：
	 * 
	 * @param pport 描述：
	 */
	/*
	 * public static void setParentProxy(String name, int pport) { parent =
	 * name; parentPort = pport; }
	 */

	/**
	 * 描述：开启代理服务
	 * 
	 * @param port
	 *            描述：
	 */
	public static void startProxy(int port) {

		ServerSocket ssock = null;
		ThreadPool pool = ThreadPool.getInstance();

		try {

			ssock = new ServerSocket(port);
			System.out.println("在端口1942启动代理服务器/n");

			// 浏览器对同一页面上的不同图元采用不同的线程进行请求的
			// 所以用不同的线程进行服务
			// 一个页面使用的线程数和其图元数一致
			// 所以采用线程池是必要的
			while (true) {
				Socket tmp = ssock.accept();
				pool.assign(new DataService(tmp));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.complete();
			if (ssock != null) {
				try {
					ssock.close();
				} catch (Exception e) {

				}
			}
		}
	}

	// 测试用的简单main方法
	public static void main(String[] args) {
		HttpProxy.startProxy(1942);
	}

}
