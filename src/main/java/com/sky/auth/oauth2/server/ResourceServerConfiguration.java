package com.sky.auth.oauth2.server;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.FilterInvocation;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	 private static final String DEMO_RESOURCE_ID = "*";
	 
	 @Override
     public void configure(ResourceServerSecurityConfigurer resources) {
         //resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
     }

	 /*
     @Override
     public void configure(HttpSecurity http) throws Exception {
         
    	 http.
 	 		httpBasic().disable()	
 	 		.requestMatchers()
 	 			.antMatchers("/users/**","/roles/**","/menus/**","/role-menus/**","/user-roles/**","/operations/**","/role-operations/**")
 	 	.and()
 	 		.authorizeRequests()
 	 		    .anyRequest().permitAll();	//just for test
// 	 			.antMatchers("/users/**").authenticated()
// 	 			.antMatchers("/roles/**").authenticated()
// 	 			.antMatchers("/menus/**").authenticated()
// 	 			.antMatchers("/operations/**").authenticated()
// 	 			.antMatchers("/user-roles/**").authenticated()
// 	 			.antMatchers("/role-menus/**").authenticated()
// 	 			.antMatchers("/role-operations/**").authenticated();
    	
     }
     */
	 
	 @Override
     public void configure(HttpSecurity http) throws Exception {
		
		 /*** 测试   放开权限  BEGIN***/
		 boolean test = false;
 	 	 if(test){
 	 		 configureForTest(http);
 	 		 return;
 	 	 }
 	 	 /*** 测试   放开权限  END***/
 	 	 
    	 http
    	 	.httpBasic().disable()	//禁掉HTTP基本认证	
    	 	.anonymous().disable()	//禁掉匿名用户
    	 	.authorizeRequests()
    	 		.anyRequest()
    	 			.authenticated()
    	 			.accessDecisionManager(accessDecisionManager());
    	
     }
	 
	 private void configureForTest(HttpSecurity http) throws Exception {
		
		 http
	 		.httpBasic().disable()	
	 		.requestMatchers()
	 			.antMatchers("/users/**","/roles/**","/menus/**","/permissions/**","/operations/**","/user-roles/**","/role-permissions/**","/role-menus/**","/permission-operations/**","/permission-menus/**")
	 	.and()
	 		.authorizeRequests()
	 		    .anyRequest().permitAll();	//just for test
//	 			.antMatchers("/users/**").authenticated()
//	 			.antMatchers("/roles/**").authenticated()
//	 			.antMatchers("/menus/**").authenticated()
//	 			.antMatchers("/operations/**").authenticated()
//	 			.antMatchers("/user-roles/**").authenticated()
//	 			.antMatchers("/role-menus/**").authenticated()
//	 			.antMatchers("/role-operations/**").authenticated();
	 }
     
	 
	 @Bean
	 public AccessDecisionManager accessDecisionManager(){
		 List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>(1);
		 decisionVoters.add(accessDecisionVoter());
		 return new AffirmativeBased(decisionVoters);
	 }
	 
	 @Bean
	 public AccessDecisionVoter<FilterInvocation> accessDecisionVoter(){
		 return new ApiAccessVoter();
	 }

}
