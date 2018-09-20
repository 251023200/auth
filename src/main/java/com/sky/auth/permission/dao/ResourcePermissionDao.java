package com.sky.auth.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.Resource;
import com.sky.auth.permission.domain.ResourcePermission;

/**
 * 资源-权限
 * @author yangfan
 *
 */
public interface ResourcePermissionDao extends BaseDao<ResourcePermission>{
	
	public ResourcePermission getByResourceIdAndPermissionId(@Param("resourceId")String resourceId,@Param("permissionId")String permissionId);	//通过用户编号和角色编号查找
	
	public int deleteByResourceIdAndPermissionId(@Param("resourceId") String resourceId,@Param("permissionId") String permissionId);	//通过用户编号和角色编号查找
	
	public List<Resource> getResourcesByPermissionId(String permissionId);	//查找权限拥有的资源
	
	public List<Permission> getPermissionsByResourceId(String resourceId);	//查找资源被赋予的权限
	
	//public List<Permission> searchPermissionsByResourceId(int pageNum,int pageSize,Permission permissionId);	//查找用户拥有的角色
	public int batchInsert(List<ResourcePermission> resourcePermissions);
	
	public List<Permission> searchPermissionsByResourceId(@Param("resourceId") String resourceId,@Param("permission") Permission permission);	//查找用户拥有的角色
	
	public int deleteByResourceId(String resourceId);
	
	public int deleteByPermissionId(String permissionId);
}
