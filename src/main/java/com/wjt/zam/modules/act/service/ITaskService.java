package com.wjt.zam.modules.act.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.ui.Model;

import com.wjt.zam.modules.act.model.Leave;

public interface ITaskService {

	/**  
	 * 获取登录用户需要办理任务  
	 * @param name 
	 * @param pageSize 
	 * @param pageNum 
	 */
	List<Task> getTasksByAssignee(String assignee, String name);

	/**  
	 * 根据taskId查询leave信息
	 */
	Leave findLeaveByTaskId(String taskId);
	/**  
	 * 根据taskId获取连线信息
	 */
	List<String> findOutComeListByTaskId(String taskId);
	/**  
	 * 保存任务信息
	 */
	int save(Leave leave);
	/**  
	 * 根据任务Id查询批注信息
	 */
	List<Comment> findCommentByTaskId(String taskId);
	/**  
	 * 查看流程图
	 */
	void search(String taskId, Model model);
	/**  
	 * 查看图片
	 * @throws IOException 
	 */
	void viewImage(String deploymentId, String imageName, HttpServletResponse resp) throws IOException;
	
}
