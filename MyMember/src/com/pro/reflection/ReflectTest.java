package com.pro.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectTest {

	public static void main(String[] args) {

		// Car car=(Car)initByHasParamCons("com.pro.extension.Car");
		Car car = (Car) initByDefCons("com.pro.extension.Car");
		car.introduce();
	}

	public static Object initByHasParamCons(String className) {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Class<?> carClass = loader.loadClass(className);
			Class<?>[] params = new Class[] { String.class, String.class,
					int.class };
			Constructor<?> cons = carClass.getDeclaredConstructor(params);
			Object[] par = new Object[] { "Porsche", "red", 230 };
			Car car = (Car) cons.newInstance(par); // ͨ���������һ��ʵ��
			return car;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object initByDefCons(String className) {
		// ͨ�������������Ĭ�Ϲ��캯���ȡʵ��
		// ExtClassLoader��AppClassLoader����ClassLoader������
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println("��ǰװ������" + loader); // ϵͳ��װ������AppClassLoader
		System.out.println("����װ������" + loader.getParent()); // ��չ��װ������ExtClassLoader
		System.out.println("үүװ������" + loader.getParent().getParent());// ��װ��������ClassLoader�����࣬��ʹ��C++��д�����������Java�п�������
		try {
			Class<?> carClass = loader.loadClass(className);

			Class<?>[] params = null;
			Constructor<?> cons = carClass.getDeclaredConstructor(params);
			Car car = (Car) cons.newInstance();

			// �������ʱ˽�еģ�Ҳ���Է��ʵ�
			Field color = carClass.getDeclaredField("color");
			color.setAccessible(true);
			color.set(car, "blue");
			// ��ʼ������
			// Method setBrand=carClass.getMethod("setBrand", String.class);
			// setBrand.invoke(car, "Porsche");
			// Method setColor=carClass.getMethod("setColor", String.class);
			// setColor.invoke(car, "Red");
			// Method setMaxSpeed=carClass.getMethod("setMaxSpeed", int.class);
			// setMaxSpeed.invoke(car,250);
			return car;

		} catch (Exception e) {
			return null;
		}
	}

}
