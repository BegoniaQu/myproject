package com.pro.reflection.one;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MyTest {

	public static void main(String[] args) throws Exception, SecurityException {

		Constructor<MyClass> constructor = MyClass.class
				.getConstructor(int.class);

		MyClass myClass = constructor.newInstance(10);

		Method method = MyClass.class.getMethod("increase", int.class);

		method.invoke(myClass, 10);

		Field field = MyClass.class.getField("count");
		int result = field.getInt(myClass);
		System.out.println(result);

		// 泛型
		Field listField = MyClass.class.getDeclaredField("list");
		Type myType = listField.getGenericType();
		if (myType instanceof ParameterizedType) {
			Type[] arr = ((ParameterizedType) myType).getActualTypeArguments();
			for (Type one : arr) {
				if (one instanceof Class) {
					Class oneClass = (Class) one;
					System.out.println(oneClass.getName());
				}
			}
		}
	}
}
