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
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.User;
import com.sky.auth.permission.dto.MenuTreeNode;
import com.sky.auth.permission.service.UserService;
import com.sky.auth.permission.util.MenuTreeUtil;
import com.sky.auth.util.EncryptUtil;
import com.sky.auth.util.Page;
import com.sky.auth.util.StringUtil;

@Controller
public class UserController {
public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;	
	
	/**
	 * 函数功能说明 ： 查询所有用户 
	 * GET http://127.0.0.1:8080/users
	 * 
	 * @参数： 
	 *
	 * @throws
	 */
	@RequestMapping(value = "/users",method={RequestMethod.GET})
	@ResponseBody
	public List<User> getAllUsers(){
		List<User> users = (List<User>)userService.getAllUsers();
		return users;
	}
	
	/**
	 * 函数功能说明 ： 添加用户
	 * POST http://127.0.0.1:8080/users
	 * 
	 * @参数： User {"userNo":"0000012345","userName":"yangfan","password":"ABCDEDF","name":"yangfan"}
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/users",method=RequestMethod.POST)
	@ResponseBody
	public User createUser(@RequestBody User user){	
		String operatorId=StringUtil.get32UUID();
		user.setId(operatorId);
		user.setPassword(EncryptUtil.encodeMD5String(user.getPassword()));
		userService.addUser(user);
		return user;
	}
	
	/**
	 * 函数功能说明 ： 查询所有用户
	 * GET http://127.0.0.1:8080/users/{id}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/users/{id}",method=RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable String id){
		User userObj = userService.getUserById(id);
		if(userObj==null){
			throw new DataNotFoundException("get /users/{id} failed because users with id("+id+") not found!");
		}
		return userObj;
	}
	
	/**
	 * 函数功能说明 ：修改用户
	 * PUT http://127.0.0.1:8080/users/id
	 * 
	 * @参数： User {"userNo":"0000012346","userName":"yangfan23","password":"ABCDefffEDF","name":"abc"}
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/users/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public User updateUser(@PathVariable String id,@RequestBody User user){
		user.setId(id);
		userService.updateUser(user);
		return userService.getUserById(id);
	}
	
	/**
	 * 函数功能说明 ：删除用户
	 * DETELE http://127.0.0.1:8080/auth/users/{id}
	 * 
	 * @参数：
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/users/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id){
		int c = userService.deleteUserById(id);
		return c;
	}
	
	
	/**
	 * 函数功能说明 ：批量删除用户
	 * POST http://127.0.0.1:8080/auth/users/batch-delete
	 * 
	 * @参数： ["xxx","xxx",...]
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/users/batch-delete",method=RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids){
		int c = userService.deleteUsersByIds(ids);
		return c;
	}
	
	
	/**
	 * 函数功能说明 ： 查询所有用户
	 * http://127.0.0.1:8080/auth/user/search?pageNum=1&pageSize=10
	 * 
	 * @参数： {"page":{"pageSize":10,"pageNum":2},"user":{"roleName":"yangfan"}}
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/users/search",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<User> search(@RequestParam String pageNum,@RequestParam String pageSize,@RequestParam(required=false) String sortby,@RequestParam(required=false) String order,@RequestBody User user){
		
		PageInfo<User> userPageBean = userService.search(Integer.parseInt(pageNum),Integer.parseInt(pageSize), user);
		return userPageBean;
	}	
	
	/**
	 * 函数功能说明 ： 查询所有用户 
	 * http://127.0.0.1:8080/auth/user/search?pageNum=1&pageSize=10
	 * 
	 * @参数： {"page":{"pageSize":10,"pageNum":2},"user":{"roleName":"yangfan"}}
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/users/search",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<User> searchUsers(@RequestParam String pageNum,@RequestParam String pageSize,@RequestParam(required=false) String sortby,@RequestParam(required=false) String order, @RequestParam(required=false) String userNo,@RequestParam(required=false) String userName,@RequestParam(required=false) String name){
		
		User user = new User();
		if(userNo!=null){
			user.setUserNo(userNo);
		}
		if(userName!=null){
			user.setUserName(userName);
		}
		if(name!=null){
			user.setName(name);
		}
		PageInfo<User> pageInfo=userService.search(Integer.parseInt(pageNum), Integer.parseInt(pageSize),user);
		return pageInfo;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有权利
	 * POST http://127.0.0.1:8085/users/{userId}/roles?pageNum=1&pageSize=5
	 * 
	 * @参数：{"id":"XXX"}
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/users/{userId}/permissions/search",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<Permission> searchPermissionsAssignedToUser(@PathVariable String userId,@RequestParam(required=false) String pageNum,@RequestParam(required=false) String pageSize,@RequestBody(required=false) Permission permission){
		userId=userId.trim();
		Page page = new Page(pageNum,pageSize);
		PageInfo<Permission> permissionPageBean = userService.searchPermissions(page.getPageNum(),page.getPageSize(),userId, permission);
		return permissionPageBean;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有操作
	 * POST http://127.0.0.1:8085/users/{userId}/roles?pageNum=1&pageSize=5
	 * 
	 * @参数：{"id":"XXX"}
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/users/{userId}/operations/search",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<Operation> searchOperationsAssignedToUser(@PathVariable String userId,@RequestParam(required=false) String pageNum,@RequestParam(required=false) String pageSize,@RequestBody(required=false) Operation operation){
		userId=userId.trim();
		Page page = new Page(pageNum,pageSize);
		PageInfo<Operation> operationPageBean = userService.searchOperations(page.getPageNum(),page.getPageSize(),userId, operation);
		return operationPageBean;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有的菜单
	 * GET http://127.0.0.1:8080/users/{userId}/menus/tree
	 * 
	 * @参数：{"id":"XXX"}
	 * 
	 * @throws
	 */
	@RequestMapping(value = "/users/{userId}/menus/tree",method=RequestMethod.GET)
	@ResponseBody
	public List<MenuTreeNode> getMenusByUserId(@PathVariable String userId){
		List<Menu> menus= userService.getMenus(userId);
		List<MenuTreeNode> menuTree=MenuTreeUtil.wapTree(menus);
		return menuTree;
	}
	
	//修改密码
	/*
	@RequestMapping(value = "/changePwd",method=RequestMethod.POST)
	@ResponseBody
	public Response changePwd(HttpSession session,String oldPwd,String newPwd,String _newPwd){
		logger.info(">>>old"+oldPwd+">>>>new"+newPwd+">>>_new"+_newPwd);
		Response response=null;
		SessionInfo sessionInfo=(SessionInfo) session.getAttribute("sessionInfo");
		if(sessionInfo==null){
			response=new Response(1,"session不存在,非法操作");
			return response;
		}
		if(!newPwd.equals(_newPwd)){
			response=new Response(1,"两次密码输入不一致");
			return response;
		}
		PmsOperator pmsOperator=pmsOperatorService.getOperatorById(sessionInfo.getId());
		if(!pmsOperator.getLoginPwd().equals(EncryptUtil.encodeMD5String(oldPwd))){
			response=new Response(1,"原始密码输入有误");
			return response;
		}
		try {
			pmsOperator.setLoginPwd(EncryptUtil.encodeMD5String(newPwd));
			pmsOperatorService.updateOperator(pmsOperator);
			
			response=new Response(0);
		} catch (Exception e) {
			response=new Response(1,e.getMessage());
		}
		return response;
	}
	*/
}
