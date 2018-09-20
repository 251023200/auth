package com.sky.auth.permission.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.auth.permission.dao.PermissionMenuDao;
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.PermissionMenu;

/**
 * 用户角色操作
 * @author yangfan
 *
 */
@Service
public class PermissionMenuService{
	
	@Autowired
	private PermissionMenuDao permissionMenuDao;
	
	/**
	 * 通过id获取用户
	 * @param id 用户姓名
	 * @return PermissionMenu 用户角色
	 */
	public PermissionMenu getPermissionMenuById(String id){
		return permissionMenuDao.getById(id);
	}
	
	
	/**
	 * 添加用户角色
	 * @param permission 待添加用户信息
	 * @return
	 */
	public int addPermissionMenu(PermissionMenu permissionPermission){
		return permissionMenuDao.insert(permissionPermission);
	}
	
	/**
	 * 获取所有用户角色
	 * @return
	 */
	public List<PermissionMenu> getAllPermissionMenus(){
		return permissionMenuDao.listAll();
	}
	
	/**
	 * 根据用户id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deletePermissionMenuById(String id){
		return permissionMenuDao.deleteById(id);
	}
	
	/**
	 * 通过用户编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deletePermissionMenuByIds(String[] ids){
		return permissionMenuDao.deleteByIds(ids);
	}
	
	/**
	 * 删除用户的角色
	 * @param permissionId
	 * @return
	 */
	public int deletePermissionMenuByPermissionId(String permissionId){
		return permissionMenuDao.deleteByPermissionId(permissionId);
	}
	
	/**
	 * 查询用户拥有的角色
	 * @param permissionId
	 * @return
	 */
	public List<Permission> getPermissionsByMenuId(String menuId){
		return permissionMenuDao.getPermissionsByMenuId(menuId);
	}
	
	/**
	 * 批量添加
	 * @param permissionPermissions
	 * @return
	 */
	public int addPermissionMenus(List<PermissionMenu> permissionPermissions){
		return permissionMenuDao.batchInsert(permissionPermissions);
	}
	
	/**
	 * 查询用户拥有的角色
	 * @param permissionId
	 * @return
	 */
	/*
	public List<Permission> searchPermissionsByPermissionId(int pageNum,int pageSize,Permission permission){
		return permissionMenuDao.searchPermissionsByPermissionId(pageNum,pageSize,permission);
	}
	*/
	
	/**
	 * 查询拥有角色的用户
	 * @param permissionId
	 * @return
	 */
	public List<Menu> getMenusByPermissionId(String permissionId){
		return permissionMenuDao.getMenusByPermissionId(permissionId);
	}
	
	/**
	 * 通过用户编号和角色编号查询
	 * @param permissionId
	 * @param permissionId
	 * @return
	 */
	public PermissionMenu getPermissionMenuByPermissionIdAndMenuId(String permissionId,String menuId){
		return permissionMenuDao.getByPermissionIdAndMenuId(permissionId, menuId);
	}
	
	/**
	 * 通过用户编号和角色编号查询
	 * @param permissionId
	 * @param permissionId
	 * @return
	 */
	public int deletePermissionMenuByPermissionIdAndMenuId(String permissionId,String menuId){
		return permissionMenuDao.deleteByPermissionIdAndMenuId(permissionId, menuId);
	}
	
	/**
	 * 分页查询
	 * @param pageNum 页数
	 * @param pageSize 页大小
	 * @param List<Permission>
	 * @return
	 */
//	public List<Permission> search(int offset,int limit,String orderBy,int asc,Permission permission){
//		return permissionDao.search(offset,limit,orderBy,asc,permission);
//	}
	
	/*
	public PageInfo<Permission> search(int pageNum,int pageSize,String menuId,Permission permission){
		PageHelper.startPage(pageNum, pageSize);
		List<Permission> permissions = permissionMenuDao.searchPermissionsByMenuId(menuId,permission);
		PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissions);
		return pageInfo;
	}
	*/
	
	
}
