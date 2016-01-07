package com.pro.httpproxy;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;

public class DataService implements Runnable {

	int CONNECT_RETRIES = 5;
	int CONNECT_PAUSE = 5;
	int TIMEOUT = 5000; // socket超时设置
	int BUFSIZ = 1024 * 5; // 缓冲区
	boolean loging = false; // 是否输出日志信息

	// 和此线程关联的Socket，用于和客户端交换数据
	Socket socket;

	// 输出日志
	public void log(String log) {
		if (loging) {
			System.out.println(log);
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param s
	 *            描述：
	 */
	public DataService(Socket s) {
		socket = s;
	}

	// 对请求的每一个图元进行服务的线程
	public void run() {
		String line;
		String host;
		int port = 80; // 默认
		Socket outbound = null;
		try {
			socket.setSoTimeout(TIMEOUT);
			InputStream is = socket.getInputStream();

			OutputStream os = null;
			// 获取请求行的内容
			line = "";
			host = "";
			int state = 0;
			char v = ' ';
			boolean space;
			while (true) {
				int c = is.read();
				v = (char) c;
				System.out.print(v);
				if (v == ' ') {
					System.out.println();
				}
				if (c == -1) {
					break;
				}
				space = Character.isWhitespace((char) c);
				switch (state) {
				case 0:
					if (space) {
						continue;
					}
					state = 1;
				case 1:
					if (space) {
						state = 2;
						continue;
					}
					line = line + (char) c;
					break;
				case 2:
					if (space) {
						continue; // 跳过多个空白字符
					}
					state = 3;
				case 3: // ----------------------------------------------------------
					if (space) {
						state = 4;
						// 只取出主机名称部分
						String host0 = host;
						// System.out.println(host0);
						int n;
						n = host.indexOf("//");
						if (n != -1) {
							host = host.substring(n + 2);
						}
						n = host.indexOf('/');
						if (n != -1) {
							host = host.substring(0, n);
						}
						// 分析可能存在的端口号
						n = host.indexOf(":");
						if (n != -1) {
							port = Integer.parseInt(host.substring(n + 1));
							host = host.substring(0, n);
						}

						// 如果存在上级代理，则不用进行以上操作，直接和代理通讯

						int retry = CONNECT_RETRIES;

						// 尝试连接远程目标，CONNECT_RETRIES次
						while (retry-- != 0) {
							try {
								outbound = new Socket(host, port);
								break;
							} catch (Exception e) {
								// System.out.println ("重试...");
							}
							// 等待
							Thread.sleep(CONNECT_PAUSE);
						}
						if (outbound == null) {
							break;
						}
						// System.out.println(line);
						// outbound.setSoTimeout(TIMEOUT);
						os = outbound.getOutputStream(); // 向远程服务器写入请求
						os.write(line.getBytes());
						os.write(' ');
						os.write(host0.getBytes());
						os.write(' ');
						pipe(is, outbound.getInputStream(), os,
								socket.getOutputStream());
						break; // 跳出case 3
					}
					host = host + (char) c;
					break;

				// -------------------------------------------------------------
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e1) {
			}
			try {
				outbound.close();
			} catch (Exception e2) {
			}
		}
	}

	/**
	 * 描述：数据传送桥
	 * 
	 * @param is0
	 *            描述：读取客户的请求信息
	 * @param is1
	 *            描述：读取远程服务器返回数据
	 * @param os0
	 *            描述：向远程服务器写入请求信息
	 * @param os1
	 *            描述：向客户端写入服务器返回数据
	 */
	void pipe(InputStream is0, InputStream is1, OutputStream os0,
			OutputStream os1) {
		try {
			int ir;
			byte[] bytes = new byte[BUFSIZ];
			while (true) {
				try {
					if ((ir = is0.read(bytes)) > 0) {
						os0.write(bytes, 0, ir);
						// System.out.println (new String(bytes).trim ());
					} else if (ir < 0) {
						break;
					}
				} catch (InterruptedIOException e) {
				}
				try {
					if ((ir = is1.read(bytes)) > 0) {
						os1.write(bytes, 0, ir);
					} else if (ir < 0) {
						break;
					}
				} catch (InterruptedIOException e) {

				}
			}
		} catch (Exception e0) {
			// System.out.println ("Pipe异常: " + e0);
		}
	}

}
