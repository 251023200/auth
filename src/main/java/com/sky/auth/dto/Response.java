package com.sky.auth.dto;

import java.io.Serializable;

/**
 * api请求正常返回结果,该实体作为API请求时,按照规范返回的实体. code 为返回码 msg 为返回描述 data 为返回的具体结果 Created
 * 
 * @author yangfan
 */
public class Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3668883748673948795L;

	/**
	 * 返回码
	 */
	private String code;

	/**
	 * 返回描述
	 */
	private String msg = "";

	/**
	 * 返回数据
	 */
	private Object data;
	
//	public Response(String msg){
//		this(-1,msg);
//	}
	
	public Response(){
		
	}
	
	public Response(String code){
		this(code,null,null);
	}
	
	public Response(String code, String msg) {
		this(code,msg,null);
	}
	
	public Response(String code,Object data){
		this.code = code;
		this.data = data;
	}
	
	public Response(String code, String msg,Object data) {
		this.code = code;
		this.msg = msg;
		if (data != null) {
			this.data = data;
		}
	}

	public Response(Object data) {
		this.code = "0";
		this.msg = "";
		if (data != null) {
			this.data = data;
		}
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}
	
//	public static  void main(String [] args ){
//		int code = 0;
//		String msg = "test data";
//        
//       
//
//        PageListVO pageListVO = new PageListVO(0,2,33,new ArrayList<Object>());
//        Response response = new Response(code,msg,pageListVO);
//       
//
//        System.out.println(JSONObject.toJSONString(response));
//        
//        PageParam param = new PageParam(10, 20);
//        response.setData(param);
//        System.out.println(JSONObject.toJSONString(response));
//    }
	
}
