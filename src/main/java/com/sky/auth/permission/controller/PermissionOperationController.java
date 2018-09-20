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
import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.domain.PermissionOperation;
import com.sky.auth.permission.service.PermissionOperationService;
import com.sky.auth.util.StringUtil;

@Controller
public class PermissionOperationController {
public static Logger logger = LoggerFactory.getLogger(PermissionOperationController.class);
	
	@Autowired
	private PermissionOperationService permissionOperationService;	
	
	/**
	 * 函数功能说明 ： 查询所有权限操作
	 * GET http://127.0.0.1:8085/permission-operations
	 * 
	 */
	@RequestMapping(value = "/permission-operations",method={RequestMethod.GET})
	@ResponseBody
	public List<PermissionOperation> getAllPermissions(){
		List<PermissionOperation> permissionOperations = (List<PermissionOperation>)permissionOperationService.getAllPermissionOperations();
		return permissionOperations;
	}
	
	/**
	 * 函数功能说明 ： 添加权限操作
	 * POST http://127.0.0.1:8085/auth/permission-operations
	 * 
	 * @throws
	 */
	@RequestMapping(value = "/permission-operations",method=RequestMethod.POST)
	@ResponseBody
	public PermissionOperation addPermission(@RequestBody PermissionOperation permissionOperation){
		String id=StringUtil.get32UUID();
		permissionOperation.setId(id);
		permissionOperationService.addPermissionOperation(permissionOperation);
		return permissionOperation;
	}
	
	/**
	 * 函数功能说明 ：删除权限操作
	 * DETELE http://127.0.0.1:8085/auth/permission-operations/{id}
	 * 
	 */
	@RequestMapping(value = "/permission-operations/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id){
		
		int c = permissionOperationService.deletePermissionOperationById(id);
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量添加权限操作
	 * POST http://127.0.0.1:8085/auth/permissions/batch-add
	 * 
	 */
	@RequestMapping(value = "/permission-operations/batch-add",method=RequestMethod.POST)
	@ResponseBody
	public int batchAdd(@RequestBody List<PermissionOperation> permissionOperations){
		
		for(int i=0;i<permissionOperations.size();i++){
			PermissionOperation permissionOperation = permissionOperations.get(i);
			String id = StringUtil.get32UUID();
			permissionOperation.setId(id);
		}
		int c=permissionOperationService.addPermissionOperations(permissionOperations);
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量删除权限操作
	 * POST http://127.0.0.1:8085/auth/permissions/batch-delete
	 * 
	 */
	@RequestMapping(value = "/permission-operations/batch-delete",method=RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids){
		
		int c = permissionOperationService.deletePermissionOperationByIds(ids);
		return c;	
	}
	
	/**
	 * 函数功能说明 ： 查询权限拥有的操作
	 * GET http://127.0.0.1:8085/permission-operations/permissions/{permissionId}/operations
	 * 
	 */
	@RequestMapping(value = "/permissions/{permissionId}/operations",method=RequestMethod.GET)
	@ResponseBody
	public List<Operation> getOperationsByPermissionId(@PathVariable String permissionId){
		
		List<Operation> operations = permissionOperationService.getOperationsByPermissionId(permissionId);
		return operations;
	}
	
	
	/**
	 * 函数功能说明 ：  查询权限拥有的操作
	 * POST http://127.0.0.1:808/permissions/{permissionId}/operations/search?pageNum=1&pageSize=5
	 * 
	 */
	@RequestMapping(value = "/permissions/{permissionId}/operations/search",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<Operation> getOperationsByPermissionId(@PathVariable String permissionId,@RequestParam String pageNum,@RequestParam String pageSize,@RequestBody(required=false) Operation operation){
		permissionId=permissionId.trim();
		
		PageInfo<Operation> operationPageBean = permissionOperationService.search(Integer.parseInt(pageNum),Integer.parseInt(pageSize),permissionId, operation);
		return operationPageBean;
	}
	
	
	/**
	 * 函数功能说明 ： 删除权限操作
	 * DELETE http://127.0.0.1:8085/permissions/{permissionId}/operations/{operationId}
	 * 
	 */
	@RequestMapping(value = "/permissions/{permissionId}/operations/{operationId}",method=RequestMethod.DELETE)
	@ResponseBody
	public int deletePermissionOperationByPermissionIdAndOperationId(@PathVariable String permissionId,@PathVariable String operationId){
		
		int c = permissionOperationService.deletePermissionOperationByPermissionIdAndOperationId(permissionId,operationId);
		return c;
	}
	
	
	
	
	/**
	 * 函数功能说明 ： 权限不含有的操作
	 * http://127.0.0.1:8085/permissions/search-left?pageNum=1&pageSize=10&userId=xxx
	 * 
	 */
	@RequestMapping(value = "/permissions/{permissionId}/operations/search-left", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<Operation> searchOperationsNotAssignedToPermission(@RequestParam String pageNum, @RequestParam String pageSize, @PathVariable String permissionId,@RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestBody Operation operation) {
		PageInfo<Operation> pageInfo=permissionOperationService.searchLeftOperation(Integer.parseInt(pageNum), Integer.parseInt(pageSize),permissionId,operation);
		return pageInfo;
	}
	
}
