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
import com.sky.auth.permission.domain.ResourcePermission;
import com.sky.auth.permission.service.ResourcePermissionService;
import com.sky.auth.util.StringUtil;

@Controller
public class ResourcePermissionController {
public static Logger logger = LoggerFactory.getLogger(ResourcePermissionController.class);

	@Autowired
	private ResourcePermissionService resourcePermissionService;	
	
	/**
	 * 函数功能说明 ： 查询所有用户角色 
	 * GET http://127.0.0.1:8080/auth/resource-permissions
	 * 
	 * @参数： 
	 * @return {"code":0,"msg":"","data":[{"id":"dabf93c0be344d6e83f19842013d9105","version":0,"status":"ACTIVE","createTime":1533204913000,"permissionId":"f8de908e0f9f49578965a960b3c4b5fb","resourceId":"5e6c851bfe8b4685a9d025b9fc991f93"}]}
	 * @throws
	 */
	@RequestMapping(value = "/resource-permissions",method={RequestMethod.GET})
	@ResponseBody
	public List<ResourcePermission> getAllResources(){
		List<ResourcePermission> resourcePermissions = (List<ResourcePermission>)resourcePermissionService.getAllResourcePermissions();
		return resourcePermissions;
	}
	
	/**
	 * 函数功能说明 ： 添加用户角色
	 * POST http://127.0.0.1:8080/auth/resource-permissions
	 * 
	 * @参数： Resource {"resourceId":"XXX","permissionId":"XXX"}
	 * @return Response {"code":"xxx","data":""}
	 * @throws
	 */
	@RequestMapping(value = "/resource-permissions",method=RequestMethod.POST)
	@ResponseBody
	public ResourcePermission addResource(@RequestBody ResourcePermission resourcePermission){
		String id=StringUtil.get32UUID();
		resourcePermission.setId(id);
		resourcePermissionService.addResourcePermission(resourcePermission);
		return resourcePermission;
	}
	
