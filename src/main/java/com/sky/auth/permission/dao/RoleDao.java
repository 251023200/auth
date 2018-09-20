package com.sky.auth.permission.dao;

import java.util.List;
import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Role;

/**
 * 角色操作
 * @author yangfan
 *
 */
public interface RoleDao extends BaseDao<Role>{
	
	/**
	 * 通过部门名称获取部门
	 * @param name 角色名称
	 * @return List<Role> 角色列表
	 */
	public List<Role> getByName(String name);			
	
	/**
	 * 通过部门编号获取用户
	 * @param code 角色编码
	 * @return Role 角色
	 */
	public Role getByCode(String code);		
	
}
