package com.pro.securitymanager;

import java.security.Permission;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class BadWordPermit extends Permission {

	private String actions;

	public BadWordPermit(String target, String action) {
		super(target);
		actions = action;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BadWordPermit bp = (BadWordPermit) obj;
		if (!actions.equals(bp.actions)) {
			return false;
		}
		if (actions.equals("insert")) {
			return getName().equals(bp.getName());
		}
		if (actions.equals("avoid")) {
			return badWordSet().equals(bp.badWordSet());
		}
		return false;
	}

	public Set<String> badWordSet() {
		Set<String> set = new HashSet<String>();
		StringTokenizer tokenizer = new StringTokenizer(getName(), ",");
		while (tokenizer.hasMoreTokens()) {
			set.add(tokenizer.nextToken());
		}
		return set;
	}

	public String getActions() {

		return actions;
	}

	public int hashCode() {
		return getName().hashCode() + actions.hashCode();
	}

	public boolean implies(Permission permission) {
		if (!(permission instanceof BadWordPermit)) {
			return false;
		}
		BadWordPermit bp = (BadWordPermit) permission;
		if (actions.equals("insert")) {
			return bp.actions.equals("insert")
					&& getName().indexOf(bp.getName()) > 0;
		} else if (actions.equals("avoid")) {
			if (bp.actions.equals("avoid")) {
				return bp.badWordSet().containsAll(badWordSet());
			} else if (bp.actions.equals("insert")) {
				Iterator<String> iter = badWordSet().iterator();
				while (iter.hasNext()) {
					if (bp.getName().indexOf(iter.next()) >= 0) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
