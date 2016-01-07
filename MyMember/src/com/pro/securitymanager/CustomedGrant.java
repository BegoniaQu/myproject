package com.pro.securitymanager;

public class CustomedGrant {

	/*
	 * 编译后：在src下 java -Djava.security.policy=../permissiontest.policy
	 * com.pro.securitymanager.CustomedGrant
	 */
	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		CustomedGrant grant = new CustomedGrant();
		grant.append("fuck");

		System.out.println(sb.toString());
	}

	public void append(String text) {
		BadWordPermit bp = new BadWordPermit(text, "insert");
		System.getSecurityManager().checkPermission(bp);
		sb.append(text);
	}

	private static StringBuilder sb = new StringBuilder();
}
