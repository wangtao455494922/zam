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
import com.wjt.zam.modules.sys.model.Role;
import com.wjt.zam.modules.sys.service.RoleService;

/**  
* <p>Description: 角色--控制器</p>  
* @author wjt  add
* @date 2018年8月1日  
*/ 
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<Role> {
	@Autowired
	private RoleService roleService;
	/**
	 * 跳转到首页
	 */
	@RequestMapping("/index")
	public String resource() {
		return "/modules/role/index";
	}
	
	/**  
	 * table数据初始化
	 */
	@PostMapping("/tableRender")
	@ResponseBody
	public Object tableRender(Role role){
		PageHelper.startPage(role.getPageNum(),role.getPageSize());
		List<Role> roles = roleService.findList(role);
		return layuiTableRender(roles);
	}
	
	/**   
	 * 跳转到添加页面
	 */
	@GetMapping("/add")
	public String resourceAdd(Model model){
		return "/modules/role/add";
	}

	
	/**  
	 * 保存请假信息
	 */
	@PostMapping("/save")
	@ResponseBody
	public Object save(Role role){
		int i = roleService.insert(role);
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
	public Object resourceDelete(Role role){
		int i = roleService.delete(role);
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
		int i = roleService.batchDelete(ids);
		if (i > 0) {
			return renderSuccess(Constant.DELETE_SUCCESS);
		} else {
			return renderSuccess(Constant.DELETE_ERROR);
		}
	}
	
	/**   
	 * 跳转到编辑页面  
	 */
	@GetMapping("/toUpdate")
	public String toUpdate(Long id,Model model){
		Role role = roleService.findById(id);
		model.addAttribute("role", role);
		return "/modules/role/update";
	}
	
	/**  
	 * 更新
	 */
	@PostMapping("/update")
	@ResponseBody
	public Object resourceUpdate(Role role){
		int i = roleService.update(role);
		if (i==1) {
			return renderSuccess(Constant.UPDATE_SUCCESS);
		} else {
			return renderError(Constant.UPDATE_ERROR);
		}
	}
	
	/**   
	 * 跳转到资源编辑页面  
	 */
	@GetMapping("/toResource")
	public String toResource(Long id,Model model){
		model.addAttribute("id",id);
		return "/modules/role/resource";
	}
	
	/**  
	 * 更新资源
	 */
	@PostMapping("/updateResource")
	@ResponseBody
	public Object updateResource(Role role){
		int i = roleService.update(role);
		if (i==1) {
			return renderSuccess(Constant.UPDATE_SUCCESS);
		} else {
			return renderError(Constant.UPDATE_ERROR);
		}
	}
}
