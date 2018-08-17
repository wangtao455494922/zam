package com.wjt.zam.modules.act.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.wjt.zam.common.Constant;
import com.wjt.zam.common.base.BaseController;
import com.wjt.zam.common.utils.ConvertUtils;
import com.wjt.zam.common.utils.ShiroUtils;
import com.wjt.zam.modules.act.model.Leave;
import com.wjt.zam.modules.act.service.ITaskService;

/**  

* <p>Description: 任务管理 </p>  
* @author wjt  create
* @date 2018年7月19日  

*/ 
@Controller
@RequestMapping(value="/task")
public class TaskController extends BaseController<com.wjt.zam.modules.act.model.Task>{
	
	@Autowired
	private ITaskService  taskService;
	
	@GetMapping(value="/index")
	public String index(Model model){
		return "modules/task/index";
	}
	/**  
	 * table数据初始化
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@PostMapping("/tableRender")
	@ResponseBody
	public Object resourceTableRender(com.wjt.zam.modules.act.model.Task task) throws InstantiationException, IllegalAccessException{
		List<com.wjt.zam.modules.act.model.Task> list = new ArrayList<>();
		PageHelper.startPage(task.getPageNum(),task.getPageSize());
		//查询部署对象信息，对应表（act_re_deployment）
		List<Task> actTasks = taskService.getTasksByAssignee(ShiroUtils.getUsername(),task.getName());
		ConvertUtils.actObjectToZamObject(com.wjt.zam.modules.act.model.Task.class,list,actTasks);
		return layuiTableRender(list);
	}
	
	/**  
	 * 办理任务
	 */
	@GetMapping(value="/handle")
	public String handle(String taskId,Model model){
		//根据taskId获取请假对象
		Leave leave = taskService.findLeaveByTaskId(taskId);
		//根据taskId获取连线信息
		List<String> outcomeList = taskService.findOutComeListByTaskId(taskId);
		model.addAttribute("outcomeList", outcomeList);
		model.addAttribute("leave", leave);
		return "modules/task/handle";
	}
	
	/**  
	 * 保存任务批准信息
	 */
	@PostMapping("/save")
	@ResponseBody
	public Object save(Leave leave){
		int i = taskService.save(leave);
		if (i==1) {
			return renderSuccess(leave.getOutcome()+Constant.INSERT_SUCCESS.substring(2));
		} else {
			return renderError(leave.getOutcome()+Constant.INSERT_ERROR.substring(2));
		}
	}
	
	/**  
	 * 审批记录
	 */
	@GetMapping(value="/approve")
	public String approve(String taskId,Model model){
		List<Comment> commentList = taskService.findCommentByTaskId(taskId);
		model.addAttribute("commentList", commentList);
		return "modules/task/approve";
	}
	
	/**  
	 * 查看流程图
	 */
	@GetMapping(value="/search")
	public String search(String taskId,Model model){
		taskService.search(taskId,model);
		return "modules/task/search";
	}
	/**  
	 * 查看图片
	 * @throws IOException 
	 */
	@GetMapping(value="/viewImage")
	public void viewImage(String deploymentId,String imageName,HttpServletResponse resp) throws IOException{
		taskService.viewImage(deploymentId,imageName,resp);
	}
	
}
