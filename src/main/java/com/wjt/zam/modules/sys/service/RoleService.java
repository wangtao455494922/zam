package com.wjt.zam.modules.sys.service;

import java.util.List;
import java.util.Set;

import com.wjt.zam.common.base.BaseService;
import com.wjt.zam.modules.sys.model.Role;

/**
 * @Description:角色-业务层
 * @author: wangjintao
 * @date: 2018年6月24日 上午11:26:13
 */
public interface RoleService extends BaseService<Role, String>{

	/**
	 * 根据根据角色编号获取角色列表
	 */
	Set<String> findRoles(List<String> roleIdsList);

	/**
	 * 根据角色编号得到权限字符串列表
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<String> findPermissions(List<String> roleIdsList);
}
