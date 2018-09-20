package com.sky.auth.exception;

public class AuthorizationException extends BusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4758839140358515745L;

	public AuthorizationException(){
		super(ExceptionEnum.AuthorizationException.getCode(),ExceptionEnum.AuthorizationException.getMessage());
	}
	
	public AuthorizationException(String msg){
		super(ExceptionEnum.AuthorizationException.getCode(),msg);
	}

}
