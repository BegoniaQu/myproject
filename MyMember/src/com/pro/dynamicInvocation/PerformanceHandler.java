package com.pro.dynamicInvocation;

import java.lang.reflect.Method;

public class PerformanceHandler implements java.lang.reflect.InvocationHandler {

	private Object target;

	public PerformanceHandler(Object target) { // ��targetΪĿ���ҵ����
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		PerformanceMonitor.begin(target.getClass().getName() + "."
				+ method.getName());
		Object obj = method.invoke(target, args);
		System.out.println(obj);
		PerformanceMonitor.end();
		return obj;
	}

}
