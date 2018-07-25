package com.wjt.zam.modules.act.model;
import java.util.Date;

import com.github.pagehelper.PageInfo;

public class Deployment extends PageInfo<Deployment> {

	private static final long serialVersionUID = -288000338978077210L;
	
	private String id;//部署id
	private String name;//部署名称
	private Date deploymentTime;//部署时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDeploymentTime() {
		return deploymentTime;
	}
	public void setDeploymentTime(Date deploymentTime) {
		this.deploymentTime = deploymentTime;
	}
	
	
}
