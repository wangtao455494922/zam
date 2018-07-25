package com.wjt.zam.modules.sys.impl;

import com.wjt.zam.common.base.BaseMapper;
import com.wjt.zam.common.base.BaseServiceImpl;
import com.wjt.zam.common.utils.ConvertUtils;
import com.wjt.zam.modules.sys.mapper.UserMapper;
import com.wjt.zam.modules.sys.model.User;
import com.wjt.zam.modules.sys.service.RoleService;
import com.wjt.zam.modules.sys.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserMapper userMapper;
    
    @Override
	public BaseMapper<User, Long> getBaseMapper() {
		return userMapper;
	}

    @Override
    public User findByUsername(String username) {
		return userMapper.findByUsername(username);
    }
    
    @Override
	public Set<String> findRoles(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        return roleService.findRoles(ConvertUtils.strToList(user.getRoleIdsStr()));
    }

    @Override
	public Set<String> findPermissions(String username) {
        User user =findByUsername(username);
        if(user == null) {
        	return Collections.emptySet();
        }
        return roleService.findPermissions(ConvertUtils.strToList(user.getRoleIdsStr()));
    }

}
