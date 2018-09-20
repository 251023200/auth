package com.sky.auth.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.auth.permission.dao.UserGroupDao;
import com.sky.auth.permission.domain.UserGroup;

/**
 * 用户-部门
 * @author yangfan
 *
 */
@Service
public class UserGroupService{
	
	@Autowired
	private UserGroupDao userGroupDao;
	
	/**
	 * 通过用户编号和部门编号查找
	 * @param userId 用户编号
	 * @param groupId 组编号
	 * @return UserGroup 用户组
	 */
	public UserGroup getByUserIdAndGroupId(String userId,String groupId){
		return userGroupDao.getByUserIdAndGroupId(userId, groupId);
	}
	
}
