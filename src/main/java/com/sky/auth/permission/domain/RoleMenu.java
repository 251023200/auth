package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 权限管理-角色权限关联表..
 * 
 * @author：yangfan
 */
public class RoleMenu extends BaseEntity {

	private static final long serialVersionUID = -9012707031072904356L;

	/** 角色ID **/
	private String roleId;

	/** 菜单ID **/
	private String menuId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
