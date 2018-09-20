package com.sky.auth.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.RoleMenu;

/**
 * 用户-角色
 * @author yangfan
 *
 */
public interface RoleMenuDao extends BaseDao<RoleMenu>{
	
	public RoleMenu getByRoleIdAndMenuId(@Param("roleId")String roleId,@Param("menuId")String menuId);	//通过用户编号和角色编号查找
	
	public int deleteByRoleIdAndMenuId(@Param("roleId") String roleId,@Param("menuId") String menuId);	//通过用户编号和角色编号查找
	
	public List<Menu> getMenusByRoleId(String roleId);	//查找拥有角色的用户
	
	public List<Role> getRolesByMenuId(String menuId);	//查找用户拥有的角色
	
	public int deleteByRoleId(String roleId);
	
	public int deleteByMenuId(String menuId);
	
	//public List<Role> searchRolesByUserId(int pageNum,int pageSize,Role roleId);	//查找用户拥有的角色
	public int batchInsert(List<RoleMenu> roleMenus);
	
	
}
