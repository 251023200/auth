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
import com.sky.auth.permission.domain.Application;
import com.sky.auth.permission.service.ApplicationService;
import com.sky.auth.util.Page;
import com.sky.auth.util.StringUtil;

@Controller
public class ApplicationController {
	public static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	private ApplicationService applicationService;

	/**
	 * 函数功能说明 ： 查询所有应用
	 * GET http://127.0.0.1:8085/applications
	 * 
	 * @参数：
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/applications", method=RequestMethod.GET)
	@ResponseBody
	public List<Application> getAllApplications() {
		List<Application> applications = (List<Application>) applicationService.getAllApplications();
		return applications;
	}

	/**
	 * 函数功能说明 ： 添加应用
	 * POST http://127.0.0.1:8085/applications
	 * 
	 * @param User {"code":"caiwu","name":"caiwujuese","description":"ABCDEDF"} 
	 * @return  User
	 * @throws
	 */
	@RequestMapping(value = "/applications", method=RequestMethod.POST)
	@ResponseBody
	public Application createApplication(@RequestBody Application application) {
		String id = StringUtil.get32UUID();
		application.setId(id);
		applicationService.addApplication(application);
		return application;
	}

	/**
	 * 函数功能说明 ： 查询所有用户 
	 * GET http://127.0.0.1:8085/applications/{id}
	 * 
	 * @参数：
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/applications/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Application getApplication(@PathVariable String id) {
		Application application = applicationService.getApplicationById(id);
		if(application==null){
			throw new DataNotFoundException("get /applications/{id} failed because application with id("+id+") not found!");
		}
		return application;
	}

	/**
	 * 函数功能说明 ：修改用户 PUT http://127.0.0.1:8085/applications/{id}
	 * PUT http://127.0.0.1:8085/applications/{id}
	 * 
	 * @参数： User {"userNo":"0000012346","userName":"yangfan23","password":"ABCDefffEDF","name":"abc"}
	 *  @return
	 *  @throws
	 */
	@RequestMapping(value = "/applications/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Application updateApplication(@PathVariable String id, @RequestBody Application application) {
		application.setId(id);
		int c = applicationService.updateApplication(application);
		if(c==0){
			throw new DataNotFoundException("put /applications/{id} failed because application with id("+id+") not found!");
		}
		return application;	
	}

	/**
	 * 函数功能说明 ：删除应用DETELE http://127.0.0.1:8085/applications/{id}
	 * DELETE http://127.0.0.1:8085/applications/{id}
	 * 
	 * @参数：
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/applications/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id) {
		int c=applicationService.deleteApplicationById(id);
		if(c==0){
			throw new DataNotFoundException("delete /applications/{id} failed because application with id("+id+") not found!");
		}
		return c;
	}

	/**
	 * 函数功能说明 ：批量删除用户 POST http://127.0.0.1:8085/auth/users/batch-delete
	 * 
	 * @参数： String[] ["xxx","yyy",...] 
	 * @return
	 * {"code":0,msg:null,data:null} @throws
	 */
	@RequestMapping(value = "/applications/batch-delete", method = RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids) {
		int c = applicationService.deleteApplicationsByIds(ids);
		return c;
	}

	/**
	 * 函数功能说明 ： 查询所有应用
	 * http://127.0.0.1:8085/auth/applications/search?pageNum=1&pageSize=10
	 * 
	 * @参数：
	 * @return
	 * 
	 */
	@RequestMapping(value = "/applications/search", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Application> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestParam(required=false) String name,@RequestParam(required=false)String code,@RequestParam(required=false) String description) {
		Page page=new Page(pageNum,pageSize);
		Application application = new Application();
		if(name!=null){
			application.setName(name);
		}
		if(code!=null){
			application.setName(code);
		}
		if(description!=null){
			application.setDescription(description);
		}
		PageInfo<Application> pageInfo=applicationService.search(page.getPageNum(), page.getPageSize(),application);
		return pageInfo;
	}
	
	/**
	 * 函数功能说明 ： 查询所有应用
	 * http://127.0.0.1:8085/auth/applications/search?pageNum=1&pageSize=10
	 * 
	 * @参数：
	 * @return
	 * 
	 */
	@RequestMapping(value = "/applications/search", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<Application> search(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required=false) String sortby,
			@RequestParam(required=false) String order, @RequestBody Application application) {
		Page page=new Page(pageNum,pageSize);
		PageInfo<Application> pageInfo=applicationService.search(page.getPageNum(), page.getPageSize(),application);
		return pageInfo;
	}
	
}
