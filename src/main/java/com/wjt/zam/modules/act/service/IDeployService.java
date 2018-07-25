package com.wjt.zam.modules.act.service;

import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.web.multipart.MultipartFile;

public interface IDeployService {

	/**  
	 * 获取部署信息
	 * @param deployment 
	 */
	List<Deployment> getDeployments(com.wjt.zam.modules.act.model.Deployment deployment);

	/**  
	 * 获取流程定义信息
	 */
	List<ProcessDefinition> findProcessDefinitionList();
	
	/**  
	 * 开始部署
	 */
	Deployment startDeploy(MultipartFile file);
	
	/**  
	 * 根据部署id删除部署信息
	 */
	String deleteById(String id);
	
	/**  
	 * 批量删除部署信息
	 */
	String batchDelete(String[] ids);

}
