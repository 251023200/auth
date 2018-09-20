package com.sky.auth.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky.auth.oauth2.server.MyUserDetail;
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.dto.MenuTreeNode;
import com.sky.auth.permission.service.UserService;
import com.sky.auth.permission.util.MenuTreeUtil;


/**
 * OAuth2.0 认证通过后获取用户信息
 * @author 杨帆
 *
 */
@Controller
public class UserInfoController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenStore tokenStore;
	
	@RequestMapping(path="/mymenu",method=RequestMethod.GET)
	@ResponseBody
	public List<MenuTreeNode> getMyMenus(@RequestParam String access_token){
		OAuth2Authentication authentication = tokenStore.readAuthentication(access_token);
		MyUserDetail user=(MyUserDetail)authentication.getPrincipal();
		String userId = user.getUserId();
		List<Menu> menus= userService.getMenus(userId);
		List<MenuTreeNode> menuTree=MenuTreeUtil.wapTree(menus);
		return menuTree;
	}

	@RequestMapping(path="/user-info",method=RequestMethod.GET)
	@ResponseBody
	public List<String> userInfo(){
		List<String> userInfo = new ArrayList<String>();
		userInfo.add("123");
		userInfo.add("456");
		return userInfo;
	}

}
