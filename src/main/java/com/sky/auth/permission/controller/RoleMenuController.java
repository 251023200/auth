package com.sky.auth.permission.controller;

import java.util.ArrayList;
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
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.domain.RoleMenu;
import com.sky.auth.permission.dto.StatedMenuTreeNode;
import com.sky.auth.permission.service.MenuService;
import com.sky.auth.permission.service.RoleMenuService;
import com.sky.auth.permission.util.MenuTreeUtil;
import com.sky.auth.util.StringUtil;

@Deprecated
@Controller
public class RoleMenuController {
public static Logger logger = LoggerFactory.getLogger(RoleMenuController.class);
	
	@Autowired
	private RoleMenuService roleMenuService;	
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 函数功能说明 ： 查询所有用户角色 
	 * GET http://127.0.0.1:8080/auth/role-menus
	 * 
	 * @参数： 
	 * @return {"code":0,"msg":"","data":[{"id":"dabf93c0be344d6e83f19842013d9105","version":0,"status":"ACTIVE","createTime":1533204913000,"roleId":"f8de908e0f9f49578965a960b3c4b5fb","roleId":"5e6c851bfe8b4685a9d025b9fc991f93"}]}
	 * @throws
	 */
	@RequestMapping(value = "/role-menus",method={RequestMethod.GET})
	@ResponseBody
	public List<RoleMenu> getAllRoles(){
		List<RoleMenu> roleMenus = (List<RoleMenu>)roleMenuService.getAllRoleMenus();
		return roleMenus;
	}
	
	/**
	 * 函数功能说明 ： 添加用户角色
	 * POST http://127.0.0.1:8080/auth/role-menus
	 * 
	 * @参数： Role {"roleId":"XXX","roleId":"XXX"}
	 * @return Response {"code":"xxx","data":""}
	 * @throws
	 */
	@RequestMapping(value = "/role-menus",method=RequestMethod.POST)
	@ResponseBody
	public RoleMenu addRole(@RequestBody RoleMenu roleMenu){
		String id=StringUtil.get32UUID();
		roleMenu.setId(id);
		roleMenuService.addRoleMenu(roleMenu);
		return roleMenu;
	}
	
	/**
	 * 函数功能说明 ： 通过id查询用户角色
	 * GET http://127.0.0.1:8080/auth/role-menus/{id}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"xxx","roleId":"xxx","roleId":"xxx"}}
	 * @throws
	 */
	@RequestMapping(value = "/role-menus/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RoleMenu getRoleMenuById(@PathVariable String id){
		RoleMenu roleMenu = roleMenuService.getRoleMenuById(id);
		if(roleMenu==null){
			throw new DataNotFoundException("get /role-menus/{id} failed because role-menu with id("+id+") not found!");
		}
		return roleMenu;
	}
	
	/**
	 * 函数功能说明 ： 查询用户所有角色
	 * GET http://127.0.0.1:8080/auth/role-menus/menus/{menuId}/roles
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":[{"id":"f8de908e0f9f49578965a960b3c4b5fb","version":0,"status":"ACTIVE","createTime":1533197619000,"code":"caiwu","name":"caiwujuese","description":"ABCDEDF"}]}
	 * @throws
	 */
//	@RequestMapping(value = "/role-menus/menus/{menuId}/roles",method=RequestMethod.GET)
//	@ResponseBody
//	public List<Role> getRolesByMenuId(@PathVariable String menuId){
//		List<Role> roles = roleMenuService.getRolesByMenuId(menuId);
//		return roles;
//	}
//	
	
	/**
	 * 函数功能说明 ： 查询用户的指定角色
	 * GET http://127.0.0.1:8085/roles/{roleId}/roles/{roleId}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","roleCode":"admin","roleName":"超级管理员角色"}}
	 * @throws
	 */
	@RequestMapping(value = "/roles/{roleId}/menus/{menuId}",method=RequestMethod.GET)
	@ResponseBody
	public RoleMenu getRoleMenuByRoleIdAndMenuId(@PathVariable String roleId,@PathVariable String menuId){
		RoleMenu roleMenu = roleMenuService.getRoleMenuByRoleIdAndMenuId(roleId,menuId);
		return roleMenu;
	}
	
