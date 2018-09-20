package com.sky.auth.permission.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.PermissionDao;
import com.sky.auth.permission.domain.Permission;

@Service
public class PermissionService {

	@Autowired
	private PermissionDao permissionDao;
	
	/**
	 * 通过id获取资源
	 * @param id 资源编号
	 * @return Permission 资源
	 */
	public Permission getPermissionById(String id){
		return permissionDao.getById(id);
	}
	
	/**
	 * 通过资源名称获取资源
	 * @param name 资源名称
	 * @return List<Permission> 资源列表
	 */
	public List<Permission> getPermissionsByName(String name){
		return permissionDao.getByName(name);
	}
	
	/**
	 * 通过部门编号获取资源
	 * @param code 资源编码
	 * @return Permission 资源
	 */
	public Permission getPermissionByCode(String code){
		return permissionDao.getByCode(code);
	}
	
	/**
	 * 添加资源
	 * @param permission 待添加资源信息
	 * @return
	 */
	public int addPermission(Permission permission){
		return permissionDao.insert(permission);
	}
	
	/**
	 * 获取所有资源
	 * @return
	 */
	public List<Permission> getAllPermissions(){
		return permissionDao.listAll();
	}
	
	/**
	 * 通过资源id修改资源信息
	 * @param user 
	 * @return
	 */
	public int updatePermission(Permission permission){
		return permissionDao.update(permission);
	}
	
	/**
	 * 根据资源id删除资源
	 * @param id 资源编号
	 * @return
	 */
	public int deletePermissionById(String id){
		return permissionDao.deleteById(id);
	}
	
	/**
	 * 通过资源编号批量删除资源
	 * @param id 资源编号
	 * @return
	 */
	public int deletePermissionsByIds(String[] ids){
		return permissionDao.deleteByIds(ids);
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
	 * 分页查询Permission
	 * @param pageNum
	 * @param pageSize
	 * @param user
	 * @return
	 */
	public PageInfo<Permission> search(int pageNum,int pageSize,Permission permission){
		PageHelper.startPage(pageNum, pageSize);
		List<Permission> permissions = permissionDao.search(permission);
		PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissions);
		return pageInfo;
	}
	
	/**
	 * 通过资源编号和操作获取权限
	 * @param resourceId
	 * @param operationId
	 * @return Permisson 权限
	 */
	public Permission getByResourceIdAndOperationId(String resourceId,String operationId){
		return permissionDao.getByResourceIdAndOperationId(resourceId, operationId);
	}
}
