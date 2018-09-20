package com.sky.auth.exception;

/**
 * 参数不合法异常类
 * @author 杨帆
 *
 */
public class ParameterInvalidException extends BusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7662059603604332478L;

	public ParameterInvalidException(){
		super(ExceptionEnum.ParameterInvalidException.getCode(),ExceptionEnum.ParameterInvalidException.getMessage());
	}
	
	public ParameterInvalidException(String msg){
		super(ExceptionEnum.ParameterInvalidException.getCode(),msg);
	}
}
