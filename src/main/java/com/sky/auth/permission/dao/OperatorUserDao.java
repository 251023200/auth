package com.sky.auth.permission.dao;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.OperatorUser;

public interface OperatorUserDao extends BaseDao<OperatorUser>{
	/*
	 * 根据用户ID获取
	 */
   public OperatorUser getByUserID(String id);
   /*
    * 根据就用户id更新
    */
   public int updateByUserID(OperatorUser operatorUser);
   /*
    * 根据用户id删除
    */
   public int deleteByUserID(String id);
   
  
}
