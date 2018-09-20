package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 权限管理-应用
 * 
 * @author：yangfan
 */
public class Application extends BaseEntity {

	private static final long serialVersionUID = -1850274607153125161L;
	private String code; // 应用编码：例如：admin
	private String name; // 应用名称
//	private String serviceId;	//服务标识, 服务注册发布中心
//	private String apiGatewaay;	//网关注册服务名
	private String description; // 应用描述

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 应用名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 应用名称
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
