package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.RolePermissionDao;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.RolePermission;

/**
 * 权限权限service接口实现
 * 
 * @author：yangfan
 */
@Service
public class RolePermissionService {
	
	@Autowired
	private RolePermissionDao rolePermissionDao;
	
//	@Autowired
//	private PmsOperatorRoleService pmsOperatorRoleService;
	
	/**
	 * 通过id获取角色
	 * @param id 角色姓名
	 * @return RolePermission 角色权限
	 */
	public RolePermission getRolePermissionById(String id){
		return rolePermissionDao.getById(id);
	}
	
	
	/**
	 * 添加角色权限
	 * @param user 待添加角色信息
	 * @return
	 */
	public int addRolePermission(RolePermission userRole){
		return rolePermissionDao.insert(userRole);
	}
	
	/**
	 * 添加角色权限
	 * @param user 待添加角色信息
	 * @return
	 */
	public int addRolePermissions(List<RolePermission> rolePermissions){
		return rolePermissionDao.batchInsert(rolePermissions);
	}
	
	/**
	 * 获取所有角色权限
	 * @return
	 */
	public List<RolePermission> getAllRolePermissions(){
		return rolePermissionDao.listAll();
	}
	
	/**
	 * 根据角色id删除角色
	 * @param id 角色编号
	 * @return
	 */
	public int deleteRolePermissionById(String id){
		return rolePermissionDao.deleteById(id);
	}
	
	/**
	 * 通过角色编号批量删除角色
	 * @param id 角色编号
	 * @return
	 */
	public int deleteRolePermissionByIds(String[] ids){
		return rolePermissionDao.deleteByIds(ids);
	}
	
	/**
	 * 查询角色拥有的权限
	 * @param userId
	 * @return
	 */
	public List<Role> getRolesByPermissionId(String permissionId){
		return rolePermissionDao.getRolesByPermissionId(permissionId);
	}
	
	/**
	 * 查询拥有权限的角色
	 * @param roleId
	 * @return
	 */
	public List<Permission> getPermissionsByRoleId(String roleId){
		return rolePermissionDao.getPermissionsByRoleId(roleId);
	}
	
	/**
	 * 通过角色编号和权限编号查询
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public RolePermission getRolePermissionByRoleIdAndPermissionId(String roleId,String permissionId){
		return rolePermissionDao.getRolePermissionByRoleIdAndPermissionId(roleId, permissionId);
	}
	
	/**
	 * 通过角色编号和权限编号查询
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int deleteRolePermissionByRoleIdAndPermissionId(String roleId,String permissionId){
		return rolePermissionDao.deleteByRoleIdAndPermissionId(roleId, permissionId);
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
	 * 分页查询角色拥有的权限
	 * @param pageNum
	 * @param pageSize
	 * @param roleId
	 * @param permission
	 * @return
	 */
	public PageInfo<Permission> searchPermissions(int pageNum,int pageSize,String roleId,Permission permission){
		PageHelper.startPage(pageNum, pageSize);
		List<Permission> permissions = rolePermissionDao.searchPermissions(roleId,permission);
		PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissions);
		return pageInfo;
	}
	
	/**
	 * 分页查询角色没有的权限
	 * @param pageNum
	 * @param pageSize
	 * @param roleId
	 * @param permission
	 * @return
	 */
	public PageInfo<Permission> searchLeftPermissions(int pageNum,int pageSize,String roleId,Permission permission){
		PageHelper.startPage(pageNum, pageSize);
		List<Permission> permissions = rolePermissionDao.searchLeftPermissions(roleId,permission);
		PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissions);
		return pageInfo;
	}
}
