package com.wjt.zam.modules.sys.model;

import org.apache.ibatis.type.Alias;

import com.github.pagehelper.PageInfo;

/**
 * <p>
 * Description:用户-实体
 * </p>
 * 
 * @author wjt
 * @date 2018年6月25日
 */
@Alias(value="User")
public class User extends PageInfo<User> {
	private static final long serialVersionUID = 2155968235214278821L;

	private Long id; // 编号
	private String organizationId; // 所属公司
	private String username; // 用户名
	private String password; // 密码
	private String salt; // 加密密码的盐
	private String roleIdsStr;// 拥有的角色字符串
	private String locked;//是否锁住
	private String managerId;//是否锁住
	private String organizationName;//所属机构名称
	private String managerName;//上级管理者
	
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getRoleIdsStr() {
		return roleIdsStr;
	}

	public void setRoleIdsStr(String roleIdsStr) {
		this.roleIdsStr = roleIdsStr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getCredentialsSalt() {
		return username + salt;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		if (id != null ? !id.equals(user.id) : user.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
