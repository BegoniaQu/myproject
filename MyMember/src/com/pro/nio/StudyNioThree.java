package com.pro.nio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 学习ByteBuffer
 * 
 * @author Administrator
 * 
 */
public class StudyNioThree {

	public static void main(String[] args) throws UnsupportedEncodingException {

		ByteBuffer bb = ByteBuffer.allocate(10);
		System.out.println(bb.capacity());
		System.out.println(bb.position()); // 指针位置
		System.out.println(bb.limit()); // eof有效位置，目前和capacity相同

		// int pos = bb.position(); //记住初始位置
		// for(int i = 0;i < bb.limit();i++){
		// byte b = bb.get(); //读操作，指针会自动移动
		// System.out.println(Integer.toHexString(b));
		// }
		// bb.position(pos); //指针挪回到初始位置

		bb.putChar('a'); // char 二字节
		System.out.println(bb.position());
		bb.putChar('啊');
		byte[] bta = bb.array(); // 获取缓冲中的字节数组数据
		byte[] data = "你是ba".getBytes("utf-8"); // utf-8编码的文字占用三个字节，a由于是字母，则占用一个字节
		System.out.println("data.size = " + data.length);

		ByteBuffer bwrap = ByteBuffer.wrap(data); // 将数据包装到bytebuffer缓冲
		System.out.println("-----bwrap-------" + bwrap.capacity());
		System.out.println("-----bwrap-------" + bwrap.limit());
		System.out.println("-----bwrap-------" + bwrap.position());
		System.out.println(bb.position());
		System.out.println("-------" + bb.limit());
		bb.flip();
		System.out.println("-------" + bb.limit());
		ByteBuffer subBb = bb.slice();
		System.out.println("--------slice: " + subBb.getChar());

		bb.order(ByteOrder.BIG_ENDIAN); // 可以设置字节序，默认都是大端字节序，即最高字节在前

		for (int i = 0; i < bb.limit(); i++) {
			byte b = bb.get(); // 读操作，指针会自动移动
			System.out.println(Integer.toHexString(b));
		}
		System.out.println("------------------------");
		bb.position(1); // 位置为0的数据会在compact后被覆盖掉
		bb.compact(); // 后面的挤到前面，compact方法会把EOF位置重置为最大容量，这里就是10
		System.out.println(bb.limit());
		bb.rewind();
		for (int i = 0; i < bb.limit(); i++) {
			byte b = bb.get(); // 读操作，指针会自动移动
			System.out.println(Integer.toHexString(b));
		}

		String str = "中";
		byte[] bt = str.getBytes();
		byte[] btutf = str.getBytes("utf-8");
		System.out.println("经过utf-8编码的字节长度：" + btutf.length);
		System.out.println("经过默认编码的字节长度" + bt.length);

		Set<String> setStr = new HashSet<String>();
		setStr.add("我");
		setStr.add("受");
		setStr.add("够");
		Iterator<String> iterator = setStr.iterator();
		while (iterator.hasNext()) {
			String one = iterator.next();
			// iterator.remove(); //移除掉set集合中已经被迭代的数据，永久性移除
			System.out.println(one);
		}

		System.out.println(setStr.size());

	}

}
