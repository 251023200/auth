package com.sky.auth.oauth2.server;

/**
 * 调用的接口
 * @author 杨帆
 *
 */
public class Api {

	private String serviceId;	//调用的服务id
	
	private String method;	//调用的防范
	
	private String url;	//调用的接口

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
