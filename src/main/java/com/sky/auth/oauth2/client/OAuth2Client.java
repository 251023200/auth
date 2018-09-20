package com.sky.auth.oauth2.client;


import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.JdbcClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

//@Configuration
//@EnableOAuth2Client
public class OAuth2Client {

	private String defaultFilterProcessesUrl = "/access-token";
	
	@Autowired
	private OAuth2ClientProperties oAuth2ClientProperties;
	
	@Autowired
	private OAuth2ClientContext oAuth2ClientContext;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public ClientTokenServices clientTokenServices(){//客户端存储token
		return new JdbcClientTokenServices(dataSource);
	}
	
	@Bean
	public OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationProcessingFilter(OAuth2RestTemplate oAuth2RestTemplate,RemoteTokenServices remoteTokenService){
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(defaultFilterProcessesUrl);
		filter.setRestTemplate(oAuth2RestTemplate);
		filter.setTokenServices(remoteTokenService);
		
		filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler(){
			public void onAuthenticationSuccess(HttpServletRequest request,
					HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {

				this.setDefaultTargetUrl("http://127.0.0.1/index.html");
				super.onAuthenticationSuccess(request, response, authentication);
			}
		});
		return filter;
	}
	
	
	@Bean
	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails,OAuth2ClientContext OAuth2ClientContext){
		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(oAuth2ProtectedResourceDetails,oAuth2ClientContext);
		
		AuthorizationCodeAccessTokenProvider authorizationCodeAccessTokenProvider = new AuthorizationCodeAccessTokenProvider();
		authorizationCodeAccessTokenProvider.setStateMandatory(false);	//Possible CSRF detected - state parameter was required but no state could be found
		AccessTokenProviderChain accessTokenProviderChain = new AccessTokenProviderChain(Arrays.asList(authorizationCodeAccessTokenProvider));
		
		accessTokenProviderChain.setClientTokenServices(clientTokenServices());
		
		oAuth2RestTemplate.setAccessTokenProvider(accessTokenProviderChain);
		return oAuth2RestTemplate;
	}
	
	
//	@Qualifier("checkTokenService")
//	@Bean
//	public RemoteTokenServices tokenService(OAuth2ProtectedResourceDetails details){
//		RemoteTokenServices tokenService = new RemoteTokenServices();
//		tokenService.setCheckTokenEndpointUrl("http://127.0.1:8087/oauth/check_token");
//		tokenService.setClientId(details.getClientId());
//		tokenService.setClientSecret(details.getClientSecret());
//		return tokenService;
//	}
	

}
