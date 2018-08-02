package com.wjt.zam.modules.sys.impl;

import com.wjt.zam.common.base.BaseMapper;
import com.wjt.zam.common.base.BaseServiceImpl;
import com.wjt.zam.common.utils.CheckUtils;
import com.wjt.zam.common.utils.ConvertUtils;
import com.wjt.zam.modules.sys.mapper.ResourceMapper;
import com.wjt.zam.modules.sys.mapper.RoleMapper;
import com.wjt.zam.modules.sys.model.Resource;
import com.wjt.zam.modules.sys.model.Role;
import com.wjt.zam.modules.sys.service.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service(value="resourceServiceImpl")
@Transactional
public class ResourceServiceImpl extends BaseServiceImpl<Resource, String> implements ResourceService {
	@Autowired
	private ResourceMapper resourceMapper;
	@Override
	public BaseMapper<Resource, String> getBaseMapper() {
		return  resourceMapper;
	}
	@Autowired
	private RoleMapper roleMapper;


    @Override
    public Set<String> findPermissions(List<String> roleIdsList) {
        Set<String> permissions = new HashSet<String>();
        List<String> resourceIdsList = new LinkedList<>();
        //角色->资源
        List<Role> roles = roleMapper.findRoles(roleIdsList);
        //资源id格式化
        for (Role role : roles) {
        	resourceIdsList.addAll(ConvertUtils.strToList(role.getResourceIdsStr()));
		}
        List<Resource> resourcesList = resourceMapper.findResources(resourceIdsList);
        if (CheckUtils.listIsNotNull(resourcesList)) {
        	for (Resource resource : resourcesList) {
        		permissions.add(resource.getPermission());
			}
        	 return permissions;
		} else {
			return Collections.emptySet();
		}
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public List<Resource> findMenus(Set<String> permissions) {
    	//一级菜单
    	List<String> permissionList = new ArrayList(permissions);
		List<Resource> firstResources = findFirstMenus(permissionList);
    	for (Resource resource : firstResources) {
    		//二级菜单
    		List<Resource> secondMenus = resourceMapper.findSecondMenus(resource.getId(),permissionList);
    		resource.setSecondResources(secondMenus);
		}
        return firstResources;
    }
    
	private List<Resource> findFirstMenus(List<String> permissionList) {
		return resourceMapper.findFirstMenus(permissionList);
	}

	@Override
	public List<Resource> getResources(Resource resource) {
		return resourceMapper.getResources(resource);
	}

	@Override
	public List<Resource> getResources4Ztree(String id) {
		return resourceMapper.getResources4Ztree(id);
	}

}
