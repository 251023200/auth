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
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.User;
import com.sky.auth.permission.domain.UserRole;
import com.sky.auth.permission.service.UserRoleService;
import com.sky.auth.util.Page;
import com.sky.auth.util.StringUtil;

@Controller
public class UserRoleController {
public static Logger logger = LoggerFactory.getLogger(UserRoleController.class);
	
	@Autowired
	private UserRoleService userRoleService;	
	
	/**
	 * 函数功能说明 ： 查询所有用户角色 
	 * GET http://127.0.0.1:8085/user-roles
	 * 
	 * @参数： 
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/user-roles",method={RequestMethod.GET})
	@ResponseBody
	public List<UserRole> getAllUserRoles(){
		List<UserRole> userRoles = (List<UserRole>)userRoleService.getAllUserRoles();
		return userRoles;
	}
	
	/**
	 * 函数功能说明 ： 添加用户角色
	 * POST http://127.0.0.1:8085/user-roles
	 * 
	 * @参数： User {"userId":"XXX","roleId":"XXX"}
	 * @return UserRole
	 * @throws
	 */
	@RequestMapping(value = "/user-roles",method=RequestMethod.POST)
	@ResponseBody
	public UserRole addUser(@RequestBody UserRole userRole){
		String id=StringUtil.get32UUID();
		userRole.setId(id);
		userRoleService.addUserRole(userRole);
		return userRole;
	}
	
