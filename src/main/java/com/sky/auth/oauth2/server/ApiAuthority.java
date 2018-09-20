package com.sky.auth.oauth2.server;

import org.springframework.security.core.GrantedAuthority;

/**
 * 接口访问权限
 * @author 杨帆
 *
 */
public class ApiAuthority implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8104177803891569983L;
	private String appCode;	//应用编码
	private String method;	//方法
	private String apiCode;	//api的编码
	private String url;	//接口
	
	public ApiAuthority(String appCode,String apiCode,String method,String url) {
		this.appCode = appCode;
		this.apiCode = apiCode;
		this.method = method;
		this.url = url;
	}
	
	@Override
	public String getAuthority() {
		return appCode+":"+method+":"+apiCode;
	}
	
	public String getAppCode(){
		return appCode;
	}
	
	public String getMethod(){
		return method;
	}

	public String getUrl(){
		return url;
	}
	
	@Override
	public int hashCode(){
        int result = 17;
        result = 31 * result + method.hashCode();
        result = 31 * result + url.hashCode();
        return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this==obj)return true;
		if(obj==null)return false;
		if(this.getClass()!=obj.getClass())return false;
		
		ApiAuthority o = (ApiAuthority)obj;
		if(method.equals(o.getMethod()) && url.equals(o.getUrl())){
			return true;
		}
		
		return false;
	}
}
