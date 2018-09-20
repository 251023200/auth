package com.sky.auth.dto;

import java.io.IOException;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

@ControllerAdvice(basePackages="com.sky.auth.permission.controller")
public class ApiRequestBody implements RequestBodyAdvice {

	private static Logger logger = LoggerFactory.getLogger(ApiRequestBody.class);
	
	public ApiRequestBody() {
		//System.out.println("---xxx");
	}

	@Override
	public Object afterBodyRead(Object arg0, HttpInputMessage arg1, MethodParameter arg2, Type arg3,
			Class<? extends HttpMessageConverter<?>> arg4) {
		// TODO Auto-generated method stub
		String method = arg2.getMethod().getName();
		//System.out.println("====="+method);
		return arg0;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage arg0, MethodParameter arg1, Type arg2,
			Class<? extends HttpMessageConverter<?>> arg3) throws IOException {
		//String host = arg0.getHeaders().get
		String method = arg1.getMethod().getName();
		//System.out.println("====="+method);
		return arg0;
	}

	@Override
	public Object handleEmptyBody(Object arg0, HttpInputMessage arg1, MethodParameter arg2, Type arg3,
			Class<? extends HttpMessageConverter<?>> arg4) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public boolean supports(MethodParameter arg0, Type arg1, Class<? extends HttpMessageConverter<?>> arg2) {
		String method = arg0.getMethod().getName();
		//System.out.println("====xx="+method);
		return true;
	}

}
