package com.pro.json;

import net.sf.json.JSONObject;

public class TestJson {

	public static void main(String[] args) {
		String json = "{\"name\":\"andrew\"}";
		JSONObject jsonOj = JSONObject.fromObject(json);
		// JSONObject jsonOj=new JSONObject();
		jsonOj.put("age", "23");
		String name = jsonOj.getString("name");
		int age = jsonOj.getInt("age");
		System.out.println("## name =" + name + ",age =" + age);
		String[] arr = new String[] { "JavaScript", "Java", "JVM" };
		jsonOj.put("type", arr);

		// { name:"andrew",
		// age:"23",
		// type:{"JavaScript", "Java", "JVM"};
		// }
		//

		StringBuilder sb = new StringBuilder();
		sb.append("dddd");
		sb.append("aaaa");
		sb.append("cccc");
		sb.delete(0, sb.length());
		System.out.println(sb.toString().length());

	}
}
