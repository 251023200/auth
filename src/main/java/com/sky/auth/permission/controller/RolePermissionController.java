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
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.RolePermission;
import com.sky.auth.permission.service.RolePermissionService;
import com.sky.auth.util.Page;
import com.sky.auth.util.StringUtil;

@Controller
public class RolePermissionController {
public static Logger logger = LoggerFactory.getLogger(RolePermissionController.class);
	
	@Autowired
	private RolePermissionService rolePermissionService;	
	
	/**
	 * 函数功能说明 ： 查询所有角色权限
	 * GET http://127.0.0.1:8085/role-permissions
	 * 
	 * @param 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/role-permissions",method={RequestMethod.GET})
	@ResponseBody
	public List<RolePermission> getAllUsers(){
		List<RolePermission> rolePermissions = (List<RolePermission>)rolePermissionService.getAllRolePermissions();
		return rolePermissions;
	}
	
	/**
	 * 函数功能说明 ： 添加角色权限
	 * POST http://127.0.0.1:8085/role-permissions
	 * 
	 * @param
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/role-permissions",method=RequestMethod.POST)
	@ResponseBody
	public RolePermission addRolePermission(@RequestBody RolePermission rolePermission){
		String id=StringUtil.get32UUID();
		rolePermission.setId(id);
		rolePermissionService.addRolePermission(rolePermission);
		return rolePermission;
	}
	
	/**
	 * 函数功能说明 ： 通过id查询角色权限
	 * GET http://127.0.0.1:8085/role-permissions/{id}
	 * 
	 * @param 
	 * @return  
	 * @throws
	 */
	@RequestMapping(value = "/role-permissions/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RolePermission getRolePermissionById(@PathVariable String id){
		RolePermission rolePermission = rolePermissionService.getRolePermissionById(id);
		if(rolePermission==null){
			throw new DataNotFoundException("get /role-permissions/{id} failed because role-permission with id("+id+") not found!");
		}
		return rolePermission;
	}
	
	/**
	 * 函数功能说明 ：删除角色权限
	 * DETELE http://127.0.0.1:8085/role-permissions/{id}
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/role-permissions/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id){
		int c=rolePermissionService.deleteRolePermissionById(id);
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量添加角色权限
	 * POST http://127.0.0.1:8085/role-permissions/batch-add
	 * 
	 * @param： [{"permissionId":"XXX","operationId":"xxx"},{"permissionId":"xxx","operationId":"xxx"},...]
	 * @return {"code":0,msg:null,data:null}
	 * @throws
	 */
	@RequestMapping(value = "/role-permissions/batch-add",method=RequestMethod.POST)
	@ResponseBody
	public int batchAdd(@RequestBody List<RolePermission> rolePermissions){
		
		for(int i=0;i<rolePermissions.size();i++){
			RolePermission rolePermission = rolePermissions.get(i);
			String id = StringUtil.get32UUID();
			rolePermission.setId(id);
		}
		int c=rolePermissionService.addRolePermissions(rolePermissions);
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量删除角色权限
	 * POST http://127.0.0.1:8085/role-permissions/batch-delete
	 * 
	 * @param ["xxx","xxx",...]
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/role-permissions/batch-delete",method=RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids){
		int c = rolePermissionService.deleteRolePermissionByIds(ids);
		return c;
	}
	
	/**
	 * 函数功能说明 ： 查询角色拥有的权限
	 * GET http://127.0.0.1:8085/roles/{roleId}/permissions
	 * 
	 * @param
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/roles/{roleId}/permissions",method=RequestMethod.GET)
	@ResponseBody
	public List<Permission> getRolesByUserId(@PathVariable String roleId){
		List<Permission> permissions = rolePermissionService.getPermissionsByRoleId(roleId);
		return permissions;
	}
	
	
	/**
	 * 函数功能说明 ： 查询角色的某个权限
	 * DELETE http://127.0.0.1:8085/roles/{roleId}/permissions/{permissionId}
	 * 
	 * @param
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/roles/{roleId}/permissions/{permissionId}",method=RequestMethod.DELETE)
	@ResponseBody
	public int deleteRolePermissionByUserIdAndRoleId(@PathVariable String roleId,@PathVariable String permissionId){
		int deletedCount = rolePermissionService.deleteRolePermissionByRoleIdAndPermissionId(roleId,permissionId);
		return deletedCount;
	}
	
	/**
	 * 函数功能说明 ： 查询拥有权限的角色
	 * GET http://127.0.0.1:8085/permissions/{permissionId}/roles
	 * 
	 * @param
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/permissions/{permissionId}/roles",method=RequestMethod.GET)
	@ResponseBody
	public List<Role> getUsersByRoleId(@PathVariable String permissionId){
		List<Role> roles = rolePermissionService.getRolesByPermissionId(permissionId);
		return roles;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有权限
	 * GET http://127.0.0.1:8085/roles/{roleId}/permissions/search?pageNum=1&pageSize=5
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/roles/{roleId}/permissions/search",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<Permission> getPermissionsByRoleId(@PathVariable String roleId,@RequestParam(required=false) String pageNum,@RequestParam(required=false) String pageSize,@RequestParam(required=false) String name,@RequestParam(required=false) String code){
		Page page = new Page(pageNum,pageSize);
		roleId=roleId.trim();	
		Permission permission = new Permission();
		permission.setName(name);
		permission.setCode(code);
		PageInfo<Permission> permissionPageBean = rolePermissionService.searchPermissions(page.getPageNum(),page.getPageSize(),roleId, permission);
		return permissionPageBean;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有权限
	 * GET http://127.0.0.1:8085/roles/{roleId}/permissions?pageNum=1&pageSize=5
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/roles/{roleId}/permissions/search",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<Permission> getPermissionsByRoleId(@PathVariable String roleId,@RequestParam String pageNum,@RequestParam String pageSize,@RequestBody(required=false) Permission permission){
		Page page = new Page(pageNum,pageSize);
		roleId=roleId.trim();	
		PageInfo<Permission> permissionPageBean = rolePermissionService.searchPermissions(page.getPageNum(),page.getPageSize(),roleId, permission);
		return permissionPageBean;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有权限
	 * GET http://127.0.0.1:8085/roles/{roleId}/permissions?pageNum=1&pageSize=5
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/roles/{roleId}/permissions/search-left",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<Permission> getPermissionsNotAssignedToRoles(@PathVariable String roleId,@RequestParam String pageNum,@RequestParam String pageSize,@RequestBody(required=false) Permission permission){
		Page page = new Page(pageNum,pageSize);
		roleId=roleId.trim();	
		PageInfo<Permission> permissionPageBean = rolePermissionService.searchLeftPermissions(page.getPageNum(),page.getPageSize(),roleId, permission);
		return permissionPageBean;
	}
	
	
}
