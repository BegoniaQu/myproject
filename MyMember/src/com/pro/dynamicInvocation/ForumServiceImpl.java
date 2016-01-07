package com.pro.dynamicInvocation;

public class ForumServiceImpl implements ForumService {

	@SuppressWarnings("static-access")
	public String removeForum(int forumId) {
		System.out.println("ģ��ɾ��Forum��¼:" + forumId);
		try {
			Thread.currentThread().sleep(40);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "remove";
	}

	@SuppressWarnings("static-access")
	public void removeTopic(int topicId) {
		System.out.println("ģ��ɾ��Topic��¼:" + topicId);
		try {
			Thread.currentThread().sleep(20);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
