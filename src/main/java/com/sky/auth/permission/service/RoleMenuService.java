package com.sky.auth.permission.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.auth.permission.dao.RoleMenuDao;
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.RoleMenu;

/**
 * 用户角色操作
 * @author yangfan
 *
 */
@Service
public class RoleMenuService{
	
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	/**
	 * 通过id获取用户
	 * @param id 用户姓名
	 * @return RoleMenu 用户角色
	 */
	public RoleMenu getRoleMenuById(String id){
		return roleMenuDao.getById(id);
	}
	
	
	/**
	 * 添加用户角色
	 * @param role 待添加用户信息
	 * @return
	 */
	public int addRoleMenu(RoleMenu roleRole){
		return roleMenuDao.insert(roleRole);
	}
	
	/**
	 * 获取所有用户角色
	 * @return
	 */
	public List<RoleMenu> getAllRoleMenus(){
		return roleMenuDao.listAll();
	}
	
	/**
	 * 根据用户id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteRoleMenuById(String id){
		return roleMenuDao.deleteById(id);
	}
	
	/**
	 * 通过用户编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteRoleMenuByIds(String[] ids){
		return roleMenuDao.deleteByIds(ids);
	}
	
	/**
	 * 删除用户的角色
	 * @param roleId
	 * @return
	 */
	public int deleteRoleMenuByRoleId(String roleId){
		return roleMenuDao.deleteByRoleId(roleId);
	}
	
	/**
	 * 查询用户拥有的角色
	 * @param roleId
	 * @return
	 */
	public List<Role> getRolesByMenuId(String menuId){
		return roleMenuDao.getRolesByMenuId(menuId);
	}
	
	/**
	 * 批量添加
	 * @param roleRoles
	 * @return
	 */
	public int addRoleMenus(List<RoleMenu> roleRoles){
		return roleMenuDao.batchInsert(roleRoles);
	}
	
	/**
	 * 查询用户拥有的角色
	 * @param roleId
	 * @return
	 */
	/*
	public List<Role> searchRolesByRoleId(int pageNum,int pageSize,Role role){
		return roleMenuDao.searchRolesByRoleId(pageNum,pageSize,role);
	}
	*/
	
	/**
	 * 查询拥有角色的用户
	 * @param roleId
	 * @return
	 */
	public List<Menu> getMenusByRoleId(String roleId){
		return roleMenuDao.getMenusByRoleId(roleId);
	}
	
	/**
	 * 通过用户编号和角色编号查询
	 * @param roleId
	 * @param roleId
	 * @return
	 */
	public RoleMenu getRoleMenuByRoleIdAndMenuId(String roleId,String menuId){
		return roleMenuDao.getByRoleIdAndMenuId(roleId, menuId);
	}
	
	/**
	 * 通过用户编号和角色编号查询
	 * @param roleId
	 * @param roleId
	 * @return
	 */
	public int deleteRoleMenuByRoleIdAndMenuId(String roleId,String menuId){
		return roleMenuDao.deleteByRoleIdAndMenuId(roleId, menuId);
	}
	
	/**
	 * 分页查询
	 * @param pageNum 页数
	 * @param pageSize 页大小
	 * @param List<Role>
	 * @return
	 */
//	public List<Role> search(int offset,int limit,String orderBy,int asc,Role role){
//		return roleDao.search(offset,limit,orderBy,asc,role);
//	}
	
	/*
	public PageInfo<Role> search(int pageNum,int pageSize,String menuId,Role role){
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles = roleMenuDao.searchRolesByMenuId(menuId,role);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
		return pageInfo;
	}
	*/
	
	
}
