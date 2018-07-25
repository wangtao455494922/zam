package com.wjt.zam.modules.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wjt.zam.modules.sys.model.ShiroUser;

/**  

* <p>Description:首页--控制器 </p>  
* @author wjt  
* @date 2018年6月27日  

*/ 
@Controller
public class IndexController {
	
	/**  
	 * 首页 
	 */
	@GetMapping("/index")
    public String index(Model model) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
			model.addAttribute("menus", shiroUser.getResources());
			model.addAttribute("username", shiroUser.getUser().getUsername());
			return "index";
		} else {
			return "login";
		}
    }
	/**  
	 * 首页 
	 */
	@GetMapping("/")
    public String index() {
        return "redirect:/index";
    }
	
	/**  
	 * 欢迎界面 
	 */
	@GetMapping("/welcome")
	public String welcome(){
		return "welcome";
	}
	
}
