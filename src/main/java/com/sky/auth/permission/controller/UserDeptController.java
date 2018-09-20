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
import com.sky.auth.permission.domain.Dept;
import com.sky.auth.permission.domain.User;
import com.sky.auth.permission.domain.UserDept;
import com.sky.auth.permission.service.UserDeptService;
import com.sky.auth.util.StringUtil;

@Controller
public class UserDeptController {
public static Logger logger = LoggerFactory.getLogger(UserDeptController.class);
	
	@Autowired
	private UserDeptService userDeptService;	
	
	/**
	 * 函数功能说明 ： 查询所有用户角色 
	 * GET http://127.0.0.1:8080/auth/user-depts
	 * 
	 * @参数： 
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/user-depts",method={RequestMethod.GET})
	@ResponseBody
	public List<UserDept> getAllUserDepts(){
		List<UserDept> userDepts = (List<UserDept>)userDeptService.getAllUserDepts();
		return userDepts;
	}
	
	/**
	 * 函数功能说明 ： 添加用户角色
	 * POST http://127.0.0.1:8080/auth/user-depts
	 * 
	 * @参数： User {"userId":"XXX","deptId":"XXX"}
	 * @return Response {"code":"xxx","data":""}
	 * @throws
	 */
	@RequestMapping(value = "/user-depts",method=RequestMethod.POST)
	@ResponseBody
	public UserDept addUser(@RequestBody UserDept userDept){	
		String id=StringUtil.get32UUID();
		userDept.setId(id);
		userDeptService.addUserDept(userDept);
		return userDept;
	}
	
	/**
	 * 函数功能说明 ： 通过id查询用户角色
	 * GET http://127.0.0.1:8080/auth/user-depts/{id}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"xxx","userId":"xxx","deptId":"xxx"}}
	 * @throws
	 */
	@RequestMapping(value = "/user-depts/{id}",method=RequestMethod.GET)
	@ResponseBody
	public UserDept getUserDeptById(@PathVariable String id){
		UserDept userDept = userDeptService.getUserDeptById(id);
		if(userDept==null){
			throw new DataNotFoundException("get /user-depts/{id} failed because user-dept with id("+id+") not found!");
		}
		return userDept;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有角色
	 * GET http://127.0.0.1:8080/auth/user-depts/users/{userId}/depts
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":[{"id":"f8de908e0f9f49578965a960b3c4b5fb","version":0,"status":"ACTIVE","createTime":1533197619000,"code":"caiwu","name":"caiwujuese","description":"ABCDEDF"}]}
	 * @throws
	 */
	@RequestMapping(value = "/user-depts/users/{userId}/depts",method=RequestMethod.GET)
	@ResponseBody
	public List<Dept> getDeptsByUserId(@PathVariable String userId){
		List<Dept> depts = userDeptService.getDeptsByUserId(userId);
		return depts;
	}
	
	/**
	 * 函数功能说明 ： 查询用户的指定角色
	 * GET http://127.0.0.1:8080/auth/user-depts/users/{userId}/depts/{deptId}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","Code":"admin","Name":"超级管理员角色"}}
	 * @throws
	 */
	@RequestMapping(value = "/user-depts/users/{userId}/depts/{deptId}",method=RequestMethod.GET)
	@ResponseBody
	public UserDept getUserDeptByUserIdAndDeptId(@PathVariable String userId,@PathVariable String deptId){
		UserDept userDept = userDeptService.getUserDeptByUserIdAndDeptId(userId,deptId);
		return userDept;
	}
	
	/**
	 * 函数功能说明 ： 查询拥有角色的用户
	 * GET http://127.0.0.1:8080/auth/user-depts/depts/{deptId}/users
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":[{"id":"5e6c851bfe8b4685a9d025b9fc991f93","version":0,"status":"ACTIVE","createTime":1533204790000,"userNo":"0000012345","userName":"yangfan","password":"1b64e2ebe52d859fb2b928f666bbc19d","name":"yangfan","salt":null}]}
	 * @throws
	 */
	@RequestMapping(value = "/user-depts/depts/{deptId}/users",method=RequestMethod.GET)
	@ResponseBody
	public List<User> getUsersByDeptId(@PathVariable String deptId){
		List<User> users = userDeptService.getUsersByDeptId(deptId);
		return users;
	}
	
	
	/**
	 * 函数功能说明 ：删除用户角色
	 * DETELE http://127.0.0.1:8080/auth/user-depts/{id}
	 * 
	 * @参数： {"id":"XXX"}
	 * @return {"code":0,msg:null,data:{}}
	 * @throws
	 */
	@RequestMapping(value = "/user-depts/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id){
		int c = userDeptService.deleteUserDeptById(id);
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
	@RequestMapping(value = "/user-depts/batch-delete",method=RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids){
		
		int c = userDeptService.deleteUserDeptByIds(ids);
		return c;
	}
	
	/**
	 * 函数功能说明 ： 查询所有角色 
	 * http://127.0.0.1:8080/auth/user/search?pageNum=1&pageSize=10
	 * 
	 * @参数： {"page":{"pageSize":10,"pageNum":2},"user":{"Name":"yangfan"}}
	 * @return {"code":0,"msg":"","data":{"pageNum":1,"pageSize":10,"totalCount":1,"dataList":[{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","roleCode":"admin","roleName":"超级管理员角色"}],"totalPage":1,"beginPageIndex":1,"endPageIndex":1,"countResultMap":null}}
	 * @throws
	 */
	/*
	@RequestMapping(value = "/user-depts/search",method=RequestMethod.POST)
	@ResponseBody
	public Response search(@RequestParam String pageNum,@RequestParam String pageSize,@RequestParam String sortby,@RequestParam String order,@RequestBody UserDept userDept){
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
			
			
			PageBean<UserDept> userPageBean = userDeptService.search(page, userDept);
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
