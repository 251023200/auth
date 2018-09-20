package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 角色权限关联表
 * 
 * @author：yangfan
 */
public class RolePermission extends BaseEntity {

	private static final long serialVersionUID = -9012707031072904356L;
	private String roleId; // 角色ID
	private String permissionId;// 权限ID

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

}
