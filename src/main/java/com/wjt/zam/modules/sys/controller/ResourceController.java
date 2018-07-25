package com.wjt.zam.modules.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.wjt.zam.modules.sys.model.Resource;
import com.wjt.zam.modules.sys.service.ResourceService;

/**
 * <p>
 * Description:资源--控制器
 * </p>
 * 
 * @author wjt
 * @date 2018年6月27日
 */
@Controller
public class ResourceController extends BaseController<Resource>{

	@Autowired
	private ResourceService resourceService;

	/**
	 * 跳转到资源首页
	 */
	@RequestMapping("/resource")
	public String resource() {
		return "/modules/resource/index";
	}
	
	/**  
	 * table数据初始化
	 */
	@PostMapping("/resourceTableRender")
	@ResponseBody
	public Object resourceTableRender(Resource resource){
		PageHelper.startPage(resource.getPageNum(),resource.getPageSize());
		List<Resource> resources = resourceService.getResources(resource);
		return layuiTableRender(resources);
	}
	
	
	/**   
	 * 跳转到保存资源页面  
	 */
	@GetMapping("/resourceAdd")
	public String resourceAdd(Model model){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", Constant.RESOURCE_MENU_CODE);
		List<Resource> menus = resourceService.findList(paramMap);
		model.addAttribute("menus", menus);
		return "/modules/resource/add";
	}
	
	/**  
	 * 保存资源信息
	 */
	@PostMapping("/resourceSave")
	@ResponseBody
	public Object resourceSave(Resource resource){
		int i = resourceService.insert(resource);
		if (i==1) {
			return renderSuccess(Constant.INSERT_SUCCESS);
		} else {
			return renderError(Constant.INSERT_ERROR);
		}
	}
	
	/**   
	 * 跳转到编辑资源页面  
	 */
	@GetMapping("/resourceToUpdate")
	public String resourceUpdate(String id,Model model){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", Constant.RESOURCE_MENU_CODE);
		List<Resource> menus = resourceService.findList(paramMap);
		model.addAttribute("menus", menus);
		
		Resource resource = resourceService.findById(id);
		model.addAttribute("resource", resource);
		
		model.addAttribute("types",Constant.getResourceType());
		return "/modules/resource/update";
	}
	
	
	/**  
	 * 更新资源信息
	 */
	@PostMapping("/resourceUpdate")
	@ResponseBody
	public Object resourceUpdate(Resource resource){
		int i = resourceService.update(resource);
		if (i==1) {
			return renderSuccess(Constant.UPDATE_SUCCESS);
		} else {
			return renderError(Constant.UPDATE_ERROR);
		}
	}
	
	/**  
	 * 根据id删除资源信息  
	 */
	@PostMapping("/resourceDelete")
	@ResponseBody
	public Object resourceDelete(String id){
		Resource resource = new Resource();
		resource.setId(id);
		int i = resourceService.delete(resource);
		if (i==1) {
			return renderSuccess(Constant.DELETE_SUCCESS);
		} else {
			return renderError(Constant.DELETE_ERROR);
		}
	}
	
	/**  
	 * 批量删除资源信息  
	 */
	@PostMapping("/resouceDeleteAll")
	@ResponseBody
	public Object resouceDeleteAll(String[] ids){
		int i  = resourceService.batchDelete(ids);
		if (i > 0) {
			return renderSuccess(Constant.DELETE_SUCCESS);
		} else {
			return renderError(Constant.DELETE_ERROR);
		}
	}

}
