package com.sky.auth.exception;

import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;

/**
 * token 不存在
 * @author 杨帆
 *
 */
public class TokenNotFountException extends InvalidTokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6921302441700226791L;

	public TokenNotFountException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public TokenNotFountException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

}
