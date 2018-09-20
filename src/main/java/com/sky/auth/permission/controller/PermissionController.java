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
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.service.PermissionService;
import com.sky.auth.util.Page;
import com.sky.auth.util.StringUtil;

@Controller
public class PermissionController {
	public static Logger logger = LoggerFactory.getLogger(PermissionController.class);

	@Autowired
	private PermissionService permissionService;

	/**
	 * 函数功能说明 ： 查询所有权限 
	 * GET http://127.0.0.1:8085/permissions
	 * 
	 * @param：
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/permissions", method = { RequestMethod.GET })
	@ResponseBody
	public List<Permission> getAllPermissions() {
		List<Permission> permissions = (List<Permission>) permissionService.getAllPermissions();
		return permissions;
	}

	/**
	 * 函数功能说明 ： 添加权限 
	 * POST http://127.0.0.1:8085/permissions
	 * 
	 * @param Permission {"name":"xxx","resourceId":"xxx","operationId":"xxx","description":"xxx"}
	 * @throws
	 */
	@RequestMapping(value = "/permissions", method = RequestMethod.POST)
	@ResponseBody
	public Permission createPermission(@RequestBody Permission permission) {
		String id = StringUtil.get32UUID();
//		String resourceId = permission.getResourceId();
		//String operationId = permission.getOperationId();
		//int resourceType = permission.getResourceType();
		permission.setId(id);
		//Resource resource = resourceService.getResourceById(resourceId);
		//Operation operation = operationService.getOperationById(operationId);
		//String resourceCode = resource.getCode();
		//String operationCode = operation.getCode();
		//String permissionCode = resourceCode + ":" + operationCode;
		//permission.setCode(permissionCode);
		permissionService.addPermission(permission);
		return permission;
	}

	/**
	 * 函数功能说明 ： 查询权限信息 
	 * GET http://127.0.0.1:8085/permissions/{id}
	 * 
	 * 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/permissions/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Permission getPermission(@PathVariable String id) {
		Permission permission = permissionService.getPermissionById(id);
		if(permission==null){
			throw new DataNotFoundException("get /permissions/{id} failed because permission with id("+id+") not found!");
		}
		return permission;
	}

	/**
	 * 函数功能说明 ：修改权限信息
	 * PUT http://127.0.0.1:8085/permissions/{id}
	 * 
	 * @param： User
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/permissions/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Permission updatePermission(@PathVariable String id, @RequestBody Permission permission) {
		id = id.trim();
		permission.setId(id);
		int c = permissionService.updatePermission(permission);
		if(c==0){
			throw new DataNotFoundException("put /permissions/{id} failed because permission with id("+id+") not found!");
		}
		return permission;
	}

	/**
	 * 函数功能说明 ：删除权限 
	 * DETELE http://127.0.0.1:8085/permissions/{id}
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/permissions/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id) {
		int c= permissionService.deletePermissionById(id);
		if(c==0){
			throw new DataNotFoundException("delete /permissions/{id} failed because permission with id("+id+") not found!");
		}
		return c;
	}

	/**
	 * 函数功能说明 ：批量删除权限 
	 * POST http://127.0.0.1:8085/permissions/batch-delete
	 * 
	 * @param ["xxx","xxx",...] 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/permissions/batch-delete", method = RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids) {
		int c = permissionService.deletePermissionsByIds(ids);
		return c;
	}

	
	/**
	 * 函数功能说明 ： 查询所有权限
	 * http://127.0.0.1:8085/permissions/search?pageNum=1&pageSize=10
	 * 
	 * @param：
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/permissions/search", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Permission> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestParam(required=false)String name,@RequestParam(required=false)String code,@RequestParam(required=false)String description) {
		
		Page page = new Page(pageNum,pageSize);
		Permission permission = new Permission();
		if(name!=null){
			permission.setName(name);
		}
		if(code!=null){
			permission.setCode(code);
		}
		if(description!=null){
			permission.setDescription(description);
		}
			
		PageInfo<Permission> permissions = permissionService.search(page.getPageNum(),page.getPageSize(), permission);
		return permissions;
	}
	
	/**
	 * 函数功能说明 ： 查询所有角色
	 * http://127.0.0.1:8085/permissions/search?pageNum=1&pageSize=10
	 * 
	 * @param：
	 * @return
	 */
	@RequestMapping(value = "/permissions/search", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<Permission> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestBody Permission permission) {
		Page page = new Page(pageNum,pageSize);
		PageInfo<Permission> pageInfo=permissionService.search(page.getPageNum(),page.getPageSize(), permission);
		return pageInfo;
	}
	
}
