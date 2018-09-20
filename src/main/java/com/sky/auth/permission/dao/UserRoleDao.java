package com.sky.auth.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.User;
import com.sky.auth.permission.domain.UserRole;

/**
 * 用户-角色
 * @author yangfan
 *
 */
public interface UserRoleDao extends BaseDao<UserRole>{
	
	public UserRole getByUserIdAndRoleId(@Param("userId")String userId,@Param("roleId")String roleId);	//通过用户编号和角色编号查找
	
	public int deleteByUserIdAndRoleId(@Param("userId") String userId,@Param("roleId") String roleId);	//通过用户编号和角色编号查找
	
	public List<User> getUsersByRoleId(String roleId);	//查找拥有角色的用户
	
	public List<Role> getRolesByUserId(String userId);	//查找用户拥有的角色
	
	//public List<Role> searchRolesByUserId(int pageNum,int pageSize,Role roleId);	//查找用户拥有的角色
	public int batchInsert(List<UserRole> userRoles);
	
	public List<Role> searchRoles(@Param("userId") String userId,@Param("role") Role role);	//查找用户拥有的角色
	
	public List<Role> searchLeftRoles(@Param("userId") String userId,@Param("role") Role role);	//查找用户没有的角色
	
}
