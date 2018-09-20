package com.sky.auth.permission.dao;

import java.util.List;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Group;

/**
 * 部门操作
 * @author yangfan
 *
 */
public interface GroupDao extends BaseDao<Group>{
	
	public List<Group> getByName(String name);			//通过部门名称获取部门
	
	public Group getByCode(String code);			//通过部门编号获取用户
	
}
