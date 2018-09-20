package com.sky.auth.oauth2.server;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;



/**
 * OAuth2 认证服务配置
 * @author 
 *
 */
@Configuration
@EnableAuthorizationServer	//提供/oauth/authorize,/oauth/token,/oauth/check_token,/oauth/confirm_access,/oauth/error
@EnableConfigurationProperties(AuthorizationServerProperties.class)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private RedisConnectionFactory redisConnectionFactory;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * 将token存储进redis
	 * @return
	 */
	@Bean
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
	}
	
	@Bean
    protected UserDetailsService userDetailsService(){
//		System.out.println("----UserDetailsService-----");
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
//        manager.createUser(User.withUsername("user_2").password("123456").authorities("USER").build());
//        return manager;
		return new MyUserDetailService();
    }

	
//	@Bean
//	public ApplicationClientDetailsService applicationClientDetailsService(){
//		return new ApplicationClientDetailsService();
//	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//security.allowFormAuthenticationForClients();
		security
			.checkTokenAccess("permitAll()")
			.allowFormAuthenticationForClients();
	}

	/**
	 * 配置 接入 授权服务的 客户端信息
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
//		clients.jdbc(dataSource);
//		 clients.withClientDetails(applicationClientDetailsService());
		
		clients.jdbc(dataSource);
		
		 /**使用存在内存中配置
		 clients.inMemory()
		 .withClient("client_1")	//客户端标识 ID
		 .secret("123456")			//客户端安全码
         //.resourceIds("oauth2-resource")	
         .scopes("auth_permissons")				// // 允许的授权范围，默认为空则拥有全部范围
         .authorizedGrantTypes("authorization_code","client_credentials","refresh_token","password")	//客户端使用的授权类型，默认为空
         .authorities("users","roles")	//客户端可使用的权限
         .accessTokenValiditySeconds(1800)
         .refreshTokenValiditySeconds(3600);
         */
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//endpoints.tokenStore(redisTokenStore());
		endpoints.tokenStore(redisTokenStore())
		.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService());
     //   .authenticationManager(authenticationManager());
	}
}
