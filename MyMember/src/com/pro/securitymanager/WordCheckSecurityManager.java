package com.pro.securitymanager;

import java.io.FilePermission;
import java.security.Permission;

public class WordCheckSecurityManager extends SecurityManager {

	@Override
	public void checkPermission(Permission perm) {
		if (perm instanceof FilePermission && perm.getActions().equals("read")) {
			// containBadWords(perm.getName())
		} else {
			super.checkPermission(perm);
		}

	}
}
