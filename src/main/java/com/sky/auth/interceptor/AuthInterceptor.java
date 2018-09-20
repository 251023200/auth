package com.sky.auth.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sky.auth.exception.AuthenticationException;
import com.sky.auth.exception.AuthorizationException;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//认证
		String accessToken = request.getHeader("access_token");
		if(accessToken==null){
			accessToken = request.getParameter("access_token");
		}
		if(!isValid(accessToken)){
			throw new AuthenticationException();
		}
		
		//校验权限
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Access注解
        Auth auth = method.getAnnotation(Auth.class);
        if (auth != null && auth.permissions().length>0) {
        	// 如果注解为null, 说明不需要拦截, 直接放过
            boolean permitted = isPermitted(accessToken,auth.permissions()) ;
            if(!permitted){
            	throw new AuthorizationException();
            }
        }
        

		return true;
	} 

	private boolean isValid(String accessToken){
		if(accessToken==null||accessToken.trim().equals("")){
			return false;
		}
		return true;
	}
	
	private boolean isPermitted(String accessToken,String[] permissions){
		return true;
	}
}
