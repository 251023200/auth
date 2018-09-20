package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.PermissionOperationDao;
import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.PermissionOperation;

/**
 * 用户角色操作
 * @author yangfan
 *
 */
@Service
public class PermissionOperationService{
	
	@Autowired
	private PermissionOperationDao permissionOperationDao;
	
	/**
	 * 通过id获取用户
	 * @param id 用户姓名
	 * @return PermissionOperation 用户角色
	 */
	public PermissionOperation getPermissionOperationById(String id){
		return permissionOperationDao.getById(id);
	}
	
	
	/**
	 * 添加用户角色
	 * @param permission 待添加用户信息
	 * @return
	 */
	public int addPermissionOperation(PermissionOperation permissionOperation){
		return permissionOperationDao.insert(permissionOperation);
	}
	
	/**
	 * 获取所有用户角色
	 * @return
	 */
	public List<PermissionOperation> getAllPermissionOperations(){
		return permissionOperationDao.listAll();
	}
	
	/**
	 * 根据用户id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deletePermissionOperationById(String id){
		return permissionOperationDao.deleteById(id);
	}
	
	/**
	 * 通过用户编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deletePermissionOperationByIds(String[] ids){
		return permissionOperationDao.deleteByIds(ids);
	}
	
	/**
	 * 删除用户的角色
	 * @param permissionId
	 * @return
	 */
	public int deletePermissionOperationByPermissionId(String permissionId){
		return permissionOperationDao.deleteByPermissionId(permissionId);
	}
	
	/**
	 * 查询用户拥有的角色
	 * @param permissionId
	 * @return
	 */
	public List<Operation> getOperationsByPermissionId(String permissionId){
		return permissionOperationDao.getOperationsByPermissionId(permissionId);
	}
	
	/**
	 * 批量添加
	 * @param permissionOperations
	 * @return
	 */
	public int addPermissionOperations(List<PermissionOperation> permissionOperations){
		return permissionOperationDao.batchInsert(permissionOperations);
	}
	
	/**
	 * 查询用户拥有的角色
	 * @param permissionId
	 * @return
	 */
	/*
	public List<Operation> searchOperationsByPermissionId(int pageNum,int pageSize,Operation operation){
		return permissionOperationDao.searchOperationsByPermissionId(pageNum,pageSize,operation);
	}
	*/
	
	/**
	 * 查询拥有角色的用户
	 * @param operationId
	 * @return
	 */
	public List<Permission> getPermissionsByOperationId(String operationId){
		return permissionOperationDao.getPermissionsByOperationId(operationId);
	}
	
	/**
	 * 通过用户编号和角色编号查询
	 * @param permissionId
	 * @param operationId
	 * @return
	 */
	public PermissionOperation getPermissionOperationByPermissionIdAndOperationId(String permissionId,String operationId){
		return permissionOperationDao.getByPermissionIdAndOperationId(permissionId, operationId);
	}
	
	/**
	 * 通过用户编号和角色编号查询
	 * @param permissionId
	 * @param operationId
	 * @return
	 */
	public int deletePermissionOperationByPermissionIdAndOperationId(String permissionId,String operationId){
		return permissionOperationDao.deleteByPermissionIdAndOperationId(permissionId, operationId);
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
	
	public PageInfo<Operation> search(int pageNum,int pageSize,String permissionId,Operation operation){
		PageHelper.startPage(pageNum, pageSize);
		List<Operation> operations = permissionOperationDao.searchOperations(permissionId,operation);
		PageInfo<Operation> pageInfo = new PageInfo<Operation>(operations);
		return pageInfo;
	}
	
	/**
	 * 分页查询没有分配权限的Operation
	 * @param pageNum
	 * @param pageSize
	 * @param permission
	 * @return
	 */
	public PageInfo<Operation> searchLeftOperation(int pageNum,int pageSize,String permissionId,Operation operation){
		PageHelper.startPage(pageNum, pageSize);
		List<Operation> operations = permissionOperationDao.searchLeftOperations(permissionId,operation);
		PageInfo<Operation> pageInfo = new PageInfo<Operation>(operations);
		return pageInfo;
	}
}
