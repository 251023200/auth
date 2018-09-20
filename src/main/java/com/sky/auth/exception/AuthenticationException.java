package com.sky.auth.exception;

/**
 * 
 * @author 认证异常
 *
 */
public class AuthenticationException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5661274546948183733L;

	public AuthenticationException(){
		super(ExceptionEnum.AuthenticationException.getCode(),ExceptionEnum.AuthenticationException.getMessage());
	}
	
	public AuthenticationException(String msg){
		super(ExceptionEnum.AuthenticationException.getCode(),msg);
	}

}
