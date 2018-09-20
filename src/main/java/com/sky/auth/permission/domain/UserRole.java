package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 
 * 用户-角色,操作员关联表..
 * 
 * @author：yangfan
 */
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 174356028197753020L;
	private String roleId;	// 角色ID
	private String userId;	// 用户ID

	/**
	 * 角色ID
	 * 
	 * @return
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 角色ID
	 * 
	 * @return
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取用户ID
	 * 
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 * 
	 * @return
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
