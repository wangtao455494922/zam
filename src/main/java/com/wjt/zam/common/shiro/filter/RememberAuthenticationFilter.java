package com.wjt.zam.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**  

* <p>Description:记住我功能过滤器 </p>  
* @author wjt  
* @date 2018年7月26日  
*/ 
public class RememberAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        //这个方法本来只返回 subject.isAuthenticated() 现在我们加上 subject.isRemembered() 让它同时也兼容remember这种情况
        return subject.isAuthenticated() || subject.isRemembered();
	}
	
}
