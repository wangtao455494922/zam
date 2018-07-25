package com.wjt.zam.modules.act.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wjt.zam.common.Constant;
import com.wjt.zam.modules.act.service.IDeployService;

@Service("deplyServiceImpl")
@Transactional
public class DeployServiceImpl implements IDeployService {
	@Autowired
	private RepositoryService repositoryService;

	@Override
	public List<Deployment> getDeployments(com.wjt.zam.modules.act.model.Deployment deployment) {
		List<Deployment> list ;
		if (StringUtils.isNotBlank(deployment.getName())) {
			 list = repositoryService.createDeploymentQuery()//创建部署对象查询
			.deploymentNameLike(deployment.getName())//根据名称查询
			.orderByDeploymenTime().asc()//按照时间升序查询
			.list();//返回集合
		}else{
			 list = repositoryService.createDeploymentQuery()//创建部署对象查询
			.orderByDeploymenTime().asc()//按照时间升序查询
			.list();//返回集合
		}
		return list;
	}

	@Override
	public List<ProcessDefinition> findProcessDefinitionList() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()//创建流程定义查询
		.orderByProcessDefinitionVersion().asc()//按照流程定义版本升序
		.list();//返回集合
		return list;
	}

	@Override
	public Deployment startDeploy(MultipartFile file) {
		try {
			 InputStream inputStream = file.getInputStream();
			 ZipInputStream zipInputStream = new ZipInputStream(inputStream);
			 Deployment deployment = repositoryService.createDeployment()//创建部署对象
			.name(file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf(".")))//设置名称
			.addZipInputStream(zipInputStream)//zip文件夹部署
			.deploy();//完成部署
			 return deployment;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String deleteById(String id) {
		 try {
			repositoryService.deleteDeployment(id, true);
			return Constant.SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.ERROR_CODE;
		}
	}

	@Override
	public String batchDelete(String[] ids) {
		try {
			for (String id : ids) {
				repositoryService.deleteDeployment(id,true);
			}
			return Constant.SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.ERROR_CODE;
		}
		
	}
	
}
