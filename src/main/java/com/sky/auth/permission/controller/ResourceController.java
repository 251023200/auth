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
import com.sky.auth.permission.domain.Resource;
import com.sky.auth.permission.service.ResourceService;
import com.sky.auth.util.StringUtil;

@Controller
public class ResourceController {
	public static Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceService resourceService;

	/**
	 * 函数功能说明 ： 查询所有操作 GET http://127.0.0.1:8080/resources
	 * 
	 * @参数：
	 * 
	 * @return {"code":0,"msg":"","data":[{"id":"180b6b9033b6431485384c9b7bcc9b02","version":0,"status":"ACTIVE","createTime":1533259121000,"parentId":"0","name":"sys user","code":"sys:user","type":"menu","description":"system user management","uri":"/users"}]}
	 */
	@RequestMapping(value = "/resources", method = { RequestMethod.GET })
	@ResponseBody
	public List<Resource> getAllResources() {
		List<Resource> resources = (List<Resource>) resourceService.getAllResources();
		return resources;
	}

	/**
	 * 函数功能说明 ： 添加操作 POST http://127.0.0.1:8080/resources
	 * 
	 * @param Resource {"parentId":"xxx","name":"xxx","code":"xxx","type":"xxx","description":"xxx","uri":"xxx"}
	 * @throws
	 */
	@RequestMapping(value = "/resources", method = RequestMethod.POST)
	@ResponseBody
	public Resource createResource(@RequestBody Resource resource) {
		String id = StringUtil.get32UUID();
		resource.setId(id);
		resourceService.addResource(resource);
		return resource;
	}

	/**
	 * 函数功能说明 ： 查询所有资源 GET http://127.0.0.1:8080/resources/{id}
	 * 
	 * @参数：{"id":"XXX"}
	 * 
	 * @return {"code":0,"msg":"","data":{"id":"180b6b9033b6431485384c9b7bcc9b02","version":0,"status":"ACTIVE","createTime":1533259121000,"parentId":"0","name":"sys user","code":"sys:user","type":"menu","description":"system user management","uri":"/users"}}
	 */
	@RequestMapping(value = "/resources/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Resource getResource(@PathVariable String id) {
		Resource resource = resourceService.getResourceById(id);
		if(resource==null){
			throw new DataNotFoundException("get /resources/{id} failed because resource with id("+id+") not found!");
		}
		return resource;
	}

	/**
	 * 函数功能说明 ：修改资源 PUT http://127.0.0.1:8080/resources/{id}
	 * 
	 * @参数： User
	 * @return 修改后的资源 {"code":0,"msg":"","data":{"id":"180b6b9033b6431485384c9b7bcc9b02","version":1,"status":"ACTIVE","createTime":1533259121000,"parentId":"0","name":"system user","code":"sys:user","type":"menu","description":"system user management","uri":"/users"}}
	 * @throws
	 */
	@RequestMapping(value = "/resources/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Resource updateResource(@PathVariable String id, @RequestBody Resource resource) {
		resource.setId(id);
		int c=resourceService.updateResource(resource);
		if(c==0){
			throw new DataNotFoundException("put /resources/{id} failed because resource with id("+id+") not found!");
		}
		return resource;
	}

	/**
	 * 函数功能说明 ：删除操作 DETELE http://127.0.0.1:8080/resources/{id}
	 * 
	 * @param： {"id":"XXX"} 
	 * @return {"code":0,msg:null,data:null} @throws
	 */
	@RequestMapping(value = "/resources/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Resource delete(@PathVariable String id) {
		Resource resource = resourceService.getResourceById(id);
		int c=resourceService.deleteResourceById(id);
		if(c==0){
			throw new DataNotFoundException("put /resources/{id} failed because resource with id("+id+") not found!");
		}
		return resource;
	}

	/**
	 * 函数功能说明 ：批量删除资源 POST http://127.0.0.1:8080/auth/resources/batch-delete
	 * 
	 * @参数： [{"id":"XXX"},{"id":"xxx"},...] @return
	 * {"code":0,msg:null,data:null} @throws
	 */
	@RequestMapping(value = "/resources/batch-delete", method = RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids) {
		int c = resourceService.deleteResourcesByIds(ids);
		return c;
	}

	/**
	 * 函数功能说明 ： 查询所有操作
	 * http://127.0.0.1:8080/auth/resources/search?pageNum=1&pageSize=10
	 * 
	 * @参数：
	 * {"page":{"pageSize":10,"pageNum":2},"user":{"roleName":"yangfan"}} @return
	 * {"code":0,"msg":"","data":{"pageNum":1,"pageSize":10,"totalCount":1,"dataList":[{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员操作","roleCode":"admin","roleName":"超级管理员操作"}],"totalPage":1,"beginPageIndex":1,"endPageIndex":1,"countResultMap":null}} @throws
	 */
	@RequestMapping(value = "/resources/search", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<Resource> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestBody Resource resource) {
		
		int ipageNum = Integer.parseInt(pageNum);
		int ipageSize = Integer.parseInt(pageSize);
			
		PageInfo<Resource> resources = resourceService.search(ipageNum,ipageSize, resource);
		return resources;
	}

	/**
	 * 函数功能说明 ： 查询所有操作
	 * http://127.0.0.1:8080/auth/resources/search?pageNum=1&pageSize=10
	 * 
	 * @参数：
	 * {"page":{"pageSize":10,"pageNum":2},"user":{"roleName":"yangfan"}} @return
	 * {"code":0,"msg":"","data":{"pageNum":1,"pageSize":10,"totalCount":1,"dataList":[{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员操作","roleCode":"admin","roleName":"超级管理员操作"}],"totalPage":1,"beginPageIndex":1,"endPageIndex":1,"countResultMap":null}} @throws
	 */
	@RequestMapping(value = "/resources/search", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Resource> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestParam(required=false)String name,@RequestParam(required=false)String code,@RequestParam(required=false)String description) {
		
		int ipageNum = Integer.parseInt(pageNum);
		int ipageSize = Integer.parseInt(pageSize);
		Resource resource = new Resource();
		if(name!=null){
			resource.setName(name);
		}
		if(code!=null){
			resource.setCode(code);
		}
		if(description!=null){
			resource.setDescription(description);
		}

		PageInfo<Resource> resources = resourceService.search(ipageNum,ipageSize, resource);
		return resources;
	}
	

}
