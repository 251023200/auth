package com.sky.auth.dto;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@ControllerAdvice
public class ApiResponseBody implements ResponseBodyAdvice<Object>{

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if(body instanceof Response){
			return body;
		}
		if(body instanceof OAuth2Exception){
			OAuth2Exception exception = (OAuth2Exception)body;
			Response responseObj = new Response();
			responseObj.setCode(""+exception.getHttpErrorCode());
			responseObj.setMsg(exception.getMessage());
			return responseObj;
		}
		if(body instanceof String){
			//TODO 转为json
			System.out.println("body is string");
		}
		
		Response responseObj = new Response(body);
		return responseObj;	
	}

}
