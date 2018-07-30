package com.wjt.zam.common;

import java.util.HashMap;
import java.util.Map;

import com.wjt.zam.common.rewrite.ZamPropertyConfigurer;

/**
 * @Description:公共--常量
 * @author: wangjintao
 * @date: 2018年6月24日 下午2:37:44
 */
public class Constant {
	public static String getProperty(String name) {
		return ZamPropertyConfigurer.getCtxProp(name);
	}

	/**
	 * 资源菜单code
	 */
	public static final String RESOURCE_MENU_CODE = "menu";
	/**
	 * 资源按钮code
	 */
	public static final String RESOURCE_BUTTON_CODE = "button";
	/**
	 * 通用成功code
	 */
	public static final String SUCCESS_CODE = "1";
	/**
	 * 通用失败code
	 */
	public static final String ERROR_CODE = "0";
	/**
	 * 请假状态--初始录入
	 */
	public static final Integer LEAVE_STATE_ZERO = 0;
	/**
	 * 请假状态--开始审批
	 */
	public static final Integer LEAVE_STATE_ONE = 1;
	/**
	 * 请假状态--审批完成
	 */
	public static final Integer LEAVE_STATE_TWO = 2;
	/**
	 * shiro 会话管理是否被强制退出标识
	 */
	public static final Object SESSION_FORCE_LOGOUT_KEY = "session.force.logout";;

	/**
	 * 资源菜单名称
	 */
	public static final String RESOURCE_MENU_NAME = getProperty("resource.menu.name");
	/**
	 * 资源按钮名称
	 */
	public static final String RESOURCE_BUTTON_NAME = getProperty("resource.button.name");
	/**
	 * 添加成功提示信息
	 */
	public static final String INSERT_SUCCESS = getProperty("insert.success");
	/**
	 * 添加失败提示信息
	 */
	public static final String INSERT_ERROR = getProperty("insert.error");
	/**
	 * 更新成功提示信息
	 */
	public static final String UPDATE_SUCCESS = getProperty("update.success");
	/**
	 * 更新失败提示信息
	 */
	public static final String UPDATE_ERROR = getProperty("update.error");
	/**
	 * 删除成功提示信息
	 */
	public static final String DELETE_SUCCESS = getProperty("delete.success");
	/**
	 * 删除失败提示信息
	 */
	public static final String DELETE_ERROR = getProperty("delete.error");
	/**
	 * layui table初始化有数据提示信息
	 */
	public static final String LAYUI_TABLE_SUCCESS_AND_HAVE_DATA_MSG = getProperty("layui.table.success.and.have.data.msg");
	/**
	 * layui table初始化无数据提示信息
	 */
	public static final String LAYUI_TABLE_SUCCESS_NOT_HAVE_DATA_MSG = getProperty("layui.table.success.not.have.data.msg");
	/**
	 * layui table查询成功返回code
	 */
	public static final String LAYUI_TABLE_SUCCESS_CODE = getProperty("layui.table.success.code");
	/**
	 * layui table查询失败返回code
	 */
	public static final String LAYUI_TABLE_ERROR_CODE = getProperty("layui.table.error.code");
	/**
	 * layui upload部署成功提示信息
	 */
	public static final String DEPLOY_SUEECESS = getProperty("deploy.success");
	/**
	 * layui upload部署失败提示信息
	 */
	public static final Object DEPLOY_ERROR = getProperty("deploy.error");
	
	/**
	 * shiro 加密算法
	 */
	public static final String SHIRO_ALGORITHMNAME = getProperty("shiro.algorithmName");
	/**
	 * shiro 加密次数
	 */
	public static final Integer SHIRO_HASHITERATIONS = Integer.parseInt(getProperty("shiro.hashIterations"));
	/**
	 * 默认outcome
	 */
	public static final String DEFAULT_OUTCOME = getProperty("default.outcome");
	/**
	 * shiro 强制退出
	 */
	public static final String FORCE_LOGOUT_SUCCESS = getProperty("force.logout.success");

	public static Map<String, String> getResourceType() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(RESOURCE_MENU_CODE, RESOURCE_MENU_NAME);
		map.put(RESOURCE_BUTTON_CODE, RESOURCE_BUTTON_NAME);
		return map;
	}
}
