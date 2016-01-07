package com.pro.linq4j;

import java.util.ArrayList;
import java.util.List;

import net.hydromatic.linq4j.Linq4j;
import net.hydromatic.linq4j.function.Function1;
import net.hydromatic.linq4j.function.Predicate1;

public class Person {
	public int age;
	public String name;
	public boolean sex;

	Person(int age, String name, boolean sex) {
		this.age = age;
		this.name = name;
		this.sex = sex;
	}

	public List<String> filter(List<Person> pList) {
		List<String> nList = Linq4j.asEnumerable(pList)
				.where(new Predicate1<Person>() {

					public boolean apply(Person person) {
						return person.sex;
					}

				}).select(new Function1<Person, String>() {

					public String apply(Person person) {
						return person.name;
					}

				}).orderByDescending(new Function1<String, String>() {

					public String apply(String arg0) {
						// TODO Auto-generated method stub
						return arg0;
					}

				}).toList();
		return nList;
	}

	public static void main(String[] args) {
		List<Person> pList = new ArrayList<Person>();
		pList.add(new Person(20, "yanglin", false));
		pList.add(new Person(21, "xiaoweizhu", true));
		pList.add(new Person(20, "liusong", true));
		pList.add(new Person(23, "quyang", true));
		pList.add(new Person(24, "wangruilei", true));
		pList.add(new Person(21, "guoyunlong", true));
		// http://www.bitscn.com/pdb/java/201308/194529.html
		String[] blacklist = new String[] { "xiaoweizhu", "wangruilei" };
	}
}
