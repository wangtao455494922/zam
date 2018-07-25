package com.wjt.zam.modules.sys.controller;

import java.util.List;

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
import com.wjt.zam.common.utils.PasswordHelper;
import com.wjt.zam.modules.sys.model.Role;
import com.wjt.zam.modules.sys.model.User;
import com.wjt.zam.modules.sys.service.RoleService;
import com.wjt.zam.modules.sys.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	/**
	 * 跳转到首页
	 */
	@RequestMapping("/index")
	public String resource() {
		return "/modules/user/index";
	}
	
	/**  
	 * table数据初始化
	 */
	@PostMapping("/tableRender")
	@ResponseBody
	public Object resourceTableRender(User user){
		PageHelper.startPage(user.getPageNum(),user.getPageSize());
		List<User> users = userService.findList(user);
		return layuiTableRender(users);
	}
	
	/**   
	 * 跳转到添加页面
	 */
	@GetMapping("/add")
	public String resourceAdd(Model model){
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "/modules/user/add";
	}
	
	/**  
	 * 保存请假信息
	 */
	@PostMapping("/save")
	@ResponseBody
	public Object resourceSave(User user){
		PasswordHelper.encryptPassword(user);//加密
		int i = userService.insert(user);
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
	public Object resourceDelete(User user){
		int i = userService.delete(user);
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
		int i = userService.batchDelete(ids);
		if (i > 0) {
			return renderSuccess(Constant.DELETE_SUCCESS);
		} else {
			return renderSuccess(Constant.DELETE_ERROR);
		}
	}
	
	/**   
	 * 跳转到编辑资源页面  
	 *//*
	@GetMapping("/toUpdate")
	public String toUpdate(Long id,Model model){
		Leave leave = leaveService.findById(id);
		model.addAttribute("leave", leave);
		return "/modules/leave/update";
	}*/
	
	/**  
	 * 更新
	 *//*
	@PostMapping("/update")
	@ResponseBody
	public Object resourceUpdate(Leave Leave){
		int i = leaveService.update(Leave);
		if (i==1) {
			return renderSuccess(Constant.UPDATE_SUCCESS);
		} else {
			return renderError(Constant.UPDATE_ERROR);
		}
	}*/
	

}
