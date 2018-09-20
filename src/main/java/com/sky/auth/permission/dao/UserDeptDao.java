package com.sky.auth.permission.dao;

import java.util.List;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Dept;
import com.sky.auth.permission.domain.User;
import com.sky.auth.permission.domain.UserDept;

/**
 * 部门操作
 * @author yangfan
 *
 */
public interface UserDeptDao extends BaseDao<UserDept>{
	
	public UserDept getByUserIdAndDeptId(String userId,String deptId);	//通过用户编号和部门编号查找
	
	/**
	 * 查询用户所在的部门
	 * @param userId
	 * @return
	 */
	public List<Dept> getDeptsByUserId(String userId);
	
	/**
	 * 查询部门下的用户
	 * @param deptId
	 * @return
	 */
	public List<User> getUsersByDeptId(String deptId);
	
}
