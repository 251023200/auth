package com.sky.auth.oauth2.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	
//	@Autowired
//	private ClientDetailsService clientDetailsService;

	/**
     * 1 这里记得设置requestMatchers,不拦截需要token验证的url
     * 不然会优先被这个filter拦截,走用户端的认证而不是token认证
     * 2 这里记得对oauth的url进行保护,正常是需要登录态才可以
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	 http
    	 	.csrf()
    	 		.disable()
    	 	.httpBasic()
    	 		.disable()
//    	 	.anonymous()
//    	 		.disable()
         	.requestMatchers()
         		.antMatchers("/login**","/error**","/favicon.ico","/check-authority")
         		//.antMatchers("/mymenu**")
          .and()
             .authorizeRequests()
             	.antMatchers("/login**","/error**","/favicon.ico","/check-authority").permitAll();
    	 
    	 
    
    }
}
