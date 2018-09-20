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
import com.sky.auth.permission.service.OperationService;
import com.sky.auth.util.Page;
import com.sky.auth.util.StringUtil;

@Controller
public class OperationController {
	public static Logger logger = LoggerFactory.getLogger(OperationController.class);

	@Autowired
	private OperationService operationService;

	/**
	 * 函数功能说明 ： 查询所有操作 
	 * GET http://127.0.0.1:8085/operations
	 * 
	 * @参数：
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/operations", method = { RequestMethod.GET })
	@ResponseBody
	public List<Operation> getAllOperations() {
		List<Operation> operations = (List<Operation>) operationService.getAllOperations();
		return operations;
	}

	/**
	 * 函数功能说明 ： 添加操作 
	 * POST http://127.0.0.1:8085/operations
	 * 
	 * @param Operation
	 * @return Operation {"id":"258a0bbdd8d945ea97f1d018e4169c6d","version":1,"status":"ACTIVE","createTime":1533258625000,"name":"update","code":"update","description":"update operation"}
	 * @throws
	 */
	@RequestMapping(value = "/operations", method = RequestMethod.POST)
	@ResponseBody
	public Operation createOperation(@RequestBody Operation operation) {
		String id = StringUtil.get32UUID();
		operation.setId(id);
		operationService.addOperation(operation);
		return operation;
	}

	/**
	 * 函数功能说明 ： 查询操作信息
	 * GET http://127.0.0.1:8085/operations/{id}
	 * 
	 * @参数：id String
	 * @return 
	 */
	@RequestMapping(value = "/operations/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Operation getOperation(@PathVariable String id) {
		Operation operation = operationService.getOperationById(id.trim());
		if(operation==null){
			throw new DataNotFoundException("get /operations/{id} failed because operation with id("+id+") not found!");
		}
		return operation;
	}

	/**
	 * 函数功能说明 ：修改操作信息
	 * PUT http://127.0.0.1:8085/operations/{id}
	 * 
	 * @参数： Operation
	 * @return Operation
	 * @throws
	 */
	@RequestMapping(value = "/operations/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Operation updateOperation(@PathVariable String id, @RequestBody Operation operation) {
		operation.setId(id);
		int c=operationService.updateOperation(operation);
		if(c==0){
			throw new DataNotFoundException("put /operations/{id} failed because operation with id("+id+") not found!");
		}
		return operation;
	}		

	/**
	 * 函数功能说明 ：删除操作
	 * DETELE http://127.0.0.1:8085/operations/{id}
	 * 
	 * @参数： id String
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/operations/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id) {
		int c = operationService.deleteOperationById(id);
		if(c==0){
			throw new DataNotFoundException("delete /operations/{id} failed because operation with id("+id+") not found!");
		}
		return c;
	}

	/**
	 * 函数功能说明 ：批量删除操作
	 * POST http://127.0.0.1:8085/operations/batch-delete
	 * 
	 * @参数： ["xxx","yyy",...] 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/operations/batch-delete", method = RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids) {
		int c = operationService.deleteOperationsByIds(ids);
		return c;
	}
	
	/**
	 * 函数功能说明 ： 查询所有操作
	 * http://127.0.0.1:8085/operations/search?pageNum=1&pageSize=10
	 * 
	 * @参数：
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/operations/search", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Operation> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestParam(required=false)String name,@RequestParam(required=false)String code,@RequestParam(required=false)String description) {
		Page page = new Page(pageNum,pageSize);
		Operation operation = new Operation();
		if(name!=null){
			operation.setName(name);
		}
		if(code!=null){
			operation.setCode(code);
		}
		if(description!=null){
			operation.setDescription(description);
		}
		PageInfo<Operation> operations = operationService.search(page.getPageNum(),page.getPageSize(), operation);
		return operations;
	}
	
	/**
	 * 函数功能说明 ： 查询所有操作
	 * http://127.0.0.1:8085/operations/search?pageNum=1&pageSize=10
	 * 
	 * @参数：
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/operations/search", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<Operation> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestBody Operation operation) {
		Page page = new Page(pageNum,pageSize);
		PageInfo<Operation> userPageBean = operationService.search(page.getPageNum(),page.getPageSize(), operation);
		return userPageBean;
	}
	
}
