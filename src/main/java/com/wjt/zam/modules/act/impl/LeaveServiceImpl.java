package com.wjt.zam.modules.act.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjt.zam.common.Constant;
import com.wjt.zam.common.base.BaseMapper;
import com.wjt.zam.common.base.BaseServiceImpl;
import com.wjt.zam.common.utils.ShiroUtils;
import com.wjt.zam.modules.act.mapper.LeaveMapper;
import com.wjt.zam.modules.act.model.Leave;
import com.wjt.zam.modules.act.service.ILeaveService;

@Service(value="leaveServiceImpl")
@Transactional
public class LeaveServiceImpl extends BaseServiceImpl<Leave, Long> implements ILeaveService {
	@Autowired
	private LeaveMapper  leaveMapper;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private TaskService taskService;

	@Override
	public BaseMapper<Leave, Long> getBaseMapper() {
		return leaveMapper;
	}

	@Override
	public ProcessInstance saveApply(Long id) {
		//更新请假状态信息
		Leave leave = new Leave();
		leave.setId(id);
		leave.setState(Constant.LEAVE_STATE_ZERO);
		leaveMapper.update(leave);
		
		//启动流程
		Map<String, Object> variables = new HashMap<>();
		variables.put("currentUser", ShiroUtils.getCurrentUser().getUser().getUsername());
		
		String processDefinitionKey = leave.getClass().getSimpleName();
		String businessKey = processDefinitionKey+"."+id;
		return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
	}

	@Override
	public List<Comment> approve(Long leaveId) {
		//使用请假单id获取请假单对象
		Leave leave = leaveMapper.findById(leaveId);
		//拼接BUSSINESS_KEY
		String bussinessKey = leave.getClass().getSimpleName()+"."+leaveId;
		//根据BUSSINESS_KEY查询历史的流程实例对象
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(bussinessKey).singleResult();
		//获取流程实例id
		String processInstanceId = historicProcessInstance.getId();
		
		List<Comment> comment = taskService.getProcessInstanceComments(processInstanceId);
		return comment;
	}


}
