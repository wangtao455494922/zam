package com.wjt.zam.modules.sys.model;

import org.apache.ibatis.type.Alias;

import com.github.pagehelper.PageInfo;

/**     
 * @Description:角色实体
 * @author: wangjintao 
 * @date:   2018年6月24日      上午11:15:54   
 */  
@Alias(value="Role")
public class Role extends PageInfo<Role>{
	private static final long serialVersionUID = -4971388004142008096L;
	private String id; // 编号
	private String role; // 角色标识 程序中判断使用,如"admin"
	private String description; // 角色描述,UI界面显示使用
	private String resourceIdsStr; // 拥有的资源
	private String available; // 是否可用,如果不可用将不会添加给用户
	
	public String getResourceIdsStr() {
		return resourceIdsStr;
	}

	public void setResourceIdsStr(String resourceIdsStr) {
		this.resourceIdsStr = resourceIdsStr;
	}

	public String getAvailable() {
		return available;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Role role = (Role) o;

		if (id != null ? !id.equals(role.id) : role.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", role='" + role + '\''
				+ ", description='" + description + '\'' + ", resourceIds="
				+ resourceIdsStr + ", available=" + available + '}';
	}
}
