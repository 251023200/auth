package com.sky.auth.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.PermissionMenu;

/**
 * 权限-菜单
 * @author yangfan
 *
 */
public interface PermissionMenuDao extends BaseDao<PermissionMenu>{
	
	public PermissionMenu getByPermissionIdAndMenuId(@Param("permissionId")String permissionId,@Param("menuId")String menuId);	//通过用户编号和角色编号查找
	
	public int deleteByPermissionIdAndMenuId(@Param("permissionId") String permissionId,@Param("menuId") String menuId);	//通过用户编号和角色编号查找
	
	public List<Menu> getMenusByPermissionId(String permissionId);	//查找拥有角色的用户
	
	public List<Permission> getPermissionsByMenuId(String menuId);	//查找用户拥有的角色
	
	public int deleteByPermissionId(String permissionId);
	
	public int deleteByMenuId(String menuId);
	
	//public List<Permission> searchPermissionsByUserId(int pageNum,int pageSize,Permission permissionId);	//查找用户拥有的角色
	public int batchInsert(List<PermissionMenu> permissionMenus);
	
	
}
