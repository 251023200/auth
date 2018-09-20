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
import com.sky.auth.enums.Status;
import com.sky.auth.exception.DataNotFoundException;
import com.sky.auth.permission.domain.Dept;
import com.sky.auth.permission.service.DeptService;
import com.sky.auth.util.StringUtil;

@Controller
public class DeptController {
	public static Logger logger = LoggerFactory.getLogger(DeptController.class);

	@Autowired
	private DeptService deptService;

	/**
	 * 函数功能说明 ： 查询所有角色 GET http://127.0.0.1:8080/depts
	 * 
	 * @参数：
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/depts", method = { RequestMethod.GET })
	@ResponseBody
	public List<Dept> getAllDepts() {
		List<Dept> depts = (List<Dept>) deptService.getAllDepts();
		return depts;
	}

	/**
	 * 函数功能说明 ： 添加角色 POST http://127.0.0.1:8080/depts
	 * 
	 * @param Dept {"parentId":"xxx","code":"xxx","name":"xxx","description":"xxx"}
	 * @throws
	 */
	@RequestMapping(value = "/depts", method = RequestMethod.POST)
	@ResponseBody
	public Dept createDept(@RequestBody Dept dept) {
		String id = StringUtil.get32UUID();
		dept.setId(id);
		if(dept.getParentId()==null){
			dept.setParentId("0");
		}
		dept.setStatus(Status.ACTIVE.getCode());
		deptService.addDept(dept);
		return dept;
	}

	/**
	 * 函数功能说明 ： 查询所有用户 GET http://127.0.0.1:8080/depts/{id}
	 * 
	 * @参数：{"id":"XXX"}
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/depts/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Dept getDept(@PathVariable String id) {
		Dept dept = deptService.getDeptById(id.trim());
		if(dept==null){
			throw new DataNotFoundException("get /depts/{id} failed because dept with id("+id+") not found!");
		}
		return dept;
	}

	/**
	 * 函数功能说明 ：修改用户 PUT http://127.0.0.1:8080/depts/{id}
	 * 
	 * @参数： User
	 * @return
	 * {"code":0,msg:null,data:null} @throws
	 */
	@RequestMapping(value = "/depts/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Dept updateDept(@PathVariable String id, @RequestBody Dept dept) {
		dept.setId(id);
		int c = deptService.updateDept(dept);
		if(c==0){
			throw new DataNotFoundException("put /depts/{id} failed because dept with id("+id+") not found!");
		}
		return dept;
	}

	/**
	 * 函数功能说明 ：删除角色 DETELE http://127.0.0.1:8080/depts/{id}
	 * 
	 * @参数： {"id":"XXX"} @return {"code":0,msg:null,data:null} @throws
	 */
	@RequestMapping(value = "/depts/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id) {
		int c = deptService.deleteDeptById(id);
		if(c==0){
			throw new DataNotFoundException("put /depts/{id} failed because dept with id("+id+") not found!");
		}
		return c;
	}

	/**
	 * 函数功能说明 ：批量删除用户 POST http://127.0.0.1:8080/auth/users/batch-delete
	 * 
	 * @参数： [{"id":"XXX"},{"id":"xxx"},...] @return
	 * {"code":0,msg:null,data:null} @throws
	 */
	@RequestMapping(value = "/depts/batch-delete", method = RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids) {
		int c = deptService.deleteDeptsByIds(ids);
		return c;
	}

	/**
	 * 函数功能说明 ： 查询所有角色
	 * http://127.0.0.1:8080/auth/depts/search?pageNum=1&pageSize=10
	 * 
	 * @参数：
	 * {"page":{"pageSize":10,"pageNum":2},"user":{"roleName":"yangfan"}} @return
	 * {"code":0,"msg":"","data":{"pageNum":1,"pageSize":10,"totalCount":1,"dataList":[{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","roleCode":"admin","roleName":"超级管理员角色"}],"totalPage":1,"beginPageIndex":1,"endPageIndex":1,"countResultMap":null}} @throws
	 */
	@RequestMapping(value = "/depts/search", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<Dept> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestBody Dept dept) {
		PageInfo<Dept> userPageBean = deptService.search(Integer.parseInt(pageNum),Integer.parseInt(pageSize), dept);
		return userPageBean;
	}

	/**
	 * 函数功能说明 ： 查询所有角色
	 * http://127.0.0.1:8080/auth/depts/search?pageNum=1&pageSize=10
	 * 
	 * @参数：
	 * {"page":{"pageSize":10,"pageNum":2},"user":{"roleName":"yangfan"}} @return
	 * {"code":0,"msg":"","data":{"pageNum":1,"pageSize":10,"totalCount":1,"dataList":[{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","roleCode":"admin","roleName":"超级管理员角色"}],"totalPage":1,"beginPageIndex":1,"endPageIndex":1,"countResultMap":null}} @throws
	 */
	@RequestMapping(value = "/depts/search", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Dept> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false)String order, @RequestParam(required=false)String name,@RequestParam(required=false)String code,@RequestParam(required=false)String description) {
		Dept dept = new Dept();
		if(name!=null){
			dept.setName(name);
		}
		if(code!=null){
			dept.setCode(code);
		}
		if(description!=null){
			dept.setDescription(description);
		}
		PageInfo<Dept> depts = deptService.search(Integer.parseInt(pageNum),Integer.parseInt(pageSize), dept);
		return depts;
	}
	

}
