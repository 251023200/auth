package com.sky.auth.permission.controller;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import com.sky.auth.exception.DataNotFoundException;
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.User;
import com.sky.auth.permission.dto.MenuTreeNode;
import com.sky.auth.permission.service.UserService;
import com.sky.auth.permission.util.MenuTreeUtil;
import com.sky.auth.util.EncryptUtil;
import com.sky.auth.util.Page;
import com.sky.auth.util.StringUtil;

@Controller
public class ApiController {
	public static Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	
	/**
	 * 函数功能说明 ： 查询所有用户 
	 * GET http://127.0.0.1:8080/users
	 * 
	 * @参数： 
	 *
	 * @throws
	 */
	@RequestMapping(value = "/api/versions",method={RequestMethod.GET})
	@ResponseBody
	public List<String> getAllVersions(){
		List<String> versions = new ArrayList<String>();
		versions.add("v1");
		versions.add("v2");
		return versions;
	}
	
	/**
	 * 函数功能说明 ：删除用户
	 * DETELE http://127.0.0.1:8080/auth/users/{id}
	 * 
	 * @参数：
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/api/versions/{id}",method=RequestMethod.GET)
	@ResponseBody
	public User getVersion(@PathVariable String id){
		User user = new User();
		return user;
	}
	
	/**
	 * 函数功能说明 ：删除用户
	 * DETELE http://127.0.0.1:8080/auth/users/{id}
	 * 
	 * @参数：
	 * @return 
	 * @throws
	 */
//	@RequestMapping(value = "/api/versions/{fid}",method=RequestMethod.GET)
//	@ResponseBody
//	public Role getVersion2(@PathVariable String fid){
//		Role role = new Role();
//		return role;
//	}
	

}
