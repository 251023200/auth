package com.sky.auth.permission.dao;

import java.util.List;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Resource;

/**
 * 资源
 * @author yangfan
 *
 */
public interface ResourceDao extends BaseDao<Resource>{
	
	/**
	 * 通过名称获取操作
	 * @param name 资源名称
	 * @return List<Resource> 资源列表
	 */
	public List<Resource> getByName(String name);	
	
	/**
	 * 通过编码获取资源
	 * @param code 资源编号
	 * @return Resource
	 */
	public Resource getByCode(String code);	
	
}
