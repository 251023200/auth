package com.sky.auth.domain;

import java.io.Serializable;

public class SessionInfo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 612782284931726112L;
	private String id;// 用户ID
	private String username;// 登录名
	private String name;// 姓名
	private String ip;// 用户IP
	
	
    private String roleIds;
    private Object menus;//菜单
    
    //private String type;//用户类型 0 精子库用户  1机构用户
    
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public Object getMenus() {
		return menus;
	}
	public void setMenus(Object menus) {
		this.menus = menus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
