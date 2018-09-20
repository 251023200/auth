package com.sky.auth.permission.dao;

import java.util.List;
import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Application;

/**
 * 角色操作
 * @author yangfan
 *
 */
public interface ApplicationDao extends BaseDao<Application>{
	
	/**
	 * 通过部门名称获取部门
	 * @param name 角色名称
	 * @return List<Application> 角色列表
	 */
	public List<Application> getByName(String name);			
	
	/**
	 * 通过部门编号获取用户
	 * @param code 角色编码
	 * @return Application 角色
	 */
	public Application getByCode(String code);		
	
}
