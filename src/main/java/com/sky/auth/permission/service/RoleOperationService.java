package com.sky.auth.permission.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.RoleOperationDao;
import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.RoleOperation;

/**
 * 用户角色操作
 * @author yangfan
 *
 */
@Service
public class RoleOperationService{
	
	@Autowired
	private RoleOperationDao roleOperationDao;
	
	/**
	 * 通过id获取用户
	 * @param id 用户姓名
	 * @return RoleOperation 角色操作
	 */
	public RoleOperation getRoleOperationById(String id){
		return roleOperationDao.getById(id);
	}
	
	
	/**
	 * 添加用户角色
	 * @param role 待添加用户信息
	 * @return
	 */
	public int addRoleOperation(RoleOperation roleRole){
		return roleOperationDao.insert(roleRole);
	}
	
	/**
	 * 获取所有用户角色
	 * @return
	 */
	public List<RoleOperation> getAllRoleOperations(){
		return roleOperationDao.listAll();
	}
	
	/**
	 * 根据用户id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteRoleOperationById(String id){
		return roleOperationDao.deleteById(id);
	}
	
	/**
	 * 通过用户编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteRoleOperationByIds(String[] ids){
		return roleOperationDao.deleteByIds(ids);
	}
	
	/**
	 * 删除用户的角色
	 * @param roleId
	 * @return
	 */
	public int deleteRoleOperationByRoleId(String roleId){
		return roleOperationDao.deleteByRoleId(roleId);
	}
	
	
	
	/**
	 * 批量添加
	 * @param roleRoles
	 * @return
	 */
	public int addRoleOperations(List<RoleOperation> roleRoles){
		return roleOperationDao.batchInsert(roleRoles);
	}
	
	/**
	 * 查询用户拥有的角色
	 * @param roleId
	 * @return
	 */
	/*
	public List<Role> searchRolesByRoleId(int pageNum,int pageSize,Role role){
		return roleOperationDao.searchRolesByRoleId(pageNum,pageSize,role);
	}
	*/
	
	public RoleOperation getRoleOperationByRoleIdAndOperationId(String roleId,String operationId){
		return roleOperationDao.getRoleOperationByRoleIdAndOperationId(roleId, operationId);
	}
	
	public int deleteRoleOperationByRoleIdAndOperationId(String roleId,String operationId){
		return roleOperationDao.deleteByRoleIdAndOperationId(roleId, operationId);
	}
	/**
	 * 查询拥有角色的用户
	 * @param roleId
	 * @return
	 */
	public List<Operation> getOperationsByRoleId(String roleId){
		return roleOperationDao.getOperationsByRoleId(roleId);
	}
	
	public List<Role> getRolesByOperationId(String operationId){
		return roleOperationDao.getRolesByOperationId(operationId);
	}
	
	public PageInfo<Operation> search(int pageNum,int pageSize,String roleId,Operation operation){
		PageHelper.startPage(pageNum, pageSize);
		List<Operation> operations = roleOperationDao.searchOperationsByRoleId(roleId,operation);
		PageInfo<Operation> pageInfo = new PageInfo<Operation>(operations);
		return pageInfo;
	}
	
	/**
	 * 分页查询
	 * @param pageNum 页数
	 * @param pageSize 页大小
	 * @param List<Role>
	 * @return
	 */
//	public List<Role> search(int offset,int limit,String orderBy,int asc,Role role){
//		return roleDao.search(offset,limit,orderBy,asc,role);
//	}
	
	/*
	public PageInfo<Role> search(int pageNum,int pageSize,String menuId,Role role){
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles = roleOperationDao.searchRolesByMenuId(menuId,role);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
		return pageInfo;
	}
	*/
	
	
}