	/**
	 * 函数功能说明 ： 查询用户的指定角色
	 * GET http://127.0.0.1:8085/roles/{roleId}/roles/{roleId}
	 * 
	 * @参数：{"id":"XXX"}
	 * @return {"code":0,"msg":"","data":{"id":"1","version":0,"status":"ACTIVE","creater":"roncoo","createTime":1464923263000,"editor":"admin","editTime":1464923263000,"remark":"超级管理员角色","roleCode":"admin","roleName":"超级管理员角色"}}
	 * @throws
	 */
	@RequestMapping(value = "/roles/{roleId}/menus/{menuId}",method=RequestMethod.DELETE)
	@ResponseBody
	public int deleteRoleMenuByRoleIdAndMenuId(@PathVariable String roleId,@PathVariable String menuId){
		int c = roleMenuService.deleteRoleMenuByRoleIdAndMenuId(roleId,menuId);
		return c;
	}
	
	/**
	 * 函数功能说明 ： 查询拥有角色的用户
	 * GET http://127.0.0.1:8080/auth/role-menus/roles/{roleId}/menus
	 * 
	 * @参数：{"roleId":"XXX"}
	 * @return List<Menu>
	 * 
	 * 
	 */
	@RequestMapping(value = "/roles/{roleId}/menus",method=RequestMethod.GET)
	@ResponseBody
	public List<Menu> getMenusByRoleId(@PathVariable String roleId){
		List<Menu> menus = roleMenuService.getMenusByRoleId(roleId);
		return menus;
	}
	
	
	/**
	 * 函数功能说明 ：删除用户角色
	 * DETELE http://127.0.0.1:8080/auth/role-menus/{id}
	 * 
	 * @参数： {"id":"XXX"}
	 * @return {"code":0,msg:null,data:{}}
	 * @throws
	 */
	@RequestMapping(value = "/role-menus/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id){
		int c =	roleMenuService.deleteRoleMenuById(id);
		if(c==0){
			throw new DataNotFoundException("delete /role-menus/{id} failed because role-menu with id("+id+") not found!");
		}
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量删除用户角色
	 * POST http://127.0.0.1:8080/auth/roles/batch-delete
	 * 
	 * @参数： [{"id":"XXX"},{"id":"xxx"},...]
	 * @return {"code":0,msg:null,data:null}
	 * @throws
	 */
	@RequestMapping(value = "/role-menus/batch-delete",method=RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids){
		int c = roleMenuService.deleteRoleMenuByIds(ids);
		return c;
	}
	
	/**
	 * 函数功能说明 ：修改角色权限   PUT http://127.0.0.1:8080/roles/{id}/menus
	 * 
	 * @参数： User
	 * {"roleNo":"0000012346","roleName":"yangfan23","password":"ABCDefffEDF","name":"abc"} @return
	 * {"code":0,msg:null,data:null} @throws
	 */
	@RequestMapping(value = "/roles/{id}/menus", method = RequestMethod.PUT)
	@ResponseBody
	public List<RoleMenu> setRoleMenus(@PathVariable String id, @RequestBody List<String> menuIds) {
		roleMenuService.deleteRoleMenuByRoleId(id);
		List<RoleMenu> roleMenus = new ArrayList<RoleMenu>(menuIds.size());
		for(int i=0;i<menuIds.size();i++){
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setId(StringUtil.get32UUID());
			roleMenu.setRoleId(id);
			roleMenu.setMenuId(menuIds.get(i));
			roleMenus.add(roleMenu);
		}
		roleMenuService.addRoleMenus(roleMenus);
		return roleMenus;	
	}
	
	/**
	 * 角色  对应的菜单树状态
	 * @return
	 */
	@RequestMapping(value = "/roles/{roleId}/menus/tree",method=RequestMethod.GET)
	@ResponseBody
	public List<StatedMenuTreeNode> getTree(@PathVariable String roleId){
		List<Menu> allMenus=menuService.getAllMenus();
		List<Menu> menus = roleMenuService.getMenusByRoleId(roleId);
		List<StatedMenuTreeNode> list=MenuTreeUtil.wapStatedTree(allMenus, menus);
		return list;
	}
	
}
