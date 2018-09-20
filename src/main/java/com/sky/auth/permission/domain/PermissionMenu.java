package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 权限管理-权限菜单关联表..
 * 
 * @author：yangfan
 */
public class PermissionMenu extends BaseEntity {

	private static final long serialVersionUID = -9012707031072904356L;

	/** 权限ID **/
	private String permissionId;

	/** 菜单ID **/
	private String menuId;

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
