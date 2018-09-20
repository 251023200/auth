package com.sky.auth.exception;

/**
 * 
 * @author 异常枚举类
 *
 */
public enum ExceptionEnum {
	
	AuthenticationException("1000","认证失败"),
	AuthorizationException("1001","没有权限"),
	TokenNotFountException("1002","token不存在,需要认证"),
	TokenExpireException("1003","token存在但过期,需要刷新token"),
	ParameterInvalidException("2000","参数不合法"),
	DataNotFoundException("3000","数据未找到");
	
	
	
	private ExceptionEnum(String code,String message){
		this.code = code;
		this.message = message;
	}
	
	private String code;
	
	private String message;
	
	public String getCode(){
		return code;
	}
	
	public String getMessage(){
		return message;
	}
}
