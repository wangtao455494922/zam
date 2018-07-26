package com.wjt.zam.modules.sys.controller;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wjt.zam.modules.sys.model.Resource;
import com.wjt.zam.modules.sys.service.ResourceService;
import com.wjt.zam.modules.sys.service.UserService;

/**  

* <p>Description:首页--控制器 </p>  
* @author wjt  
* @date 2018年6月27日  

*/ 
@Controller
public class IndexController {
	 @Autowired
	 private UserService userService;
	 @Autowired
	 private ResourceService  resourceService;

	    
	
	/**  
	 * 首页 
	 */
	@GetMapping("/index")
    public String index(Model model) {
		String username = (String)SecurityUtils.getSubject().getPrincipal();
		Set<String> permissions = userService.findPermissions(username);
	    List<Resource> resources = resourceService.findMenus(permissions);
	        
		model.addAttribute("menus", resources);
		model.addAttribute("username", username);
		return "index";
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
