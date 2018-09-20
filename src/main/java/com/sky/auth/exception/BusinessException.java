package com.sky.auth.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1931560250850549273L;
	
	protected String code;
	
	protected String message;
	
	protected Object data;
	
	public BusinessException() {
		super();
    }
	
	public BusinessException(String code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.message = String.format(msgFormat, args);
	}

    public BusinessException(String message) {
        super();
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
    	this.message = message;
    }

    public String getCode(){
        return code;
    }
    
    public void setCode(String code){
    	this.code=code;
    }
	
    public Object getData(){
    	return data;
    }
    
    public void setData(Object data){
    	this.data = data;
    }
}
