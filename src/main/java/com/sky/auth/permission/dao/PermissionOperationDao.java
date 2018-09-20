package com.sky.auth.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.PermissionOperation;

/**
 * 用户-角色
 * @author yangfan
 *
 */
public interface PermissionOperationDao extends BaseDao<PermissionOperation>{
	
	public PermissionOperation getByPermissionIdAndOperationId(@Param("permissionId")String permissionId,@Param("operationId")String operationId);	//通过用户编号和角色编号查找
	
	public int deleteByPermissionIdAndOperationId(@Param("permissionId") String permissionId,@Param("operationId") String operationId);	//通过用户编号和角色编号查找
	
	public List<Permission> getPermissionsByOperationId(String operationId);	//查找拥有角色的用户
	
	public List<Operation> getOperationsByPermissionId(String permissionId);	//查找用户拥有的角色
	
	//public List<Operation> searchOperationsByPermissionId(int pageNum,int pageSize,Operation operationId);	//查找用户拥有的角色
	public int batchInsert(List<PermissionOperation> permissionOperations);
	
	public List<Operation> searchOperations(@Param("permissionId") String permissionId,@Param("operation") Operation operation);	//查找权限拥有的操作
	
	public List<Operation> searchLeftOperations(@Param("permissionId") String permissionId,@Param("operation") Operation operation); //查找权限没有的操作
	
	public int deleteByPermissionId(String permissionId);
}
