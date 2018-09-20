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
import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.RoleOperation;
import com.sky.auth.permission.service.RoleOperationService;
import com.sky.auth.util.StringUtil;

@Deprecated
@Controller
public class RoleOperationController {
public static Logger logger = LoggerFactory.getLogger(RoleOperationController.class);
	
	@Autowired
	private RoleOperationService roleOperationService;	
	
	/**
	 * 函数功能说明 ： 查询所有用户角色 
	 * GET http://127.0.0.1:8080/auth/role-operations
	 * 
	 * @参数： 
	 * @return {"code":0,"msg":"","data":[{"id":"b1b31fe3ed0a429891548c80faa0340a","version":0,"status":"ACTIVE","createTime":1533261863000,"roleId":"f8de908e0f9f49578965a960b3c4b5fb","operationId":"37f14b0c719d491981faeb4fcb05633e"}]} 
	 * @throws
	 */
	@RequestMapping(value = "/role-operations",method={RequestMethod.GET})
	@ResponseBody
	public List<RoleOperation> getAllUsers(){
		List<RoleOperation> roleOperations = (List<RoleOperation>)roleOperationService.getAllRoleOperations();
		return roleOperations;
	}
	
	/**
	 * 函数功能说明 ： 添加用户角色
	 * POST http://127.0.0.1:8080/auth/role-operations
	 * 
	 * @参数： User {"roleId":"XXX","operationId":"XXX"}
	 * @return Response {"code":"xxx","data":""}
	 * @throws
	 */
	@RequestMapping(value = "/role-operations",method=RequestMethod.POST)
	@ResponseBody
	public RoleOperation addRoleOperation(@RequestBody RoleOperation roleOperation){
		String id=StringUtil.get32UUID();
		roleOperation.setId(id);
		roleOperationService.addRoleOperation(roleOperation);
		return roleOperation;
	}
	
