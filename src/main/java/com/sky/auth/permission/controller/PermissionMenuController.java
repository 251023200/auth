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
import com.sky.auth.permission.domain.PermissionMenu;
import com.sky.auth.permission.dto.StatedMenuTreeNode;
import com.sky.auth.permission.service.MenuService;
import com.sky.auth.permission.service.PermissionMenuService;
import com.sky.auth.permission.util.MenuTreeUtil;
import com.sky.auth.util.StringUtil;

@Controller
public class PermissionMenuController {
public static Logger logger = LoggerFactory.getLogger(PermissionMenuController.class);
	
	@Autowired
	private PermissionMenuService permissionMenuService;	
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 函数功能说明 ： 查询所有权限菜单
	 * GET http://127.0.0.1:8085/permission-menus
	 * 
	 * @参数： 
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/permission-menus",method={RequestMethod.GET})
	@ResponseBody
	public List<PermissionMenu> getAllPermissions(){
		List<PermissionMenu> permissionMenus = (List<PermissionMenu>)permissionMenuService.getAllPermissionMenus();
		return permissionMenus;
	}
	
	/**
	 * 函数功能说明 ： 权限添加菜单
	 * POST http://127.0.0.1:8085/permission-menus
	 * 
	 * @param Permission {"permissionId":"XXX","menuId":"XXX"}
	 * @return Response {"code":"xxx","data":""}
	 * @throws
	 */
	@RequestMapping(value = "/permission-menus",method=RequestMethod.POST)
	@ResponseBody
	public PermissionMenu addPermission(@RequestBody PermissionMenu permissionMenu){
		String id=StringUtil.get32UUID();
		permissionMenu.setId(id);
		permissionMenuService.addPermissionMenu(permissionMenu);
		return permissionMenu;
	}
	
	/**
	 * 函数功能说明 ：删除权限菜单
	 * DETELE http://127.0.0.1:8085/permission-menus/{id}
	 * 
	 */
	@RequestMapping(value = "/permission-menus/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id){
		int c =	permissionMenuService.deletePermissionMenuById(id);
		if(c==0){
			throw new DataNotFoundException("delete /permission-menus/{id} failed because permission-menu with id("+id+") not found!");
		}
		return c;
	}
	
	/**
	 * 函数功能说明 ：批量删除权限菜单
	 * POST http://127.0.0.1:8085/permissions/batch-delete
	 * 
	 */
	@RequestMapping(value = "/permission-menus/batch-delete",method=RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids){
		int c = permissionMenuService.deletePermissionMenuByIds(ids);
		return c;
	}
	
	/**
	 * 函数功能说明 ： 删除权限菜单
	 * GET http://127.0.0.1:8085/permissions/{permissionId}/menus/{menuId}
	 * 
	 */
	@RequestMapping(value = "permissions/{permissionId}/menus/{menuId}",method=RequestMethod.DELETE)
	@ResponseBody
	public int deletePermissionMenuByPermissionIdAndMenuId(@PathVariable String permissionId,@PathVariable String menuId){
		int c = permissionMenuService.deletePermissionMenuByPermissionIdAndMenuId(permissionId,menuId);
		return c;
	}
	
	/**
	 * 函数功能说明 ：修改角色权限   
	 * PUT http://127.0.0.1:8085/permissions/{id}/menus
	 * 
	 */
	@RequestMapping(value = "/permissions/{id}/menus", method = RequestMethod.PUT)
	@ResponseBody
	public List<PermissionMenu> setPermissionMenus(@PathVariable String id, @RequestBody List<String> menuIds) {
		permissionMenuService.deletePermissionMenuByPermissionId(id);
		List<PermissionMenu> permissionMenus = new ArrayList<PermissionMenu>(menuIds.size());
		for(int i=0;i<menuIds.size();i++){
			PermissionMenu permissionMenu = new PermissionMenu();
			permissionMenu.setId(StringUtil.get32UUID());
			permissionMenu.setPermissionId(id);
			permissionMenu.setMenuId(menuIds.get(i));
			permissionMenus.add(permissionMenu);
		}
		permissionMenuService.addPermissionMenus(permissionMenus);
		return permissionMenus;	
	}
	
	/**
	 * 函数功能说明 ： 查询拥有角色的用户
	 * GET http://127.0.0.1:8085/permission-menus/permissions/{permissionId}/menus
	 * 
	 * @参数：{"permissionId":"XXX"}
	 * @return List<Menu>
	 * 
	 * 
	 */
	@RequestMapping(value = "/permissions/{permissionId}/menus",method=RequestMethod.GET)
	@ResponseBody
	public List<Menu> getMenusByPermissionId(@PathVariable String permissionId){
		List<Menu> menus = permissionMenuService.getMenusByPermissionId(permissionId);
		return menus;
	}
	
	
	/**
	 * 函数功能说明 ：权限菜单树
	 * 
	 */
	@RequestMapping(value = "/permissions/{permissionId}/menus/tree",method=RequestMethod.GET)
	@ResponseBody
	public List<StatedMenuTreeNode> getTree(@PathVariable String permissionId){
		List<Menu> allMenus=menuService.getAllMenus();
		List<Menu> menus = permissionMenuService.getMenusByPermissionId(permissionId);
		List<StatedMenuTreeNode> list=MenuTreeUtil.wapStatedTree(allMenus, menus);
		return list;
	}
	
}