	/**
	 * 函数功能说明 ： 通过id查询用户角色
	 * GET http://127.0.0.1:8080/auth/user-roles/{id}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/user-roles/{id}",method=RequestMethod.GET)
	@ResponseBody
	public UserRole getUserRoleById(@PathVariable String id){
		UserRole userRole = userRoleService.getUserRoleById(id);
		if(userRole==null){
			throw new DataNotFoundException("get /user-roles/{id} failed because user-role with id("+id+") not found!");
		}
		return userRole;
	}
	
	/**
	 * 函数功能说明 ：删除用户角色
	 * DETELE http://127.0.0.1:8080/auth/user-roles/{id}
	 * 
	 * @参数： {"id":"XXX"}
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/user-roles/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id){
		
		int c = userRoleService.deleteUserRoleById(id);
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量添加用户角色
	 * POST http://127.0.0.1:8080/user-roles/batch-add
	 * 
	 * @参数： [{"userId":"XXX","roleId":"xxx"},{"userId":"xxx","roleId":"xxx"},...]
	 * @return {"code":0,msg:null,data:null}
	 * @throws
	 */
	@RequestMapping(value = "/user-roles/batch-add",method=RequestMethod.POST)
	@ResponseBody
	public int batchAdd(@RequestBody List<UserRole> userRoles){
		
		for(int i=0;i<userRoles.size();i++){
			UserRole userRole = userRoles.get(i);
			String id = StringUtil.get32UUID();
			userRole.setId(id);
		}
		int c=userRoleService.addUserRoles(userRoles);
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量删除用户角色
	 * POST http://127.0.0.1:8080/user-roles/batch-delete
	 * 
	 * @参数： [{"id":"XXX"},{"id":"xxx"},...]
	 * @return {"code":0,msg:null,data:null}
	 * @throws
	 */
	@RequestMapping(value = "/user-roles/batch-delete",method=RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids){
		
		int c = userRoleService.deleteUserRoleByIds(ids);
		return c;	
	}
	
	
	/**
	 * 函数功能说明 ： 查询用户所有角色
	 * GET http://127.0.0.1:8080/auth/users/{userId}/roles?pageNum=1&pageSize=5&roleName=xxx&roleCode=xxx
	 * 
	 * @参数：
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/users/{userId}/roles",method=RequestMethod.GET)
	@ResponseBody
	public List<Role> getRoles(@PathVariable String userId,@RequestParam(required=false) String pageNum,@RequestParam(required=false) String pageSize,@RequestParam(required=false) String roleName,@RequestParam(required=false) String roleCode){
		Role role = new Role();
		boolean needPage=true;
		Page page = null;
		if(pageNum==null&&pageSize==null){//不分页
			needPage=false;
		}else{
			page = new Page(pageNum,pageSize);
		}
		if(roleName!=null&&!"".equals(roleName)){
			role.setName(roleName);
		}
		if(roleCode!=null&&!"".equals(roleCode)){
			role.setCode(roleCode);
		}
		List<Role> roles = null;
		if(!needPage){
			roles = userRoleService.searchRoles(userId, role);
		}else{
			PageInfo<Role> rolePageInfo = userRoleService.searchRoles(page.getPageNum(),page.getPageSize(),userId, role);
			roles = rolePageInfo.getList();
		}
		return roles;
	}
	
	/**
	 * 函数功能说明 ： 用户添加(一个)角色
	 * POST http://127.0.0.1:8085/users/{userId}/roles?pageNum=1&pageSize=5
	 * 
	 * @参数：UserRole {"roleId":"XXX"}
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/users/{userId}/roles",method=RequestMethod.POST)
	@ResponseBody
	public UserRole addUserRole(@PathVariable String userId,@RequestBody Role role){
		UserRole userRole = new UserRole();
		userRole.setUserId(userId.trim());
		userRole.setRoleId(role.getId());
		String id = StringUtil.get32UUID();
		userRole.setId(id);
		userRoleService.addUserRole(userRole);
		return userRole;
	}
	
	/**
	 * 函数功能说明 ： 设置用户拥有的角色
	 * PUT http://127.0.0.1:8085/users/{userId}/roles
	 * 
	 * @参数：List<String> eg.["role_id_1","role_id_2",...]
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/users/{userId}/roles",method=RequestMethod.PUT)
	@ResponseBody
	public int updateRolesByUserId(@PathVariable String userId,@RequestBody List<String> roleIds){
		
		userRoleService.deleteUserRoleByUserId(userId);
			
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for(int i=0;i<roleIds.size();i++){
			UserRole userRole = new UserRole();
			String id=StringUtil.get32UUID();
			userRole.setId(id);
			userRole.setUserId(userId);
			userRole.setRoleId(roleIds.get(i));
			userRoles.add(userRole);
		}
			
		int c = userRoleService.addUserRoles(userRoles);
		return c;
	}
	
	/**
	 * 函数功能说明 ： 删除用户的指定角色
	 * DELETE http://127.0.0.1:8085/users/{userId}/roles/{roleId}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","roleCode":"admin","roleName":"超级管理员角色"}}
	 * @throws
	 */
	@RequestMapping(value = "/users/{userId}/roles/{roleId}",method=RequestMethod.DELETE)
	@ResponseBody
	public int deleteUserRoleByUserIdAndRoleId(@PathVariable String userId,@PathVariable String roleId){
		
		int c = userRoleService.deleteUserRoleByUserIdAndRoleId(userId,roleId);
		return c;
	}
	
	
	
	/**
	 * 函数功能说明 ： 分页查询用户的角色
	 * GET http://127.0.0.1:8085/users/{userId}/roles?pageNum=1&pageSize=5
	 * 
	 * @参数：
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/users/{userId}/roles/search",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<Role> searchRolesAssignedToUser(@PathVariable String userId,@RequestParam(required=false) String pageNum,@RequestParam(required=false) String pageSize,@RequestParam(required=false) String roleName,@RequestParam(required=false) String roleCode){
		Page page = new Page(pageNum,pageSize);
		Role role = new Role();
		if(roleName!=null&&!"".equals(roleName)){
			role.setName(roleName);
		}
		if(roleCode!=null&&!"".equals(roleCode)){
			role.setCode(roleCode);
		}
		PageInfo<Role> rolePageBean = userRoleService.searchRoles(page.getPageNum(),page.getPageSize(),userId, role);
		return rolePageBean;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有角色
	 * POST http://127.0.0.1:8085/users/{userId}/roles?pageNum=1&pageSize=5
	 * 
	 * @参数：{"id":"XXX"}
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/users/{userId}/roles/search",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<Role> searchRolesAssignedToUser(@PathVariable String userId,@RequestParam(required=false) String pageNum,@RequestParam(required=false) String pageSize,@RequestBody(required=false) Role role){
		userId=userId.trim();
		Page page = new Page(pageNum,pageSize);
		PageInfo<Role> rolePageBean = userRoleService.searchRoles(page.getPageNum(),page.getPageSize(),userId, role);
		return rolePageBean;
	}
	
	/**
	 * 查询用户没有的角色
	 * GET http://127.0.0.1:8085/users/{userId}/roles/search-left?pageNum=1&pageSize=5
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @param sortby
	 * @param order
	 * @param name
	 * @param code
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/users/{userId}/roles/search-left", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Role> searchRolesNotAssignedToUser(@RequestParam String pageNum, @RequestParam String pageSize,@RequestParam String userId,@RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestParam(required=false) String name,@RequestParam(required=false)String code,@RequestParam(required=false) String description) {
		
		Role role = new Role();
		if(name!=null){
			role.setName(name);
		}
		if(code!=null){
			role.setName(code);
		}
		if(description!=null){
			role.setDescription(description);
		}
		PageInfo<Role> pageInfo=userRoleService.searchLeftRoles(Integer.parseInt(pageNum), Integer.parseInt(pageSize),userId,role);
		return pageInfo;
	}
	
	/**
	 * 函数功能说明 ： 查询用户没有被赋予的角色
	 * POST http://127.0.0.1:8085/users/{userId}/roles/search-left?pageNum=1&pageSize=5
	 * 
	 * @参数：{"id":"XXX"}
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/users/{userId}/roles/search-left",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<Role> searchRolesNotAssignedToUser(@PathVariable String userId,@RequestParam(required=false) String pageNum,@RequestParam(required=false) String pageSize,@RequestBody(required=false) Role role){
		userId=userId.trim();
		Page page = new Page(pageNum,pageSize);
		PageInfo<Role> pageInfo=userRoleService.searchLeftRoles(page.getPageNum(), page.getPageSize(),userId,role);
		return pageInfo;
	}
	
	
	
	
	/**
	 * 函数功能说明 ： 查询拥有角色的用户
	 * GET http://127.0.0.1:8085/roles/{roleId}/users
	 * 
	 * @参数：{"id":"XXX"}
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/roles/{roleId}/users/search",method=RequestMethod.GET)
	@ResponseBody
	public List<User> getUsersHasRole(@PathVariable String roleId){
		roleId=roleId.trim();
		List<User> users = userRoleService.getUsersByRoleId(roleId);
		return users;
	}
	
	
}