	/**
	 * 函数功能说明 ： 通过id查询用户角色
	 * GET http://127.0.0.1:8080/auth/resource-permissions/{id}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"xxx","resourceId":"xxx","permissionId":"xxx"}}
	 * @throws
	 */
	@RequestMapping(value = "/resource-permissions/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResourcePermission getResourcePermissionById(@PathVariable String id){
		
		ResourcePermission resourcePermission = resourcePermissionService.getResourcePermissionById(id);
		if(resourcePermission==null){
			throw new DataNotFoundException("get /resource-permissions/{id} failed because resource-permission with id("+id+") not found!");
		}
		return resourcePermission;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有角色
	 * GET http://127.0.0.1:8080/auth/resource-permissions/resources/{resourceId}/permissions
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":[{"id":"f8de908e0f9f49578965a960b3c4b5fb","version":0,"status":"ACTIVE","createTime":1533197619000,"code":"caiwu","name":"caiwujuese","description":"ABCDEDF"}]}
	 * @throws
	 */
	@RequestMapping(value = "/resource-permissions/resources/{resourceId}/permissions",method=RequestMethod.GET)
	@ResponseBody
	public List<Permission> getPermissionsByResourceId(@PathVariable String resourceId){
		
		List<Permission> permissions = resourcePermissionService.getPermissionsByResourceId(resourceId);
		return permissions;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有角色
	 * GET http://127.0.0.1:8080/auth/resource/{resourceId}/permissions?pageNum=1&pageSize=5
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":[{"id":"f8de908e0f9f49578965a960b3c4b5fb","version":0,"status":"ACTIVE","createTime":1533197619000,"code":"caiwu","name":"caiwujuese","description":"ABCDEDF"}]}
	 * @throws
	 */
	/*
	@RequestMapping(value = "/resource-permissions/resources/{resourceId}/permissions",method=RequestMethod.GET)
	@ResponseBody
	public Response getPermissionsByResourceId(@PathVariable String resourceId,@RequestParam String pageNum,@RequestParam String pageSize,@RequestParam(required=false) String permissionName,@RequestParam(required=false) String permissionCode){
		resourceId=resourceId.trim();
		if("".equals(resourceId)){
			Response response = new Response(PermissionException.ADD_ROLE_INVALID_PARAM,PermissionException.ADD_ROLE_INVALID_PARAM_MSG);
			return response;
		}
		Response response = null;
		try{
			Permission permission = new Permission();
			if(permissionName!=null&&!"".equals(permissionName)){
				permission.setName(permissionName);
			}
			if(permissionCode!=null&&!"".equals(permissionCode)){
				permission.setCode(permissionCode);
			}
			PageInfo<Permission> permissionPageBean = resourcePermissionService.search(Integer.parseInt(pageNum),Integer.parseInt(pageSize),resourceId, permission);
			response = new Response(permissionPageBean);
			return response;
		}catch(BizException e){
			logger.error("修改角色错误 ",e);
			response = new Response(e.getCode(),e.getMessage());
			return response;
		}finally{
			
		}
	}
	*/
	
	/**
	 * 函数功能说明 ： 查询用户所有角色
	 * GET http://127.0.0.1:8080/auth/resource-permissions/resources/{resourceId}/permissions?pageNum=1&pageSize=5
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":[{"id":"f8de908e0f9f49578965a960b3c4b5fb","version":0,"status":"ACTIVE","createTime":1533197619000,"code":"caiwu","name":"caiwujuese","description":"ABCDEDF"}]}
	 * @throws
	 */
	@RequestMapping(value = "/resource-permissions/resources/{resourceId}/permissions",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<Permission> getPermissionsByResourceId(@PathVariable String resourceId,@RequestParam String pageNum,@RequestParam String pageSize,@RequestBody(required=false) Permission permission){
		resourceId=resourceId.trim();
		
		PageInfo<Permission> permissionPageBean = resourcePermissionService.search(Integer.parseInt(pageNum),Integer.parseInt(pageSize),resourceId, permission);
		return permissionPageBean;
	}
	
	
	/**
	 * 函数功能说明 ： 查询用户的指定角色
	 * GET http://127.0.0.1:8080/auth/resource-permissions/resources/{resourceId}/permissions/{permissionId}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","permissionCode":"admin","permissionName":"超级管理员角色"}}
	 * @throws
	 */
	@RequestMapping(value = "/resource-permissions/resources/{resourceId}/permissions/{permissionId}",method=RequestMethod.GET)
	@ResponseBody
	public ResourcePermission getResourcePermissionByResourceIdAndPermissionId(@PathVariable String resourceId,@PathVariable String permissionId){
		ResourcePermission resourcePermission = resourcePermissionService.getResourcePermissionByResourceIdAndPermissionId(resourceId,permissionId);
		return resourcePermission;
	}
	
	/**
	 * 函数功能说明 ： 查询用户的指定角色
	 * GET http://127.0.0.1:8080/auth/resource-permissions/resources/{resourceId}/permissions/{permissionId}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","permissionCode":"admin","permissionName":"超级管理员角色"}}
	 * @throws
	 */
	@RequestMapping(value = "/resource-permissions/resources/{resourceId}/permissions/{permissionId}",method=RequestMethod.DELETE)
	@ResponseBody
	public int deleteResourcePermissionByResourceIdAndPermissionId(@PathVariable String resourceId,@PathVariable String permissionId){
		
		int c = resourcePermissionService.deleteResourcePermissionByResourceIdAndPermissionId(resourceId,permissionId);
		return c;
	}
	
	
	/**
	 * 函数功能说明 ：删除用户角色
	 * DETELE http://127.0.0.1:8080/auth/resource-permissions/{id}
	 * 
	 * @参数： {"id":"XXX"}
	 * @return {"code":0,msg:null,data:{}}
	 * @throws
	 */
	@RequestMapping(value = "/resource-permissions/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id){
		
		int c = resourcePermissionService.deleteResourcePermissionById(id);
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量添加用户角色
	 * POST http://127.0.0.1:8080/auth/resources/batch-add
	 * 
	 * @参数： [{"resourceId":"XXX","permissionId":"xxx"},{"resourceId":"xxx","permissionId":"xxx"},...]
	 * @return {"code":0,msg:null,data:null}
	 * @throws
	 */
	@RequestMapping(value = "/resource-permissions/batch-add",method=RequestMethod.POST)
	@ResponseBody
	public int batchAdd(@RequestBody List<ResourcePermission> resourcePermissions){
		
		for(int i=0;i<resourcePermissions.size();i++){
			ResourcePermission resourcePermission = resourcePermissions.get(i);
			String id = StringUtil.get32UUID();
			resourcePermission.setId(id);
		}
		int c=resourcePermissionService.addResourcePermissions(resourcePermissions);
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量删除用户角色
	 * POST http://127.0.0.1:8080/auth/resources/batch-delete
	 * 
	 * @参数： [{"id":"XXX"},{"id":"xxx"},...]
	 * @return {"code":0,msg:null,data:null}
	 * @throws
	 */
	@RequestMapping(value = "/resource-permissions/batch-delete",method=RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids){
		
		int c = resourcePermissionService.deleteResourcePermissionByIds(ids);
		return c;	
	}
	
	/**
	 * 函数功能说明 ： 查询所有角色 
	 * http://127.0.0.1:8080/auth/resource/search?pageNum=1&pageSize=10
	 * 
	 * @参数： {"page":{"pageSize":10,"pageNum":2},"resource":{"permissionName":"yangfan"}}
	 * @return {"code":0,"msg":"","data":{"pageNum":1,"pageSize":10,"totalCount":1,"dataList":[{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","permissionCode":"admin","permissionName":"超级管理员角色"}],"totalPage":1,"beginPageIndex":1,"endPageIndex":1,"countResultMap":null}}
	 * @throws
	 */
	/*
	@RequestMapping(value = "/resource-permissions/search",method=RequestMethod.POST)
	@ResponseBody
	public Response search(@RequestParam String pageNum,@RequestParam String pageSize,@RequestParam String sortby,@RequestParam String order,@RequestBody ResourcePermission resourcePermission){
		logger.debug("查询用户列表");
		Response response = null;
		try{
			PageParam page = new PageParam();
			if(pageNum!=null){
				page.setPageNum(Integer.parseInt(pageNum));
			}
			if(pageSize!=null){
				page.setPageSize(Integer.parseInt(pageSize));
			}
			
			
			PageBean<ResourcePermission> resourcePageBean = resourcePermissionService.search(page, resourcePermission);
			response = new Response(resourcePageBean);
			return response;
			
		}catch(Exception e){
			logger.error("查询用户列表错误 ",e);
			response = new Response(e.getMessage());
			return response;
		}finally{
			
		}
	}
	*/
	
}
