package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 权限(资源-操作)
 * @author yangfan
 *
 */
public class Permission extends BaseEntity{

	private static final long serialVersionUID = -6461642058997915162L;
	
	private String name;		//权限名称
	
	private String code;		//权限编码
	
	private String description;		//权限描述


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
