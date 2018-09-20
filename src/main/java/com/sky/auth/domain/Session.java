package com.sky.auth.domain;

import java.io.Serializable;


/**
 * 用户会话信息
 * @author yangfan
 *
 */
public class Session implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -473161212155226268L;
	
	private String serverToken;	//用户服务器端私钥, 与accessToken成对
	
	private String userId;	//用户标识
	
	private String userNo;	//用户编号(有业务意义)
	
	private String userName;	//用户系统名
	
	private String name;	//用户姓名
	
	private String permissions;

	public String getServerToken() {
		return serverToken;
	}

	public void setServerToken(String serverToken) {
		this.serverToken = serverToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
}
