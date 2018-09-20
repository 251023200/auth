package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 权限管理-权限操作关联表..
 * 
 * @author：yangfan
 */
public class PermissionOperation extends BaseEntity {

	private static final long serialVersionUID = -9012707031072904356L;

	/** 权限ID **/
	private String permissionId;

	/** 菜单ID **/
	private String operationId;

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

}
