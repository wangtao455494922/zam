package com.wjt.zam.modules.act.controller;


import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.apache.commons.lang3.StringUtils;
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
import com.wjt.zam.common.utils.ShiroUtils;
import com.wjt.zam.modules.act.model.Leave;
import com.wjt.zam.modules.act.service.ILeaveService;
import com.wjt.zam.modules.sys.service.UserService;
/**  

* <p>Description: 请假管理    控制层</p>  
* @author wjt  
* @date 2018年7月11日  
*/ 

@Controller
@RequestMapping(value="/leave")
public class LeaveController extends BaseController<Leave> {
	
	@Autowired
	private ILeaveService leaveService;
	@Autowired
	private UserService userService;

	/**
	 * 跳转到请假管理首页
	 */
	@RequestMapping("/index")
	public String resource() {
		return "/modules/leave/index";
	}
	
	/**  
	 * table数据初始化
	 */
	@PostMapping("/tableRender")
	@ResponseBody
	public Object resourceTableRender(Leave leave){
		PageHelper.startPage(leave.getPageNum(),leave.getPageSize());
		if (StringUtils.isNotBlank(leave.getLeaveTimeFromTo())) {
			leave.setLeaveTimeFrom(leave.getLeaveTimeFromTo().substring(0,7));
			leave.setLeaveTimeTo(leave.getLeaveTimeFromTo().substring(leave.getLeaveTimeFromTo().length()-7));
		}
		List<Leave> leaves = leaveService.findList(leave);
		return layuiTableRender(leaves);
	}
	
	/**   
	 * 跳转到添加页面
	 */
	@GetMapping("/add")
	public String resourceAdd(){
		return "/modules/leave/add";
	}
	
	/**  
	 * 保存请假信息
	 */
	@PostMapping("/save")
	@ResponseBody
	public Object resourceSave(Leave leave){
		leave.setUserid(userService.findByUsername(ShiroUtils.getUsername()).getId());
		int i = leaveService.insert(leave);
		if (i==1) {
			return renderSuccess(Constant.INSERT_SUCCESS);
		} else {
			return renderError(Constant.INSERT_ERROR);
		}
	}
	
	/**  
	 * 根据id删除请假信息  
	 */
	@PostMapping("/delete")
	@ResponseBody
	public Object resourceDelete(Leave leave){
		int i = leaveService.delete(leave);
		if (i==1) {
			return renderSuccess(Constant.DELETE_SUCCESS);
		} else {
			return renderError(Constant.DELETE_ERROR);
		}
	}
	
	/**
	 * 批量删除
	 */
	@PostMapping("/batchDelete")
	@ResponseBody
	public Object batchDelete(Long[] ids) {
		int i = leaveService.batchDelete(ids);
		if (i > 0) {
			return renderSuccess(Constant.DELETE_SUCCESS);
		} else {
			return renderSuccess(Constant.DELETE_ERROR);
		}
	}
	
	/**   
	 * 跳转到编辑资源页面  
	 */
	@GetMapping("/toUpdate")
	public String toUpdate(Long id,Model model){
		Leave leave = leaveService.findById(id);
		model.addAttribute("leave", leave);
		return "/modules/leave/update";
	}
	
	/**  
	 * 更新
	 */
	@PostMapping("/update")
	@ResponseBody
	public Object resourceUpdate(Leave Leave){
		int i = leaveService.update(Leave);
		if (i==1) {
			return renderSuccess(Constant.UPDATE_SUCCESS);
		} else {
			return renderError(Constant.UPDATE_ERROR);
		}
	}
	
	/**  
	 * 申请请假
	 */
	@PostMapping("/apply")
	@ResponseBody
	public Object apply(Long id){
		ProcessInstance processInstance = leaveService.saveApply(id);
		if (null != processInstance) {
			return renderSuccess(Constant.UPDATE_SUCCESS);
		} else {
			return renderError(Constant.UPDATE_ERROR);
		}
	}
	
	/**  
	 * 审批记录
	 */
	@GetMapping(value="/approve")
	public String approve(Long leaveId,Model model){
		List<Comment> commentList = leaveService.approve(leaveId);
		model.addAttribute("commentList", commentList);
		return "modules/task/approve";
	}
}
