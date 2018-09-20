package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 操作
 * @author yangfan
 *
 */
public class Operation extends BaseEntity{

	private static final long serialVersionUID = 8299909113623173269L;
	
	private String name;	//操作名称
	private String code;	//操作编码
	private String url;		//操作路径
	private String method;  //http方法
	private String description;	//操作描述
	
	private String appId;	//应用编号
	
	/******************** 非持久化 BEGIN ***********************/
	private String appName;	//应用名称, 
	private String appCode;	//应用编码
	/******************** 非持久化 END ***********************/
	
	public String getName(){
		return name;
	}
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public void setName(String name){
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String toString(){
		boolean empty = true;
		String res = "{";
		if(id!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="name:" + name;
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
		if(method!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="method:" + method;
		}
		if(url!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="url:" + url;
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
