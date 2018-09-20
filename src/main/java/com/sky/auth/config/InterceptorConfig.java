package com.sky.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.sky.auth.interceptor.LogInterceptor;

/**
 * 拦截器
 * @author yangfan
 *
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");	//访问日志
		//registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**").excludePathPatterns("/access-token","/error");	//认证
	}

}
