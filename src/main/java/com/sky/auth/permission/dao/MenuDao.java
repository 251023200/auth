package com.sky.auth.permission.dao;

import java.util.List;
import java.util.Map;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Menu;


/**
 * 权限菜单
 * 
 * @author：yangfan
 */
public interface MenuDao extends BaseDao<Menu>{
	/**
	 * 通过部门名称获取部门
	 * @param name 角色名称
	 * @return List<Role> 角色列表
	 */
	public List<Menu> getByName(String name);			
	
	/**
	 * 通过部门编号获取用户
	 * @param code 角色编码
	 * @return Role 角色
	 */
	public Menu getByCode(String code);	
	
	/**
	 * 根据角色id查找菜单列表
	 * 
	 * @param roleIdsStr
	 * @return
	 * @throws Exception
	 */
	public List<Menu> listByRoleIds(String roleIdsStr);

	/**
	 * 根据父菜单ID获取该菜单下的所有子孙菜单.<br/>
	 * 
	 * @param parentId
	 *            (如果为空，则为获取所有的菜单).<br/>
	 * @return menuList.
	 */
	public List<Menu> listByParent(String parentId);

	/**
	 * 根据菜单ID查找菜单（可用于判断菜单下是否还有子菜单）.
	 * 
	 * @param parentId
	 *            .
	 * @return menuList.
	 */
	public List<Menu> listByParentId(String parentId);

	/***
	 * 根据名称和是否叶子节点查询数据
	 * 
	 * @param isLeaf
	 *            是否是叶子节点
	 * @param name
	 *            节点名称
	 * @return
	 */
	public List<Menu> getMenuByNameAndIsLeaf(Map<String, Object> map);
	
	/**
	 * 获取用户拥有的菜单
	 * @param userId
	 * @return
	 */
	public List<Menu> getMenusByUserId(String userId);
	
	public int deleteSubMenus(String parentId);

}