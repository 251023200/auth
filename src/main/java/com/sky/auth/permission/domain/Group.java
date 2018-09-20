package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 组(一个用户可以属于多个组,一个组可以包含多个用户)
 * @author yangfan
 *
 */
public class Group extends BaseEntity{

	private static final long serialVersionUID = 6659235372299821252L;

	private String code;	//组编码,唯一
	
	private String name;	//组名称
	
	private String description;	//组描述

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
