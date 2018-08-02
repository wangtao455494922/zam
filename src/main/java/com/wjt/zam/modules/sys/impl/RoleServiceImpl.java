package com.wjt.zam.modules.sys.impl;

import com.wjt.zam.common.base.BaseMapper;
import com.wjt.zam.common.base.BaseServiceImpl;
import com.wjt.zam.common.utils.CheckUtils;
import com.wjt.zam.modules.sys.mapper.RoleMapper;
import com.wjt.zam.modules.sys.model.Role;
import com.wjt.zam.modules.sys.service.ResourceService;
import com.wjt.zam.modules.sys.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value="roleServiceImpl")
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role, Long>implements RoleService {
	
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
	public BaseMapper<Role, Long> getBaseMapper() {
		return roleMapper;
	}
    
    @Override
    public Set<String> findRoles(List<String> roleIdsList) {
        Set<String> roles = new HashSet<String>();
        List<Role> roleList= roleMapper.findRoles(roleIdsList);
        if (CheckUtils.listIsNotNull(roleList)) {
			for (Role role : roleList) {
				roles.add(role.getRole());
			}
			return roles;
		} else {
			return Collections.emptySet();
		}
    }

    @Override
	public Set<String> findPermissions(List<String> roleIdsList) {
		if (CheckUtils.listIsNotNull(roleIdsList)) {
			return resourceService.findPermissions(roleIdsList);
		} else {
			return Collections.emptySet();
		}
	}

}
