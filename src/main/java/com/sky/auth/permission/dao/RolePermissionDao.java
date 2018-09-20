package com.sky.auth.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.RolePermission;

/**
 * 角色权限dao
 * 
 * @author：yangfan
 */
public interface RolePermissionDao extends BaseDao<RolePermission> {
	
	public int batchInsert(List<RolePermission> rolePermissions);
	
	
	/**
	 * 删除某个角色的某个权限
	 * @param roleId
	 * @param permissionId
	 */
	public void deleteByRoleIdAndPermissionId(String roleId, Long permissionId);
	
	/**
	 * 删除某个角色断所有权限
	 * @param roleId
	 */
	public void deleteByRoleId(String roleId);
	
	public List<Role> getRolesByPermissionId(String permissionId);
	
	/**
	 * 查询拥有角色的权限
	 * @param roleId
	 * @return
	 */
	public List<Permission> getPermissionsByRoleId(String roleId);
	
	/**
	 * 通过权限编号和角色编号查询
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public RolePermission getRolePermissionByRoleIdAndPermissionId(@Param("roleId")String roleId,@Param("permissionId")String permissionId);
	
	public int deleteByRoleIdAndPermissionId(@Param("roleId")String roleId,@Param("permissionId")String permissionId);
	
	/**
	 * 角色拥有的权限
	 * @param roleId
	 * @param permission
	 * @return
	 */
	public List<Permission> searchPermissions(@Param("roleId") String roleId,@Param("permission") Permission permission);	
	
	/**
	 * 角色没有的权限
	 * @param roleId
	 * @param permission
	 * @return
	 */
	public List<Permission> searchLeftPermissions(@Param("roleId") String roleId,@Param("permission") Permission permission);	
}
