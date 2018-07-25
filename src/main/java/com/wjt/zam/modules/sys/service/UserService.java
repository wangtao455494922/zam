package com.wjt.zam.modules.sys.service;

import java.util.Set;

import com.wjt.zam.common.base.BaseService;
import com.wjt.zam.modules.sys.model.User;

/**
 * @Description:用户-业务层
 * @author: wangjintao
 * @date: 2018年6月24日 上午11:26:35
 */
public interface UserService extends BaseService<User, Long> {
	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findRoles(String username);

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String username);


}
