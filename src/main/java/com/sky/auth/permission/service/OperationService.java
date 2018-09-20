package com.sky.auth.permission.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.OperationDao;
import com.sky.auth.permission.domain.Operation;

/**
 * 操作服务
 * @author yangfan
 *
 */
@Service
public class OperationService{

	@Autowired 
	private OperationDao operationDao;
	
	/**
	 * 通过id获取用户
	 * @param id 角色编号
	 * @return Operation 用户
	 */
	public Operation getOperationById(String id){
		return operationDao.getById(id);
	}
	
	/**
	 * 通过角色名称获取角色
	 * @param name 角色名称
	 * @return List<Operation> 角色列表
	 */
	public List<Operation> getOperationsByName(String name){
		return operationDao.getByName(name);
	}
	/**
	 * 通过部门名称获取部门
	 * @param name 部门名称
	 * @return List<Operation> 部门
	 */
	public List<Operation> getByName(String name){
		return operationDao.getByName(name);
	}
	
	/**
	 * 通过部门编号获取部门
	 * @param code 部门编号
	 * @return Operation 部门
	 */
	public Operation getByCode(String code){
		return operationDao.getByCode(code);
	}
	
	/**
	 * 添加角色
	 * @param operation 待添加角色信息
	 * @return
	 */
	public int addOperation(Operation operation){
		return operationDao.insert(operation);
	}
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Operation> getAllOperations(){
		return operationDao.listAll();
	}
	
	/**
	 * 通过角色id修改用户信息
	 * @param permission 
	 * @return
	 */
	public int updateOperation(Operation operation){
		return operationDao.update(operation);
	}
	
	/**
	 * 根据角色id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteOperationById(String id){
		return operationDao.deleteById(id);
	}
	
	/**
	 * 通过角色编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteOperationsByIds(String[] ids){
		return operationDao.deleteByIds(ids);
	}
	
	
	/**
	 * 分页查询
	 * @param pageNum 页数
	 * @param pageSize 页大小
	 * @param List<Permission>
	 * @return
	 */
//	public List<Permission> search(int offset,int limit,String orderBy,int asc,Permission permission){
//		return permissionDao.search(offset,limit,orderBy,asc,permission);
//	}
	
	/**
	 * 分页查询Group
	 * @param pageNum
	 * @param pageSize
	 * @param permission
	 * @return
	 */
	public PageInfo<Operation> search(int pageNum,int pageSize,Operation operation){
		PageHelper.startPage(pageNum, pageSize);
		List<Operation> operations = operationDao.search(operation);
		PageInfo<Operation> pageInfo = new PageInfo<Operation>(operations);
		return pageInfo;
	}
	
	/**
	 * 分页查询Operation
	 * @param pageNum
	 * @param pageSize
	 * @param permission
	 * @return
	 */
	public PageInfo<Operation> searchLeft(int pageNum,int pageSize,String permissionId,Operation operation){
		PageHelper.startPage(pageNum, pageSize);
		List<Operation> operations = operationDao.searchLeft(permissionId,operation);
		PageInfo<Operation> pageInfo = new PageInfo<Operation>(operations);
		return pageInfo;
	}
	
}
