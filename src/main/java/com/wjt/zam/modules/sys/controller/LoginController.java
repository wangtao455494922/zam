package com.wjt.zam.modules.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjt.zam.common.base.BaseController;

/**
 * 
 * 登录Controller
 * 
 * @author wjt
 * @param <T>
 * @date 2018年6月21日
 * 
 */
@Controller
public class LoginController extends BaseController<String>{
	/**  
	 * 系统默认登录页面(get)
	 */
	@GetMapping("/login")
	public String login() {
		boolean login = SecurityUtils.getSubject().isAuthenticated();
		boolean rememeber = SecurityUtils.getSubject().isRemembered();
		if (login||rememeber) {
			return "redirect:/index";
		}
		return "login";
	}

	/**
	 * 页面Ajax请求登录(post)
	 */
	@PostMapping("/login")
	@ResponseBody
	public Object login(String username, String password,Boolean rememberMe, Model model) {
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
			subject.login(token);
			return renderSuccess("登录成功");
		} catch (LockedAccountException e) {
			return renderError("帐号被锁定");
		} catch (UnknownAccountException e) {
			return renderError("用户名/密码错误");
		} catch (ExpiredCredentialsException e) {
			return renderError("帐号过期");
		} catch (IncorrectCredentialsException e) {
			return renderError("用户名/密码错误");
		} catch (NullPointerException e) {
			return renderError("空指针异常");
		}
	}

	/**  
	 * 登出
	 */
	@PostMapping("/logout")
	@ResponseBody
	public boolean logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return true;
	}
}
