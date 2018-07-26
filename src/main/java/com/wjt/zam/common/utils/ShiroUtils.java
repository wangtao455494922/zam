package com.wjt.zam.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {
	
	public static String getUsername() {
		String username = null;
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()||subject.isRemembered()) {
			username = (String)subject.getPrincipal();
		} 
		return username;
	}
}
