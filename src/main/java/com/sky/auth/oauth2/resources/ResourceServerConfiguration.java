package com.sky.auth.oauth2.resources;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

//@Configuration
//@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	 private static final String DEMO_RESOURCE_ID = "*";
	 
//	 @Autowired
//	 private OAuth2ClientProperties oAuth2ClientProperties;
	 
//	 @Autowired
//	 private AuthorizationServerProperties authorizationServerProperties;

	 @Primary
	 @Bean
	 public RemoteTokenServices remoteTokenSerices(){
		 RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
		 remoteTokenServices.setClientId("client_1");
		 remoteTokenServices.setClientSecret("123456");
		 remoteTokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:8087/oauth/check_token");
		 remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
		 return remoteTokenServices;
	 }
	 
	 @Override
     public void configure(ResourceServerSecurityConfigurer resources) {
         //resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
		 resources.tokenServices(remoteTokenSerices());
     }

     @Override
     public void configure(HttpSecurity http) throws Exception {
         // @formatter:off
    	 http.
    	 	httpBasic().disable()
    	 	
    	 .requestMatchers().antMatchers("/users/**","/roles/**","/menus/**","/role-menus/**","/user-roles/**","/operations/**","/role-operations/**")
         .and()
         .authorizeRequests()
//         .anyRequest().authenticated();
         .antMatchers("/users/**").authenticated()
         .antMatchers("/roles/**").authenticated()
         .antMatchers("/menus/**").authenticated()
         .antMatchers("/operations/**").authenticated()
         .antMatchers("/user-roles/**").authenticated()
         .antMatchers("/role-menus/**").authenticated()
         .antMatchers("/role-operations/**").authenticated();
               
         // @formatter:on
     }

     @Bean
     public AccessTokenConverter accessTokenConverter() {
         return new DefaultAccessTokenConverter();
     }
}
