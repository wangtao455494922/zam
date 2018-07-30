package com.wjt.zam.modules.mon.model;

import java.io.Serializable;
import java.util.Date;

import com.github.pagehelper.PageInfo;

public class Session extends PageInfo<Session> {

	private static final long serialVersionUID = -792766307302220473L;

	/** session的会话id */
	private Serializable id;
	/** 用户名 */
	private String name;
	/** 主机 */
	private String host;
	/** 最后访问时间 */
	private Date lastAccessTime;
	/** 是否强制退出 */
	private String forceLogout;
	
	public Serializable getId() {
		return id;
	}
	public void setId(Serializable id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Date getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public String getForceLogout() {
		return forceLogout;
	}
	public void setForceLogout(String forceLogout) {
		this.forceLogout = forceLogout;
	}
	
	

}
