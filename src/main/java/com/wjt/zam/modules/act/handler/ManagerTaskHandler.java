package com.wjt.zam.modules.act.handler;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.wjt.zam.common.utils.ShiroUtils;
import com.wjt.zam.modules.sys.model.User;
import com.wjt.zam.modules.sys.service.UserService;

/**
 * 员工经理任务分配
 *
 */
public class ManagerTaskHandler implements TaskListener {
	private static final long serialVersionUID = 1568053510726509985L;
	@Autowired
	private UserService userService;

	@Override
	public void notify(DelegateTask delegateTask) {
		//shiro缓存信息
		User user = userService.findByUsername(ShiroUtils.getUsername());
		//由spring上下文获取bean
		WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		UserService userService = (UserService) ac.getBean("userServiceImpl");
		
		//设置个人任务的办理人
		delegateTask.setAssignee(userService.findById(Long.parseLong(user.getManagerId())).getUsername());
	}


}
