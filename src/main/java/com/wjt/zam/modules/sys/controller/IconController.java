package com.wjt.zam.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/icon")
public class IconController {
	/**
	 * 跳转到首页
	 */
	@RequestMapping("/index")
	public String resource() {
		return "/modules/icon/index";
	}
}
