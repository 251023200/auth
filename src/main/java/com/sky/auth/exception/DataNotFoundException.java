package com.sky.auth.exception;

/**
 * 数据找不到异常类
 * @author yangfan
 *
 */
public class DataNotFoundException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 185897461679321287L;

	public DataNotFoundException(){
		super(ExceptionEnum.DataNotFoundException.getCode(),ExceptionEnum.DataNotFoundException.getMessage());
	}
	
	public DataNotFoundException(String msg){
		super(ExceptionEnum.DataNotFoundException.getCode(),msg);
	}
}
