package com.sky.auth.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Operation;


public interface OperationDao extends BaseDao<Operation>{

	public List<Operation> getByName(String name);	//通过名称获取操作
	
	public Operation getByCode(String code);	//通过编码获取操作
	
	/**
	 * 分页查询不属于用户的Role
	 * @param userId
	 * @param permission
	 * @return
	 */
	public List<Operation> searchLeft(@Param("permissionId") String permissionId,@Param("operation")Operation operation);
	
}
