package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 角色权限关联表
 * 
 * @author：yangfan
 */
public class OperationPermission extends BaseEntity {

	private static final long serialVersionUID = -9012707031072904356L;
	private String permissionId; // 角色ID
	private String operationId;// 权限ID

	/**
	 * 权限ID
	 * 
	 * @return
	 */
	public String getPermissionId() {
		return permissionId;
	}

	/**
	 * 权限ID
	 * 
	 * @return
	 */
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
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
