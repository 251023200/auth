package com.sky.auth.exception;

import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;

/**
 * Token 超过有效期
 * @author 杨帆
 *
 */
public class TokenExpireException extends InvalidTokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8151218772024187660L;

	public TokenExpireException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public TokenExpireException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

}
