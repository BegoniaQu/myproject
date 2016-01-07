package com.pro.cas;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {
	/*
	 * （1）字段必须是volatile类型的 保证可见性
	 * 
	 * （2）字段的描述类型（修饰符public/protected/default/private）是与调用者与操作对象字段的关系一致。
	 * 也就是说调用者能够直接操作对象字段，那么就可以反射进行原子操作。但是对于父类的字段，子类是不能直接操作的，尽管子类可以访问父类的字段。
	 * 
	 * （3）只能是实例变量，不能是类变量，也就是说不能加static关键字。
	 * 
	 * （4）只能是可修改变量，不能使final变量，因为final的语义就是不可修改。实际上final的语义和volatile是有冲突的，
	 * 这两个关键字不能同时存在。
	 * 
	 * （5）对于AtomicIntegerFieldUpdater和AtomicLongFieldUpdater只能修改int/long类型【基本数据类型
	 * 】的字段，不能修改其包装类型（Integer/Long）。
	 */
	class DemoData {
		public volatile int value1 = 1;
		volatile int value2 = 2;
		// private volatile int value4 = 4;
	}

	AtomicIntegerFieldUpdater<DemoData> getUpdater(String fieldName) {
		return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
	}

	void doit() {
		DemoData data = new DemoData();
		System.out.println("1 ==> " + getUpdater("value1").getAndSet(data, 10));
		System.out.println("3 ==> "
				+ getUpdater("value2").incrementAndGet(data));
		// System.out.println("true ==> "+getUpdater("value4").compareAndSet(data,
		// 4, 5));
	}

	public static void main(String[] args) {
		AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
		demo.doit();
	}

}
