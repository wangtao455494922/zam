package com.wjt.zam.modules.act.controller;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.wjt.zam.common.Constant;
import com.wjt.zam.common.base.BaseController;
import com.wjt.zam.common.utils.ConvertUtils;
import com.wjt.zam.modules.act.service.IDeployService;



@Controller
@RequestMapping(value="/deployment")
public class DeploymentController extends BaseController<com.wjt.zam.modules.act.model.Deployment> {
	@Autowired
	private IDeployService  deployService;
	
	@GetMapping(value="/index")
	public String index(Model model){
		return "modules/deployment/index";
	}
	
	/**  
	 * table数据初始化
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@PostMapping("/tableRender")
	@ResponseBody
	public Object resourceTableRender(com.wjt.zam.modules.act.model.Deployment deployment) throws InstantiationException, IllegalAccessException{
		List<com.wjt.zam.modules.act.model.Deployment> list = new ArrayList<>();
		PageHelper.startPage(deployment.getPageNum(),deployment.getPageSize());
		//查询部署对象信息，对应表（act_re_deployment）
		List<Deployment> deployments = deployService.getDeployments(deployment);
		ConvertUtils.actObjectToZamObject(com.wjt.zam.modules.act.model.Deployment.class,list,deployments);
		return layuiTableRender(list);
	}
	
	/**  
	 * 开始流程部署
	 */
	@PostMapping("/startDeploy")
	@ResponseBody
	public Object startDeploy(@RequestParam(value="file")MultipartFile file){
		Deployment deployment = deployService.startDeploy(file);
		if (null!=deployment) {
			return renderSuccess(Constant.DEPLOY_SUEECESS);
		} else {
			return renderError(Constant.DEPLOY_ERROR);
		}
	}
	
	/**  
	 * 根据部署id删除部署信息  
	 */
	@PostMapping("/deleteById")
	@ResponseBody
	public Object deleteById(String id) {
		String resultCode = deployService.deleteById(id);
		if (Constant.SUCCESS_CODE.equals(resultCode)) {
			return renderSuccess(Constant.DELETE_SUCCESS);
		} else {
			return renderSuccess(Constant.DELETE_ERROR);
		}
	}
	
	/**
	 * 批量删除部署信息
	 */
	@PostMapping("/batchDelete")
	@ResponseBody
	public Object batchDelete(String[] ids) {
		String resultCode = deployService.batchDelete(ids);
		if (Constant.SUCCESS_CODE.equals(resultCode)) {
			return renderSuccess(Constant.DELETE_SUCCESS);
		} else {
			return renderSuccess(Constant.DELETE_ERROR);
		}
	}
}
