package com.sky.auth.oauth2.server;

import java.util.Collection;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

public class ApiAccessVoter implements AccessDecisionVoter<FilterInvocation>{

	private AntPathMatcher antPathMatcher;
	
	public ApiAccessVoter() {
		antPathMatcher = new AntPathMatcher();
		antPathMatcher.setTrimTokens(false);
		antPathMatcher.setCaseSensitive(false);
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {
		System.out.println("校验    "+fi.getFullRequestUrl());
		String method = fi.getRequest().getMethod();
		if(method==null){
			method = "GET";
		}
		String url = fi.getRequestUrl();
		int index = url.indexOf("?");
		if(index>0){
			url = url.substring(0, index);
		}
		
		for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
			ApiAuthority apiAuthority = (ApiAuthority)grantedAuthority;
			String apiUrl = apiAuthority.getUrl();
			String apiMethod = apiAuthority.getMethod();
			
			if(!"ALL".equalsIgnoreCase(apiMethod) && !method.equalsIgnoreCase(apiMethod)){
				continue;
			}
			if(antPathMatcher.match(apiUrl, url)){//匹配成功
				return ACCESS_GRANTED;
			}
				
		}
		
		return ACCESS_DENIED;
	}

	
}
