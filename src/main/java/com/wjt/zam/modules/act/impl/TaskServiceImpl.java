package com.wjt.zam.modules.act.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wjt.zam.common.Constant;
import com.wjt.zam.common.utils.CheckUtils;
import com.wjt.zam.common.utils.ShiroUtils;
import com.wjt.zam.modules.act.model.Leave;
import com.wjt.zam.modules.act.service.ILeaveService;
import com.wjt.zam.modules.act.service.ITaskService;

@Service(value="taskServiceImpl")
@Transactional
public class TaskServiceImpl implements ITaskService {
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ILeaveService leaveService;
	
	
	@Override
	public List<Task> getTasksByAssignee(String assignee, String name) {
		List<Task> tasks=null;
		if (StringUtils.isEmpty(name)) {
			 tasks = taskService.createTaskQuery()
					.taskAssignee(assignee)//根据办理人查询
					.orderByTaskCreateTime()//时间排序
					.asc()//升序
					.list();//返回集合
		} else {
			 tasks = taskService.createTaskQuery()
					.taskAssignee(assignee)//根据办理人查询
					.orderByTaskCreateTime()//时间排序
					.taskNameLike(name)
					.asc()//升序
					.list();//返回集合
		}
		return tasks;
	}
	@Override
	public Leave findLeaveByTaskId(String taskId) {
		// 1.根据taskId查询任务对象
		Task task = taskService.createTaskQuery()// 创建查询
				.taskId(taskId)// 根据id查询
				.singleResult();// 获取唯一结果
		// 2.使用任务对象获取流程实例id
		String processInstanceId = task.getProcessInstanceId();
		// 3.根据流程实例id获取,获取正在执行的流程对象
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		// 4.获取流程对象中的BUSSINESS_KEY
		String bussinessKey = processInstance.getBusinessKey();
		String leaveId = bussinessKey.split("\\.")[1];
		Leave leave = leaveService.findById(Long.parseLong(leaveId));
		leave.setTaskId(taskId);
		return leave;
	}
	
