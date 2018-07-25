package com.wjt.zam.common.result;

public class AjaxResult {

    private boolean success = false;

    private Object msg = "";

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

    
}
