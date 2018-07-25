package com.wjt.zam.modules.act.service;


import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;

import com.wjt.zam.common.base.BaseService;
import com.wjt.zam.modules.act.model.Leave;

/**  

* <p>Description: 请假管理  业务层</p>  
* @author wjt  
* @date 2018年7月11日  

*/ 
public interface ILeaveService extends BaseService<Leave, Long> {

	/**  
	 * 申请请假
	 */
	ProcessInstance saveApply(Long id);

	/**  
	 * 根据请假信息id查询审批记录
	 */
	List<Comment> approve(Long leaveId);

}
