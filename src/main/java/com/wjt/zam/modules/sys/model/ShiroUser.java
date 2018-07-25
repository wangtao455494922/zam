package com.wjt.zam.modules.sys.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**  

* <p>Description:shiro缓存对象,shiro认证时设置该信息 </p>  
* @author wjt  
* @date 2018年6月27日  

*/ 
public class ShiroUser implements Serializable{
	private static final long serialVersionUID = -632869852848725033L;
	
	User user;//用户信息
	Set<String> roles;//角色  如:admin 
	Set<String> permissions ; //权限 如:admin:index
	List<Resource> resources; //符合权限角色的资源信息
	
	public ShiroUser(User user, Set<String> roles, Set<String> permissions, List<Resource> resources) {
		super();
		this.user = user;
		this.roles = roles;
		this.permissions = permissions;
		this.resources = resources;
	}
	
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public Set<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}
	
	
}
