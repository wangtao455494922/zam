package com.wjt.zam.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.wjt.zam.modules.sys.model.ShiroUser;

public class ShiroUtils {
	
	public static ShiroUser getCurrentUser() {
		ShiroUser shiroUser = null;
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()||subject.isRemembered()) {
			 shiroUser = (ShiroUser) subject.getPrincipal();
		} 
		return shiroUser;
	}
}
