package com.sky.auth.oauth2.server;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUserDetail extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2632167040142141696L;
	
	private String userId;	//用户open-id

	public MyUserDetail(String userId,String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
	}

	public MyUserDetail(String userId,String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}

}
