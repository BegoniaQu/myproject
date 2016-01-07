package com.pro.dynamicInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class TestForumService {

	public static void main(String[] args) {
		ForumServiceImpl target = new ForumServiceImpl();

		// �ڽ�Ŀ��ҵ����ͺ��д����֯��һ��
		PerformanceHandler handler = new PerformanceHandler(target);
		ForumService proxy = (ForumService) Proxy.newProxyInstance(target
				.getClass().getClassLoader(),
				target.getClass().getInterfaces(), handler);
		proxy.removeForum(10);

		// ����2�����ڽ�ֹʹ��add����
		TestForumService service = new TestForumService();
		List<String> list = service.getList(new ArrayList<String>());
		list.add("sss");
	}

	@SuppressWarnings("unchecked")
	public List<String> getList(final List<String> list) {
		return (List<String>) Proxy.newProxyInstance(list.getClass()
				.getClassLoader(), new Class[] { List.class },
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						if ("add".equals(method.getName())) {
							throw new UnsupportedOperationException();
						} else {
							return method.invoke(list, args);
						}
					}
				});
	}
}
