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
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.dto.MenuTreeNode;
import com.sky.auth.permission.service.MenuService;
import com.sky.auth.permission.util.MenuTreeUtil;
import com.sky.auth.util.StringUtil;

@Controller
public class MenuController {
	public static Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;

	/**
	 * 函数功能说明 ： 查询所有菜单
	 * GET http://127.0.0.1:8085/menus
	 * 
	 * @参数：
	 * 
	 */
	@RequestMapping(value = "/menus", method = { RequestMethod.GET })
	@ResponseBody
	public List<Menu> getAllMenus() {
		List<Menu> menus = (List<Menu>) menuService.getAllMenus();
		return menus;
	}

	/**
	 * 函数功能说明 ： 添加菜单
	 * POST http://127.0.0.1:8085/menus
	 * 
	 * @param Menu 
	 * @throws
	 */
	@RequestMapping(value = "/menus", method = RequestMethod.POST)
	@ResponseBody
	public Menu createMenu(@RequestBody Menu menu) {
		String id = StringUtil.get32UUID();
		menu.setId(id);
		menuService.addMenu(menu);
		return menu;
	}

	/**
	 * 函数功能说明 ： 查询菜单信息
	 * GET http://127.0.0.1:8085/menus/{id}
	 * 
	 * @参数：{"id":"XXX"}
	 * 
	 */
	@RequestMapping(value = "/menus/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Menu getMenu(@PathVariable String id) {
		Menu menu = menuService.getMenuById(id);
		if(menu==null){
			throw new DataNotFoundException("get /menus/{id} failed because menu with id("+id+") not found!");
		}
		return menu;
	}

	/**
	 * 函数功能说明 ：修改菜单信息
	 * PUT http://127.0.0.1:8085/menus/{id}
	 * 
	 */
	@RequestMapping(value = "/menus/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Menu updateMenu(@PathVariable String id, @RequestBody Menu menu) {
		id = id.trim();
		menu.setId(id);
		int c = menuService.updateMenu(menu);
		if(c==0){
			throw new DataNotFoundException("put /menus/{id} failed because menu with id("+id+") not found!");
		}
		return menu;
	}

	/**
	 * 函数功能说明 ：删除菜单
	 * DETELE http://127.0.0.1:8085/menus/{id}
	 * 
	 * @参数：String
	 * @return int
	 * @throws
	 */
	@RequestMapping(value = "/menus/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@PathVariable String id) {
		int c = menuService.deleteMenuById(id);
		if(c==0){
			throw new DataNotFoundException("delete /menus/{id} failed because menu with id("+id+") not found!");
		}
		return c;
	}
	
	/**
	 * 函数功能说明 ：删除菜单 
	 * DETELE http://127.0.0.1:8085/menus/{id}/cascade
	 * 
	 * @参数： {"id":"XXX"} 
	 * 
	 *  @throws
	 */
	@RequestMapping(value = "/menus/{id}/cascade", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteCascade(@PathVariable String id) {
		int c = menuService.cascadeDeleteMenus(id);
		if(c==0){
			throw new DataNotFoundException("delete /menus/{id}/cascade failed because menu with id("+id+") not found!");
		}
		return c;	
	}

	/**
	 * 函数功能说明 ：批量删除菜单
	 * POST http://127.0.0.1:8085/users/batch-delete
	 * 
	 * @参数： ["xxx","xxx",...] 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/menus/batch-delete", method = RequestMethod.POST)
	@ResponseBody
	public int batchDelete(@RequestBody String[] ids) {
		int c = menuService.deleteMenusByIds(ids);
		return c;
	}

	@RequestMapping(value = "/menus/getTree",method=RequestMethod.GET)
	@ResponseBody
	public List<MenuTreeNode> getTree(){
		List<Menu> menus = menuService.getAllMenus();
		List<MenuTreeNode> list=MenuTreeUtil.wapTree(menus);
		return list;
	}
	
	
}
