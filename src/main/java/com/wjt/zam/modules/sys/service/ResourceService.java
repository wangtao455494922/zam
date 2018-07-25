package com.wjt.zam.modules.sys.service;

import java.util.List;
import java.util.Set;

import com.wjt.zam.common.base.BaseService;
import com.wjt.zam.modules.sys.model.Resource;

/**     
 * @Description:资源-业务层   
 * @author: wangjintao 
 * @date:   2018年6月24日      上午11:19:00   
 */  
public interface ResourceService extends BaseService<Resource, String>{

	/**
	 * 得到资源对应的权限字符串
	 * 
	 * @param resourceIds
	 * @return
	 */
	Set<String> findPermissions(List<String> roleIdList);

	/**
	 * 根据用户权限得到菜单
	 * 
	 * @param permissions
	 * @return
	 */
	List<Resource> findMenus(Set<String> permissions);

	
	/**   
	 * @Description: 根据资源对象获取资源信息   
	 * @param: @param resource 查询参数对象
	 * @param: @return      
	 */
	List<Resource> getResources(Resource resource);

}
