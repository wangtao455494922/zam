package com.wjt.zam.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wjt.zam.common.base.BaseMapper;
import com.wjt.zam.modules.sys.model.Resource;

public interface ResourceMapper  extends BaseMapper<Resource, String>{

	/**  
	 * 根据资源id获取资源列表
	 */
	List<Resource> findResources(List<String> resourceIdsList);
	/**  
	 * 获取一级菜单(权限)  
	 * @param permissionList 
	 */
	List<Resource> findFirstMenus(List<String> permissionList);
	/**  
	 * 获取二级菜单(权限)   
	 */
	List<Resource> findSecondMenus(@Param("parentId")String parentId, @Param("list")List<String> permissionList);
	/**  
	 * 获取资源信息  
	 */
	List<Resource> getResources(Resource resource);
	/**  
	 * 获取ztree展示资源
	 * @param id 角色id
	 */
	List<Resource> getResources4Ztree(String id);
}