	/**
	 * 函数功能说明 ： 通过id查询用户角色
	 * GET http://127.0.0.1:8080/auth/role-operations/{id}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"b1b31fe3ed0a429891548c80faa0340a","version":0,"status":"ACTIVE","createTime":1533261863000,"roleId":"f8de908e0f9f49578965a960b3c4b5fb","operationId":"37f14b0c719d491981faeb4fcb05633e"}}
	 * @throws
	 */
	@RequestMapping(value = "/role-operations/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RoleOperation getRoleOperationById(@PathVariable String id){
		RoleOperation roleOperation = roleOperationService.getRoleOperationById(id);
		if(roleOperation==null){
			throw new DataNotFoundException("get /role-operations/{id} failed because role-operation with id("+id+") not found!");
		}
		return roleOperation;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有角色
	 * GET http://127.0.0.1:8080/auth/role-operations/roles/{roleId}/permissions
	 * 
	 * @参数：
	 * @return {"code":0,"msg":"","data":[{"id":"37f14b0c719d491981faeb4fcb05633e","version":1,"status":"ACTIVE","createTime":1533261369000,"resourceId":"180b6b9033b6431485384c9b7bcc9b02","operationId":"258a0bbdd8d945ea97f1d018e4169c6d","name":"update system user","code":"sys:user:update","description":"update system user privilege"}]}
	 * @throws
	 */
	@RequestMapping(value = "/role-operations/roles/{roleId}/operations",method=RequestMethod.GET)
	@ResponseBody
	public List<Operation> getRolesByUserId(@PathVariable String roleId){
		List<Operation> permissions = roleOperationService.getOperationsByRoleId(roleId);
		return permissions;
	}
	
	/**
	 * 函数功能说明 ： 查询用户的指定角色
	 * GET http://127.0.0.1:8080/auth/role-operations/permissions/{operationId}/roles/{roleId}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","roleCode":"admin","roleName":"超级管理员角色"}}
	 * @throws
	 */
	@RequestMapping(value = "/role-operations/roles/{roles}/operations/{operationId}",method=RequestMethod.GET)
	@ResponseBody
	public RoleOperation getRoleOperationByRoleIdAndOperationId(@PathVariable String operationId,@PathVariable String roleId){
		RoleOperation roleOperation = roleOperationService.getRoleOperationByRoleIdAndOperationId(roleId,operationId);
		return roleOperation;
	}
	
	/**
	 * 函数功能说明 ： 查询拥有角色的用户
	 * GET http://127.0.0.1:8080/auth/role-operations/permissions/{operationId}/roles
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":[{"id":"f8de908e0f9f49578965a960b3c4b5fb","version":0,"status":"ACTIVE","createTime":1533197619000,"code":"caiwu","name":"caiwujuese","description":"ABCDEDF"}]}
	 * @throws
	 */
	@RequestMapping(value = "/role-operations/operations/{operationId}/roles",method=RequestMethod.GET)
	@ResponseBody
	public List<Role> getOperationsByRoleId(@PathVariable String operationId){
		List<Role> roles = roleOperationService.getRolesByOperationId(operationId);
		return roles;
	}
	
	/**
	 * 函数功能说明 ：删除用户角色
	 * DETELE http://127.0.0.1:8080/auth/role-operations/{id}
	 * 
	 * @参数： {"id":"XXX"}
	 * @return {"code":0,msg:null,data:{}}
	 * @throws
	 */
	@RequestMapping(value = "/role-operations/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id){
		int c = roleOperationService.deleteRoleOperationById(id);
		return c;
	}
	
	/**
	 * 函数功能说明 ：删除用户角色
	 * DETELE http://127.0.0.1:8080/auth/role-operations/{id}
	 * 
	 * @参数： {"id":"XXX"}
	 * @return {"code":0,msg:null,data:{}}
	 * @throws
	 */
	@RequestMapping(value = "/role-operations/roles/{roleId}/operations/{operationId}",method=RequestMethod.DELETE)
	@ResponseBody
	public int deleteRoleOperationByRoleIdAndOperationId(@PathVariable String roleId,@PathVariable String operationId){
		int c = roleOperationService.deleteRoleOperationByRoleIdAndOperationId(roleId,operationId);
		return c;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有角色
	 * GET http://127.0.0.1:8080/auth/user-roles/users/{userId}/roles?pageNum=1&pageSize=5
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":[{"id":"f8de908e0f9f49578965a960b3c4b5fb","version":0,"status":"ACTIVE","createTime":1533197619000,"code":"caiwu","name":"caiwujuese","description":"ABCDEDF"}]}
	 * @throws
	 */
	@RequestMapping(value = "/role-operations/roles/{roleId}/operations/search",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<Operation> getRolesByUserId(@PathVariable String roleId,@RequestParam String pageNum,@RequestParam String pageSize,@RequestBody(required=false) Operation operation){
		PageInfo<Operation> operationPageBean = roleOperationService.search(Integer.parseInt(pageNum),Integer.parseInt(pageSize),roleId, operation);
		return operationPageBean;
	}
	
	/**
	 * 函数功能说明 ：批量删除用户角色
	 * POST http://127.0.0.1:8080/auth/users/batch-delete
	 * 
	 * @参数： [{"id":"XXX"},{"id":"xxx"},...]
	 * @return {"code":0,msg:null,data:null}
	 * @throws
	 */
	@RequestMapping(value = "/role-operations/batch-delete",method=RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids){
		int c = roleOperationService.deleteRoleOperationByIds(ids);
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量删除用户角色
	 * POST http://127.0.0.1:8080/auth/users/batch-delete
	 * 
	 * @参数： [{"id":"XXX"},{"id":"xxx"},...]
	 * @return {"code":0,msg:null,data:null}
	 * @throws
	 */
	@RequestMapping(value = "/role-operations/batch-add",method=RequestMethod.POST)
	@ResponseBody
	public int batchAdd(@RequestBody List<RoleOperation> roleOperations){
		
		for(int i=0;i<roleOperations.size();i++){
			RoleOperation roleOperation = roleOperations.get(i);
			String id = StringUtil.get32UUID();
			roleOperation.setId(id);
		}
		int c=roleOperationService.addRoleOperations(roleOperations);
		return c;
	}
	
	/**
	 * 函数功能说明 ： 查询所有角色 
	 * http://127.0.0.1:8080/auth/user/search?pageNum=1&pageSize=10
	 * 
	 * @参数： {"page":{"pageSize":10,"pageNum":2},"user":{"roleName":"yangfan"}}
	 * @return {"code":0,"msg":"","data":{"pageNum":1,"pageSize":10,"totalCount":1,"dataList":[{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","roleCode":"admin","roleName":"超级管理员角色"}],"totalPage":1,"beginPageIndex":1,"endPageIndex":1,"countResultMap":null}}
	 * @throws
	 */
	/*
	@RequestMapping(value = "/role-operations/search",method=RequestMethod.POST)
	@ResponseBody
	public Response search(@RequestParam String pageNum,@RequestParam String pageSize,@RequestParam String sortby,@RequestParam String order,@RequestBody RoleOperation roleOperation){
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
			
			
			PageBean<RoleOperation> userPageBean = roleOperationService.search(page, roleOperation);
			response = new Response(userPageBean);
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
