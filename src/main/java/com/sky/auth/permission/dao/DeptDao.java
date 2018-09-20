package com.sky.auth.permission.dao;

import java.util.List;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Dept;

/**
 * 部门操作
 * @author yangfan
 *
 */
public interface DeptDao extends BaseDao<Dept>{
	
	public List<Dept> getByName(String name);			//通过部门名称获取部门
	
	public Dept getByCode(String code);			//通过部门编号获取用户
	
	
}
