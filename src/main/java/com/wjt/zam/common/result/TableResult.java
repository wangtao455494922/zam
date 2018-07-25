package com.wjt.zam.common.result;

import java.util.List;

/**  

* <p>Description: layui  table返回对象包装(默认)</p>  
* @author wjt  
* @date 2018年7月10日  

*/ 
public class TableResult {
	
	private String code;//
	private String msg;//
	private long count;//
	private List<?> data;//
	
	public TableResult(String code, String msg, long count, List<?> list) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = list;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}	
	
	
}
