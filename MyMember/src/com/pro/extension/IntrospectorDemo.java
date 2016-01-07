package com.pro.extension;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

public class IntrospectorDemo {

	String name;
	String height;

	public static void main(String[] args) throws Exception {
		IntrospectorDemo demo = new IntrospectorDemo();
		demo.setName("Winter Lau");
		demo.setHeight(null);
		// 如果不想把父类的属性也列出来的话，
		// 那 getBeanInfo 的第二个参数填写父类的信息
		BeanInfo bi = Introspector.getBeanInfo(demo.getClass(), Object.class);
		PropertyDescriptor[] props = bi.getPropertyDescriptors();
		for (int i = 0; i < props.length; i++) {
			// System.out.println(props[i].getName() + "="
			// + props[i].getReadMethod().invoke(demo,null));
			// System.out.println(props[i].getReadMethod().getName());
			System.out.println(props[i].getWriteMethod().getName());
			props[i].getWriteMethod().invoke(demo, "11");
			System.out.println(props[i].getReadMethod().invoke(demo, null));
		}
		// MethodDescriptor [] md = bi.getMethodDescriptors();
		// for(MethodDescriptor myOBj : md){
		// System.out.println(myOBj.getMethod().);
		// }
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
