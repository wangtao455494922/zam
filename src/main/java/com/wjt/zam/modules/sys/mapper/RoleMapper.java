package com.wjt.zam.modules.sys.mapper;

import java.util.List;

import com.wjt.zam.common.base.BaseMapper;
import com.wjt.zam.modules.sys.model.Role;

public interface RoleMapper extends BaseMapper<Role, Long>{

	/**  
	 * 根据角色roleIds获取角色列表 
	 */
	List<Role> findRoles(List<String> roleIdsList);

}
