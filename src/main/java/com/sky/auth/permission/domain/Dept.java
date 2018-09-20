package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 部门
 * @author yangfan
 *
 */
public class Dept extends BaseEntity{

	private static final long serialVersionUID = 6027374475828611572L;

	private String parentId;	//父部门
	
	private String code;	//部门编码,部门编码必须唯一
	
	private String name;	//部门名称,部门名称不一定唯一(eg.分公司都可以有信息管理部)
	
	private String description;	//部门描述

	public String getParentId(){
		return parentId;
	}
	
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	
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
