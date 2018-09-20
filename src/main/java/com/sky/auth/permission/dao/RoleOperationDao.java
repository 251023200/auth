package com.sky.auth.permission.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.RoleOperation;


/**
 * 角色权限dao
 * 
 * @author：yangfan
 */
public interface RoleOperationDao extends BaseDao<RoleOperation> {

	
	/**
	 * 删除某个角色的某个权限
	 * @param roleId
	 * @param operationId
	 */
	public void deleteByRoleIdAndOperationId(String roleId, Long operationId);
	
	/**
	 * 删除某个角色断所有权限
	 * @param roleId
	 */
	public int deleteByRoleId(String roleId);
	
	public List<Role> getRolesByOperationId(String operationId);
	
	/**
	 * 查询拥有角色的用户
	 * @param roleId
	 * @return
	 */
	public List<Operation> getOperationsByRoleId(String roleId);
	
	/**
	 * 通过用户编号和角色编号查询
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public RoleOperation getRoleOperationByRoleIdAndOperationId(String roleId,String operationId);
	
	public int deleteByRoleIdAndOperationId(@Param("roleId")String roleId,@Param("operationId")String operationId);
	
	public List<Operation> searchOperationsByRoleId(@Param("roleId") String roleId,@Param("operation") Operation operation);
	
	public int batchInsert(List<RoleOperation> roleOperations);
}
