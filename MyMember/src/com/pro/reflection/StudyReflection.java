package com.pro.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class StudyReflection {

	public String username = "andrew";

	public void say() {
		System.out.println("ddd");
	}

	public static void main(String[] args) {
		StudyReflection ref = new StudyReflection();
		System.out.println(ref.getClass().getName()); // com.pro.extension.StudyReflection

		System.out.println(getProperty(ref, "username"));

		try {
			Class<?> class1 = Class
					.forName("com.pro.reflection.StudyReflection");
			StudyReflection one = (StudyReflection) class1.newInstance();
			one.say();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getProperty(Object owner, String fileName) {
		Class<?> ownerClass = owner.getClass();
		try {
			Field field = ownerClass.getField(fileName); // if username is
															// private ,current
															// operation can't
															// get the property
			// System.out.println(field.getName());
			Type type = field.getGenericType();
			System.out.println(type.toString());
			return field.get(owner);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
