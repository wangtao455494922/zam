package com.wjt.zam.modules.act.model;

import java.util.Date;

import com.github.pagehelper.PageInfo;

public class Task extends PageInfo<Task>{

	private static final long serialVersionUID = -4446882387726603793L;
	
	private String id;//任务id
	private String name;//任务名称
	private Date createTime;//创建时间
	private String assignee;//办理人
	
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	

}
