package com.sky.auth.permission.dao;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.UserGroup;

/**
 * 用户-组
 * @author yangfan
 *
 */
public interface UserGroupDao extends BaseDao<UserGroup>{
	
	public UserGroup getByUserIdAndGroupId(String userId,String groupId);	//通过用户编号和组编号查找
	
}