	@Override
	public List<String> findOutComeListByTaskId(String taskId) {
		List<String> list = new ArrayList<>();
		// 1.根据taskId查询任务对象
		Task task = taskService.createTaskQuery()// 创建查询
				.taskId(taskId)// 根据id查询
				.singleResult();// 获取唯一结果
		// 2.使用任务对象获取流程定义id
		String processDefinitionId = task.getProcessDefinitionId();
		// 3.获取流程定义对象
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
		// 4.获取流程实例对象
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		//活动id 如: usertask1
		String activityId = processInstance.getActivityId();
		// 5.获取当前活动连线
		ActivityImpl activityImpl = processDefinition.findActivity(activityId);
		// 6.回去所有连线名称
		List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
		if (CheckUtils.listIsNotNull(pvmTransitions)) {
			for (PvmTransition pvmTransition : pvmTransitions) {
				String name = (String) pvmTransition.getProperty("name");
				if (StringUtils.isNotBlank(name)) {
					list.add(name);
				}else {
					list.add(Constant.DEFAULT_OUTCOME);
				}
			}
		} 
		return list;
	}
	@Override
	public int save(Leave leave) {
		
		// 获取任务ID
		String taskId = leave.getTaskId();
		// 获取连线的名称
		String outcome = leave.getOutcome();
		// 批注信息
		String message = leave.getComment();
		// 获取请假单ID
		Long id = leave.getId();

		/**
		 * 1：在完成之前，添加一个批注信息，向act_hi_comment表中添加数据，用于记录对当前申请人的一些审核信息
		 */
		// 使用任务ID，查询任务对象，获取流程流程实例ID
		Task task = taskService.createTaskQuery()//
				.taskId(taskId)// 使用任务ID查询
				.singleResult();
		// 获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		/**
		 * 注意：添加批注的时候，由于Activiti底层代码是使用： String userId =
		 * Authentication.getAuthenticatedUserId(); CommentEntity comment = new
		 * CommentEntity(); comment.setUserId(userId);
		 * 所有需要从Session中获取当前登录人，作为该任务的办理人（审核人），对应act_hi_comment表中的User_ID的字段，不过不添加审核人，该字段为null
		 * 所以要求，添加配置执行使用Authentication.setAuthenticatedUserId();添加当前任务的审核人
		 */
		Authentication.setAuthenticatedUserId(ShiroUtils.getUsername());
		taskService.addComment(taskId, processInstanceId, message);
		/**
		 * 2：如果连线的名称是“默认提交”，那么就不需要设置，如果不是，就需要设置流程变量 在完成任务之前，设置流程变量，按照连线的名称，去完成任务
		 * 流程变量的名称：outcome 流程变量的值：连线的名称
		 */
		Map<String, Object> variables = new HashMap<String, Object>();
		if (outcome != null && !outcome.equals(Constant.DEFAULT_OUTCOME)) {
			variables.put("outcome", outcome);
		}

		// 3：使用任务ID，完成当前人的个人任务，同时流程变量
		taskService.complete(taskId, variables);
		// 4：当任务完成之后，需要指定下一个任务的办理人（使用类）-----已经开发完成

		/**
		 * 5：在完成任务之后，判断流程是否结束 如果流程结束了，更新请假单表的状态从1变成2（审核中-->审核完成）
		 */
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
				.processInstanceId(processInstanceId)// 使用流程实例ID查询
				.singleResult();
		// 流程结束了
		if (null != pi) {
			leave.setState(Constant.LEAVE_STATE_ONE);
		} else {
			leave.setState(Constant.LEAVE_STATE_TWO);
		}
		return leaveService.update(leave);
	}
	@Override
	public List<Comment> findCommentByTaskId(String taskId) {
		//1.获取正在执行的任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//2.获取实例对象id
		String processInstanceId = task.getProcessInstanceId();
		//3.根据实例对象id获取批准信息
		List<Comment>  comments= taskService.getProcessInstanceComments(processInstanceId);
		return comments;
	}
	@Override
	public void search(String taskId, Model model) {
		//1：获取任务ID，获取任务对象，使用任务对象获取流程定义ID，查询流程定义对象
		Task task = taskService.createTaskQuery()//
					.taskId(taskId)//使用任务ID查询
					.singleResult();
		//获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		//查询流程定义的对象
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()//创建流程定义查询对象，对应表act_re_procdef 
					.processDefinitionId(processDefinitionId)//使用流程定义ID查询
					.singleResult();
		
		model.addAttribute("deploymentId", pd.getDeploymentId());
		model.addAttribute("imageName", pd.getDiagramResourceName());
		
		/**二：查看当前活动，获取当期活动对应的坐标x,y,width,height，将4个值存放到Map<String,Object>中*/
		Map<String, Object> map = new HashMap<String,Object>();
		//获取流程定义的实体对象（对应.bpmn文件中的数据）
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
		//使用流程实例ID，查询正在执行的执行对象表，获取当前活动对应的流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//创建流程实例查询
					.processInstanceId(task.getProcessInstanceId())//使用流程实例ID查询
					.singleResult();
		//获取当前活动的ID
		String activityId = pi.getActivityId();
		//获取当前活动对象
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);//活动ID
		//获取坐标
		map.put("x", activityImpl.getX());
		map.put("y", activityImpl.getY());
		map.put("width", activityImpl.getWidth());
		map.put("height", activityImpl.getHeight());
		model.addAttribute("acs", map);
	}
	@Override
	public void viewImage(String deploymentId, String imageName, HttpServletResponse resp) throws IOException {
		//2：获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream
		java.io.InputStream in = repositoryService.getResourceAsStream(deploymentId, imageName);
		//3：从response对象获取输出流
		ServletOutputStream out = resp.getOutputStream();
		//4：将输入流中的数据读取出来，写到输出流中
		for(int b=-1;(b=in.read())!=-1;){
			out.write(b);
		}
		out.close();
		in.close();
		
	}
}
