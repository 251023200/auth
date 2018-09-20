package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 资源
 * 
 * @author yangfan
 *
 */
public class Resource extends BaseEntity {

	private static final long serialVersionUID = -8699337175529772379L;
	
	private String parentId; // 父资源

	private String name; // 资源名称

	private String code; // 资源编码

	private String type; // 资源类型

	private String description; // 资源描述

	private String uri; // 资源uri

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
