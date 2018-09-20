package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.RoleDao;
import com.sky.auth.permission.domain.Role;

/**
 * 
 * @author yangfan
 *
 */
@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 通过id获取用户
	 * @param id 角色编号
	 * @return Role 用户
	 */
	public Role getRoleById(String id){
		return roleDao.getById(id);
	}
	
	/**
	 * 通过角色名称获取角色
	 * @param name 角色名称
	 * @return List<Role> 角色列表
	 */
	public List<Role> getRolesByName(String name){
		return roleDao.getByName(name);
	}
	
	/**
	 * 通过部门编号获取角色
	 * @param code 角色编码
	 * @return Role 角色
	 */
	public Role getRoleByCode(String code){
		return roleDao.getByCode(code);
	}
	
	/**
	 * 添加角色
	 * @param role 待添加角色信息
	 * @return
	 */
	public int addRole(Role role){
		return roleDao.insert(role);
	}
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Role> getAllRoles(){
		return roleDao.listAll();
	}
	
	/**
	 * 通过角色id修改用户信息
	 * @param user 
	 * @return
	 */
	public int updateRole(Role role){
		return roleDao.update(role);
	}
	
	/**
	 * 根据角色id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteRoleById(String id){
		return roleDao.deleteById(id);
	}
	
	/**
	 * 通过角色编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteRolesByIds(String[] ids){
		return roleDao.deleteByIds(ids);
	}
	
	
	/**
	 * 分页查询
	 * @param pageNum 页数
	 * @param pageSize 页大小
	 * @param List<User>
	 * @return
	 */
//	public List<User> search(int offset,int limit,String orderBy,int asc,User user){
//		return userDao.search(offset,limit,orderBy,asc,user);
//	}
	
	/**
	 * 分页查询Role
	 * @param pageNum
	 * @param pageSize
	 * @param user
	 * @return
	 */
	public PageInfo<Role> search(int pageNum,int pageSize,Role role){
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles = roleDao.search(role);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
		return pageInfo;
	}
	
}
