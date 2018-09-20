package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 角色权限关联表
 * 
 * @author：yangfan
 */
public class RoleOperation extends BaseEntity {

	private static final long serialVersionUID = -9012707031072904356L;
	private String roleId; // 角色ID
	private String operationId;// 权限ID

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
	 * 操作ID
	 * 
	 * @return
	 */
	public String getOperationId() {
		return operationId;
	}

	/**
	 * 操作ID
	 * 
	 * @return
	 */
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

}
