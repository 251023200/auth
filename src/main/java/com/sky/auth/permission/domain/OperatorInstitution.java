package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntityV0;

public class OperatorInstitution extends BaseEntityV0{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6100968842662542217L;
	private String userId;//操作者id
	private String userName;//操作名称
	private String userRealName;//操作者真实姓名
	private String orgId;//机构id
	private String orgName;//机构名称
	private String type;//类型
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
	
}
