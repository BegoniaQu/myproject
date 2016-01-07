package com.pro.dynamicInvocation;

public class MethodPerformance {

	private long begin;
	private long end;
	private String serviceMethod;

	public MethodPerformance(String serviceMethod) {
		this.serviceMethod = serviceMethod;

		// �ټ�¼Ŀ���෽����ʼִ�е��ϵͳʱ��
		this.begin = System.currentTimeMillis();

	}

	public void printPerformance() {

		// �ڻ�ȡĿ���෽��ִ����ɺ��ϵͳʱ�䣬���������Ŀ���෽��ִ��ʱ��
		end = System.currentTimeMillis();
		long elapse = end - begin;

		// �۱���Ŀ���෽����ִ��ʱ��
		System.out.println(serviceMethod + "����" + elapse + "���롣");
	}
}
