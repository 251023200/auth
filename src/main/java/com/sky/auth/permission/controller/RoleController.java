package com.sky.auth.permission.controller;

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
import com.sky.auth.permission.service.RoleService;
import com.sky.auth.util.Page;
import com.sky.auth.util.StringUtil;

@Controller
public class RoleController {
	public static Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	/**
	 * 函数功能说明 ： 查询所有角色 GET http://127.0.0.1:8085/roles
	 * 
	 * @参数：
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/roles", method = { RequestMethod.GET })
	@ResponseBody
	public List<Role> getAllRoles() {
		List<Role> roles = (List<Role>) roleService.getAllRoles();
		return roles;
	}

	/**
	 * 函数功能说明 ： 添加角色 POST http://127.0.0.1:8085/roles
	 * 
	 * @param User {"code":"caiwu","name":"caiwujuese","description":"ABCDEDF"} 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	@ResponseBody
	public Role createRole(@RequestBody Role role) {
		String id = StringUtil.get32UUID();
		role.setId(id);
		roleService.addRole(role);
		return role;
	}

	/**
	 * 函数功能说明 ： 查询所有用户 GET http://127.0.0.1:8085/roles/{id}
	 * 
	 * @参数：
	 * 
	 */
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Role getRole(@PathVariable String id) {
		Role role = roleService.getRoleById(id);
		if(role==null){
			throw new DataNotFoundException("get /roles/{id} failed because role with id("+id+") not found!");
		}
		return role;
	}

	/**
	 * 函数功能说明 ：修改用户 PUT http://127.0.0.1:8080/roles/{id}
	 * 
	 * @参数： User
	 * @return Role
	 * @throws
	 */
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Role updateRole(@PathVariable String id, @RequestBody Role role) {
		role.setId(id);
		int c = roleService.updateRole(role);
		if(c==0){
			throw new DataNotFoundException("put /roles/{id} failed because role with id("+id+") not found!");
		}
		return role;	
	}

	/**
	 * 函数功能说明 ：删除角色 DETELE http://127.0.0.1:8080/roles/{id}
	 * 
	 * @参数：
	 * @return int 
	 * @throws
	 */
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id) {
		int c=roleService.deleteRoleById(id);
		if(c==0){
			throw new DataNotFoundException("delete /roles/{id} failed because role with id("+id+") not found!");
		}
		return c;
	}

	/**
	 * 函数功能说明 ：批量删除用户 POST http://127.0.0.1:8080/auth/users/batch-delete
	 * 
	 * @参数： [{"id":"XXX"},{"id":"xxx"},...] 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/roles/batch-delete", method = RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids) {
		int c = roleService.deleteRolesByIds(ids);
		return c;
	}

	/**
	 * 函数功能说明 ： 查询所有角色
	 * http://127.0.0.1:8080/auth/roles/search?pageNum=1&pageSize=10
	 * 
	 * @参数：
	 * 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/roles/search", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Role> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestParam(required=false) String name,@RequestParam(required=false)String code,@RequestParam(required=false) String description) {
		Page page = new Page(pageNum,pageSize);
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
		PageInfo<Role> pageInfo=roleService.search(page.getPageNum(), page.getPageSize(),role);
		return pageInfo;
	}
	
	/**
	 * 函数功能说明 ： 查询所有角色
	 * http://127.0.0.1:8080/auth/roles/search?pageNum=1&pageSize=10
	 * 
	 * @参数：
	 * @return PageInfo<Role>
	 * @throws
	 */
	@RequestMapping(value = "/roles/search", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<Role> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestBody Role role) {
		Page page = new Page(pageNum,pageSize);
		PageInfo<Role> pageInfo=roleService.search(page.getPageNum(), page.getPageSize(),role);
		return pageInfo;
	}
	
}
