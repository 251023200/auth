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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky.auth.exception.DataNotFoundException;
import com.sky.auth.permission.domain.ResourceType;
import com.sky.auth.permission.dto.ResourceTypeTreeNode;
import com.sky.auth.permission.service.ResourceTypeService;
import com.sky.auth.permission.util.ResourceTypeTreeUtil;
import com.sky.auth.util.StringUtil;

@Controller
public class ResourceTypeController {
	public static Logger logger = LoggerFactory.getLogger(ResourceTypeController.class);

	@Autowired
	private ResourceTypeService resourceTypeService;

	/**
	 * 函数功能说明 ： 查询所有菜单 GET http://127.0.0.1:8080/resourceTypes
	 * 
	 * @参数：
	 * 
	 */
	@RequestMapping(value = "/resourceTypes", method = { RequestMethod.GET })
	@ResponseBody
	public List<ResourceType> getAllResourceTypes() {
		List<ResourceType> resourceTypes = (List<ResourceType>) resourceTypeService.getAllResourceTypes();
		return resourceTypes;
	}

	/**
	 * 函数功能说明 ： 添加菜单 POST http://127.0.0.1:8080/resourceTypes
	 * 
	 * @param ResourceType 
	 * @throws
	 */
	@RequestMapping(value = "/resourceTypes", method = RequestMethod.POST)
	@ResponseBody
	public ResourceType createResourceType(@RequestBody ResourceType resourceType) {
		String id = StringUtil.get32UUID();
		resourceType.setId(id);
		resourceTypeService.addResourceType(resourceType);
		return resourceType;
	}

	/**
	 * 函数功能说明 ： 查询所有用户 GET http://127.0.0.1:8080/resourceTypes/{id}
	 * 
	 * @参数：{"id":"XXX"}
	 * 
	 */
	@RequestMapping(value = "/resourceTypes/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResourceType getResourceType(@PathVariable String id) {
		ResourceType resourceType = resourceTypeService.getResourceTypeById(id);
		if(resourceType==null){
			throw new DataNotFoundException("get /resourceTypes/{id} failed because resourceType with id("+id+") not found!");
		}
		return resourceType;
	}

	/**
	 * 函数功能说明 ：修改用户 PUT http://127.0.0.1:8080/resourceTypes/{id}
	 * 
	 */
	@RequestMapping(value = "/resourceTypes/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResourceType updateResourceType(@PathVariable String id, @RequestBody ResourceType resourceType) {
		id = id.trim();
		resourceType.setId(id);
		int c = resourceTypeService.updateResourceType(resourceType);
		if(c==0){
			throw new DataNotFoundException("put /resourceTypes/{id} failed because resourceType with id("+id+") not found!");
		}
		return resourceType;
	}

	/**
	 * 函数功能说明 ：删除菜单 DETELE http://127.0.0.1:8080/resourceTypes/{id}
	 * 
	 * @参数： {"id":"XXX"} 
	 * @return {"code":0,msg:null,data:null} @throws
	 */
	@RequestMapping(value = "/resourceTypes/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id) {
		int c = resourceTypeService.deleteResourceTypeById(id);
		if(c==0){
			throw new DataNotFoundException("delete /resourceTypes/{id} failed because resourceType with id("+id+") not found!");
		}
		return c;
	}
	
	/**
	 * 函数功能说明 ：删除菜单 DETELE http://127.0.0.1:8080/resourceTypes/{id}/cascade
	 * 
	 * @参数： {"id":"XXX"} 
	 * @return {"code":0,msg:null,data:null} @throws
	 */
	@RequestMapping(value = "/resourceTypes/{id}/cascade", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteCascade(@PathVariable String id) {
		//TODO
//		int c = resourceTypeService.cascadeDeleteResourceTypes(id);
//		if(c==0){
//			throw new DataNotFoundException("delete /resourceTypes/{id}/cascade failed because resourceType with id("+id+") not found!");
//		}
//		return c;	
		return 0;
	}

	/**
	 * 函数功能说明 ：批量删除用户 POST http://127.0.0.1:8080/auth/users/batch-delete
	 * 
	 * @参数： [{"id":"XXX"},{"id":"xxx"},...] 
	 * @return
	 * {"code":0,msg:null,data:null}
	 * @throws
	 */
	@RequestMapping(value = "/resourceTypes/batch-delete", method = RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids) {
		int c = resourceTypeService.deleteResourceTypesByIds(ids);
		return c;
	}

	@RequestMapping(value = "/resourceTypes/getTree",method=RequestMethod.GET)
	@ResponseBody
	public List<ResourceTypeTreeNode> getTree(){
		List<ResourceType> resourceTypes = resourceTypeService.getAllResourceTypes();
		List<ResourceTypeTreeNode> list=ResourceTypeTreeUtil.wapTree(resourceTypes);
		return list;
	}
	
}
