package com.sky.auth.oauth2.server;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;

import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.service.UserService;

//@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String userNo = username;
		com.sky.auth.permission.domain.User user = userService.getUserByUserNo(userNo);
		if(user!=null){
			String password=user.getPassword();
			String userId = user.getId();
			List<Operation> operations = userService.getAllOperations(userId);
			List<ApiAuthority> apiAuthoritys = new ArrayList<ApiAuthority>(operations.size());
			for(int i=0;i<operations.size();i++){
				String appCode = operations.get(i).getAppCode();
				String apiCode = operations.get(i).getCode();
				String method = operations.get(i).getMethod();
				String url = operations.get(i).getUrl();
				ApiAuthority apiAuthority = new ApiAuthority(appCode,apiCode,method,url);
				apiAuthoritys.add(apiAuthority);
			}
			//User userDetails = new User(username,password,Collections.<GrantedAuthority>emptyList());
			User userDetails = new MyUserDetail(user.getId(),username,password,apiAuthoritys);
			return userDetails;
		}
		throw new UsernameNotFoundException("用户"+username+"不存在!");
	}

}
