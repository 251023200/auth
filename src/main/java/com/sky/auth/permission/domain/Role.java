package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 权限管理-角色.
 * 
 * @author：yangfan
 */
public class Role extends BaseEntity {

	private static final long serialVersionUID = -1850274607153125161L;
	private String code; // 角色编码：例如：admin
	private String name; // 角色名称
	private String description; // 角色描述

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 角色名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 角色名称
	 * 
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString(){
		boolean empty = true;
		String res = "{";
		if(id!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="id:" + id;
		}
		if(code!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="code:" + code;
		}
		if(name!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="name:" + name;
		}
		if(description!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="description:" + description;
		}
		res+="}";
		return res;
	}
}
