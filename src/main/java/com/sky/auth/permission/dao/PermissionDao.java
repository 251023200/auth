package com.sky.auth.permission.dao;

import java.util.List;
import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Permission;

/**
 * 
 * @author yangfan
 *
 */
public interface PermissionDao extends BaseDao<Permission>{
	
	/**
	 * 通过名称获取权限
	 * @param name 权限名称
	 * @return List<Permisson>
	 */
	public List<Permission> getByName(String name);	
	
	/**
	 * 通过编码获取权限
	 * @param code 权限编码
	 * @return
	 */
	public Permission getByCode(String code);	
	
	/**
	 * 通过资源编号和操作获取权限
	 * @param resourceId
	 * @param operationId
	 * @return
	 */
	public Permission getByResourceIdAndOperationId(String resourceId,String operationId);
	
}
