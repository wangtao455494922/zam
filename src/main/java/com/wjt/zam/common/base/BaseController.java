package com.wjt.zam.common.base;

import java.util.List;


import com.github.pagehelper.PageInfo;
import com.wjt.zam.common.Constant;
import com.wjt.zam.common.result.AjaxResult;
import com.wjt.zam.common.result.TableResult;
import com.wjt.zam.common.utils.CheckUtils;

public class BaseController<T> {
	
	 /**
     * ajax成功
     */
    protected AjaxResult renderSuccess(Object obj) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(true);
        result.setMsg(obj);
        return result;
    }
    
    /**
     * ajax失败
     */
    protected AjaxResult renderError(Object obj) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(false);
        result.setMsg(obj);
        return result;
    }
    
    
    /**
     * layui table初始化返回数据
     * @param <T>
     */
    protected TableResult layuiTableRender(List<T> list){
    	PageInfo<T> pageInfo = new PageInfo<>(list);
		TableResult tableResult = new TableResult(Constant.LAYUI_TABLE_SUCCESS_CODE,
				CheckUtils.listIsNotNull(list)?Constant.LAYUI_TABLE_SUCCESS_AND_HAVE_DATA_MSG:Constant.LAYUI_TABLE_SUCCESS_NOT_HAVE_DATA_MSG, pageInfo.getTotal(), pageInfo.getList());
		return tableResult;
    }
    
}
