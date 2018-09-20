package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 
 * 权限-资源关联表..
 * 
 * @author：yangfan
 */
public class ResourcePermission extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4390370457966564802L;
	
	private String permissionId;	// 用户ID
	private String resourceId;	// 角色ID
	private String resourceType; //资源类型 
	

	/**
	 * 角色ID
	 * 
	 * @return
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * 角色ID
	 * 
	 * @return
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * 获取用户ID
	 * 
	 * @return
	 */
	public String getPermissionId() {
		return permissionId;
	}

	/**
	 * 设置用户ID
	 * 
	 * @return
	 */
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	
}
