package com.sky.auth.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestContextHolderUtil {

	public static HttpServletRequest getRequest(){
		RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttribute).getRequest();
        return request;
	}
	
	public static HttpServletResponse getResponse(){
		RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes)requestAttribute).getResponse();
        return response;
	}

	public static HttpSession getSession(){
		return getRequest().getSession();
	}
}
